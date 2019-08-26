package com.yp.zookeeper.curator.cache;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.EnsureContainers;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.listen.ListenerManager;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.utils.CloseableExecutorService;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;

public class PathChildrenCache implements Closeable {
    private static final ChildData NULL_CHILD_DATA = new ChildData("/", null, null);
    private static final boolean USE_EXISTS = Boolean.getBoolean("curator-path-children-cache-use-exists");

    public static final ThreadFactory DEFAULT_THREAD_FACTORY = ThreadUtils.newThreadFactory("PathChildrenCache");

    private final Logger log = LoggerFactory.getLogger(PathChildrenCache.class);

    private final WatcherRemoveCuratorFramework client;
    private final String path;
    private final CloseableExecutorService executorService;
    private final boolean cacheData;
    private final boolean dataIsCompressed;
    private final ListenerManager<PathChildrenCacheListener, PathChildrenCacheListener> listeners = StandardListenerManager.standard();
    private final ConcurrentMap<String, ChildData> currentData = Maps.newConcurrentMap();
    private final AtomicReference<Map<String, ChildData>> initialSet = new AtomicReference<>();
    private final Set<Operation> operationsQuantizer = Sets.newConcurrentHashSet();
    private final AtomicReference<State> state = new AtomicReference<>();
    private final EnsureContainers ensureContainers;

    private enum State {
        LATENT, STARTED, CLOSED
    }

    enum RefreshMode {
        STANDARD, FORCE_GET_DATA_AND_STAT,
        POST_INITIALIZED, NO_NODE_EXCEPTION
    }

    enum StartMode {
        NORMAL, BUILD_INITIAL_CACHE, POST_INITIALIZED_EVENT
    }

    private volatile Watcher childrenWatcher = (event) -> {
        offerOperation(new RefreshOperation(PathChildrenCache.this, RefreshMode.STANDARD));
    };

    private volatile Watcher dataWatcher = (event) -> {
      try {
          if(event.getType() == Watcher.Event.EventType.NodeDeleted) {
              remove(event.getPath());
          } else if(event.getType() == Watcher.Event.EventType.NodeDataChanged) {
              offerOperation(new GetDataOperation(PathChildrenCache.this, event.getPath()));
          }
      } catch(Exception e) {
          ThreadUtils.checkInterrupted(e);
          handleException(e);
      }
    };

    private volatile ConnectionStateListener connectionStateListener = (client, newState) -> {
        handleStateChange(newState);
    };

    public PathChildrenCache(CuratorFramework client, String path, boolean cacheData) {
        this(client, path, cacheData, false, new CloseableExecutorService(Executors.newSingleThreadExecutor(DEFAULT_THREAD_FACTORY), true));
    }

    public PathChildrenCache(CuratorFramework client, String path, boolean cacheData, boolean dataIsCompressed, CloseableExecutorService executorService) {
        this.client = client.newWatcherRemoveCuratorFramework();
        this.path = PathUtils.validatePath(path);
        this.cacheData = cacheData;
        this.dataIsCompressed = dataIsCompressed;
        this.executorService = executorService;
        ensureContainers = new EnsureContainers(client, path);
    }

    public void start() throws Exception {
        start(StartMode.NORMAL);
    }

    private void start(StartMode mode) throws Exception {
        Preconditions.checkState(state.compareAndSet(State.LATENT, State.STARTED), "already started");
        mode = Preconditions.checkNotNull(mode, "mode cannot be null");

        client.getConnectionStateListenable().addListener(connectionStateListener);
        switch(mode) {
            case NORMAL:
                offerOperation(new RefreshOperation(this, RefreshMode.STANDARD));
                break;
            case BUILD_INITIAL_CACHE:
                rebuild();
                break;
            case POST_INITIALIZED_EVENT:
                initialSet.set(Maps.newConcurrentMap());
                offerOperation(new RefreshOperation(this, RefreshMode.POST_INITIALIZED));
                break;
        }
    }

    private void rebuild() throws  Exception {
        Preconditions.checkState(state.get() == State.STARTED, "cache has been closed");
        ensurePath();
        clear();
        List<String> children = client.getChildren().forPath(path);
        children.forEach((child) -> {
            String fullPath = ZKPaths.makePath(path, child);
            try {
                initernalRebuildNode(fullPath);
            } catch (Exception e) {
                handleException(e);
            }
        });
        offerOperation(new RefreshOperation(this, RefreshMode.FORCE_GET_DATA_AND_STAT));
    }

    private void offerOperation(Operation operation) {
        if(operationsQuantizer.add(operation)) {
            submitToExecutor(() -> {
               try {
                   operationsQuantizer.remove(operation);
                   operation.invoke();
               } catch(InterruptedException e) {
                   if(state.get() != State.CLOSED) {
                       handleException(e);
                   }
                   Thread.currentThread().interrupt();
               } catch(Exception e) {
                   ThreadUtils.checkInterrupted(e);
                   handleException(e);
               }
            });
        }
    }

    private void handleException(Exception e) {
        log.error("", e);
    }

    private void initernalRebuildNode(String fullPath) throws Exception {
        if(cacheData) {
            try {
                Stat stat = new Stat();
                byte[] bytes = dataIsCompressed ? client.getData().decompressed().storingStatIn(stat).forPath(fullPath)
                        : client.getData().storingStatIn(stat).forPath(fullPath);
                currentData.put(fullPath, new ChildData(fullPath, stat, bytes));
            } catch(KeeperException.NoNodeException ignore) {
                currentData.remove(fullPath);
            }
        } else {
            Stat stat = client.checkExists().forPath(fullPath);
            if(stat != null) {
                currentData.put(fullPath, new ChildData(fullPath, stat, null));
            } else {
                currentData.remove(fullPath);
            }
        }
    }

    private void clear() {
        currentData.clear();
    }

    private void ensurePath() throws Exception {
        ensureContainers.ensure();
    }

    @Override
    public void close() throws IOException {
        if(state.compareAndSet(State.STARTED, State.CLOSED)) {
            client.getConnectionStateListenable().removeListener(connectionStateListener);
            listeners.clear();
            executorService.close();
            client.removeWatchers();
            connectionStateListener = null;
            childrenWatcher = null;
            dataWatcher = null;
        }
    }

    private synchronized void submitToExecutor(Runnable command) {
        if(state.get() == State.STARTED) {
            executorService.submit(command);
        }
    }

    public void rebuildNode(String fullPath) throws Exception {
        Preconditions.checkArgument(ZKPaths.getPathAndNode(fullPath).getPath().equals(path), "Node is not part of this cache: " + fullPath);
        Preconditions.checkState(state.get() == State.STARTED);
        ensurePath();
        initernalRebuildNode(fullPath);
        offerOperation(new RefreshOperation(this, RefreshMode.FORCE_GET_DATA_AND_STAT));
    }

    public ListenerManager<PathChildrenCacheListener, PathChildrenCacheListener> getListenable() {
        return listeners;
    }

    public List<ChildData> getCurrentData() {
        return ImmutableList.copyOf(Sets.newTreeSet(currentData.values()));
    }

    public ChildData getCurrentData(String fullPath) {
        return currentData.get(fullPath);
    }

    public void clearDataBytes(String fullPath) {
        clearDataBytes(fullPath, -1);
    }

    private boolean clearDataBytes(String fullPath, int ifVersion) {
        ChildData data = currentData.get(fullPath);
        if(data != null) {
            if(ifVersion < 0 || ifVersion == data.getStat().getVersion()) {
                if(data.getData() != null) {
                    currentData.replace(fullPath, data, new ChildData(data.getPath(), data.getStat(), null));
                }
                return true;
            }
        }
        return false;
    }


    public void refresh(RefreshMode mode) throws Exception {
        ensurePath();
        BackgroundCallback callback = ((client, event) -> {
           if(reRemoveWatchersOnBackgroundClosed()) {
               return;
           }
           if(event.getResultCode() == KeeperException.Code.OK.intValue()) {
               processChildren(event.getChildren(), mode);
           } else if(event.getResultCode() == KeeperException.Code.NONODE.intValue()) {
               if(mode == RefreshMode.NO_NODE_EXCEPTION) {
                   ensureContainers.reset();
               } else {
                   ensureContainers.reset();
                   offerOperation(new RefreshOperation(PathChildrenCache.this, RefreshMode.NO_NODE_EXCEPTION));
               }
           }
        });
        client.getChildren().usingWatcher(childrenWatcher).inBackground(callback).forPath(path);
    }

    void callListeners(PathChildrenCacheEvent event) {
        listeners.forEach(listener -> {
            try {
                listener.childEvent(client, event);
            } catch(Exception e) {
                ThreadUtils.checkInterrupted(e);
                handleException(e);
            }
        });
    }

    void getDataAndStat(String fullPath) throws Exception {
        BackgroundCallback callback = (client, event) -> {
          if(reRemoveWatchersOnBackgroundClosed()) {
              return;
          }
          applyNewData(fullPath, event.getResultCode(), event.getStat(), cacheData ? event.getData() : null);
        };

        if(USE_EXISTS && !cacheData) {
            client.checkExists().usingWatcher(dataWatcher).inBackground(callback).forPath(fullPath);
        } else {
            if(dataIsCompressed && cacheData) {
                client.getData().decompressed().usingWatcher(dataWatcher).inBackground(callback).forPath(fullPath);
            } else {
                client.getData().usingWatcher(dataWatcher).inBackground(callback).forPath(fullPath);
            }
        }
    }

    private boolean reRemoveWatchersOnBackgroundClosed() {
        if(state.get() == State.CLOSED) {
            client.removeWatchers();
            return true;
        } else {
            return false;
        }
    }

    private void handleStateChange(ConnectionState newState) {
        switch(newState) {
            case SUSPENDED:
                offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CONNECTION_SUSPENDED, null)));
                break;
            case LOST:
                offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CONNECTION_LOST, null)));
                break;
            case CONNECTED:
            case RECONNECTED:
                offerOperation(new RefreshOperation(this, RefreshMode.FORCE_GET_DATA_AND_STAT));
                offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CONNECTION_RECONNECTED, null)));
                break;
        }
    }

    private void processChildren(List<String> children, RefreshMode mode) throws Exception {
        Set<String> removedNodes = Sets.newHashSet(currentData.keySet());
        children.forEach((child -> {
            removedNodes.remove(ZKPaths.makePath(path, child));
        }));
        removedNodes.forEach(fullPath -> {
            remove(fullPath);
        });
        children.forEach(name -> {
            try {
                String fullPath = ZKPaths.makePath(path, name);
                if (mode == RefreshMode.FORCE_GET_DATA_AND_STAT || !currentData.containsKey(fullPath)) {
                    getDataAndStat(fullPath);
                }
                updateInitializedEvent(name, NULL_CHILD_DATA);
            } catch(Exception e) {
                ThreadUtils.checkInterrupted(e);
                handleException(e);
            }
        });
        maybeOfferInitializedEvent(initialSet.get());
    }

    private void applyNewData(String fullPath, int resultCode, Stat stat, byte[] bytes) {
        if(resultCode == KeeperException.Code.OK.intValue()) {
            ChildData data = new ChildData(fullPath, stat, bytes);
            ChildData previousData = currentData.put(fullPath, data);
            if(previousData == null) {
                offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CHILD_ADDED, data)));
            } else if(stat.getMzxid() != previousData.getStat().getMzxid()) {
                offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CHILD_UPDATED, data)));
            }
            updateInitializedEvent(ZKPaths.getNodeFromPath(fullPath), data);
        } else if(resultCode == KeeperException.Code.NONODE.intValue()){
            log.debug("NoNode at path {}, removing child from initialSet", fullPath);
            remove(fullPath);
        }
    }

    private void updateInitializedEvent(String name, ChildData data) {
        Map<String, ChildData> localInitialSet = initialSet.get();
        if(localInitialSet != null) {
            localInitialSet.put(name, data);
            maybeOfferInitializedEvent(localInitialSet);
        }
    }

    private void maybeOfferInitializedEvent(Map<String, ChildData> localInitialSet) {
        if(!hasUninitialized(localInitialSet)) {
            if(initialSet.getAndSet(null) != null) {
                List<ChildData> children = ImmutableList.copyOf(localInitialSet.values());
                PathChildrenCacheEvent event = new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.INITIALIZED, null) {
                    @Override
                    public List<ChildData> getInitialData() {
                        return children;
                    }
                };
                offerOperation(new EventOperation(this, event));
            }
        }
    }

    private boolean hasUninitialized(Map<String, ChildData> localInitialSet) {
        if(localInitialSet == null) {
            return false;
        }
        Map<String, ChildData> uninitializedChildren = Maps.filterValues(localInitialSet, input -> input == NULL_CHILD_DATA);
        return !uninitializedChildren.isEmpty();
    }

    protected void remove(String fullPath) {
        ChildData data = currentData.remove(fullPath);
        if(data != null) {
            offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CHILD_REMOVED, data)));
        }
        Map<String, ChildData> localInitialSet = initialSet.get();
        if(localInitialSet != null) {
            localInitialSet.remove(ZKPaths.getNodeFromPath(fullPath));
            maybeOfferInitializedEvent(localInitialSet);
        }
    }


}
