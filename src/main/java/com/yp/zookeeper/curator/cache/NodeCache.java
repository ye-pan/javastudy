package com.yp.zookeeper.curator.cache;

import com.google.common.base.Preconditions;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.listen.ListenerManager;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class NodeCache implements Closeable {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final WatcherRemoveCuratorFramework client;
    private final String path;
    private final boolean dataIsCompressed;
    private final AtomicReference<ChildData> data = new AtomicReference<>(null);
    private final AtomicReference<State> state = new AtomicReference<>(State.LATENT);
    private ListenerManager<NodeCacheListener, NodeCacheListener> listeners = StandardListenerManager.standard();
    private final AtomicBoolean isConnected = new AtomicBoolean(true);
    private ConnectionStateListener connectionStateListener = (client, newState) -> {
      if((newState == ConnectionState.CONNECTED) || (newState == ConnectionState.RECONNECTED)) {
          if(isConnected.compareAndExchange(false, true)) {
              try {
                  reset();
              } catch (Exception e) {
                  ThreadUtils.checkInterrupted(e);
                  log.error("Trying to reset after reconnection", e);
              }
          }
      } else {
          isConnected.set(false);
      }
    };

    private Watcher watcher = (event) -> {
        try {
            reset();
        } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            handleException(e);
        }
    };

    private void handleException(Exception e) {
        log.error("", e);
    }

    private enum State {
        LATENT, STARTED, CLOSED
    }

    private final BackgroundCallback backgroundCallback = (client, event) -> {
        processBackgroundResult(event);
    };

    public NodeCache(CuratorFramework client, String path) {
        this(client, path, false);
    }

    public NodeCache(CuratorFramework client, String path, boolean dataIsCompressed) {
        this.client = client.newWatcherRemoveCuratorFramework();
        this.path = PathUtils.validatePath(path);
        this.dataIsCompressed = dataIsCompressed;
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void start() throws Exception {
        start(false);
    }

    public void start(boolean buildInitial) throws Exception {
        Preconditions.checkState(state.compareAndSet(State.LATENT, State.STARTED), "Cannot be started more than once");
        client.getConnectionStateListenable().addListener(connectionStateListener);
        if(buildInitial) {
            client.checkExists().creatingParentContainersIfNeeded().forPath(path);
            internalRebuild();
        }
        reset();
    }

    private void reset() throws Exception {
        if(state.get() == State.STARTED && isConnected.get()) {
            client.checkExists()
                    .creatingParentContainersIfNeeded()
                    .usingWatcher(watcher)
                    .inBackground(backgroundCallback)
                    .forPath(path);
        }
    }

    private void internalRebuild() throws Exception {
        try {
            Stat stat = new Stat();
            byte[] bytes = dataIsCompressed ? client.getData().decompressed().storingStatIn(stat).forPath(path)
                    : client.getData().storingStatIn(stat).forPath(path);
            data.set(new ChildData(path, stat, bytes));
        } catch(KeeperException.NoNodeException e) {
            data.set(null);
        }
    }

    @Override
    public void close() throws IOException {
        if(state.compareAndSet(State.STARTED, State.CLOSED)) {
            client.removeWatchers();
            listeners.clear();
            client.getConnectionStateListenable().removeListener(connectionStateListener);
            connectionStateListener = null;
            watcher = null;
        }
    }

    public ListenerManager<NodeCacheListener, NodeCacheListener> getListenable() {
        Preconditions.checkState(state.get() != State.CLOSED, "Closed");
        return listeners;
    }

    public void rebuild() throws Exception {
        Preconditions.checkState(state.get() == State.STARTED, "Not started");
        internalRebuild();
        reset();
    }

    public ChildData getCurrentData() {
        return data.get();
    }

    private void processBackgroundResult(CuratorEvent event) throws Exception {
        switch(event.getType()) {
            case GET_DATA:
                if(event.getResultCode() == KeeperException.Code.OK.intValue()) {
                    ChildData childData = new ChildData(path, event.getStat(), event.getData());
                    setNewData(childData);
                }
                break;
            case EXISTS:
                if(event.getResultCode() == KeeperException.Code.NONODE.intValue()) {
                    setNewData(null);
                } else if(event.getResultCode() == KeeperException.Code.OK.intValue()) {
                    if(dataIsCompressed) {
                        client.getData().decompressed().usingWatcher(watcher).inBackground(backgroundCallback).forPath(path);
                    } else {
                        client.getData().usingWatcher(watcher).inBackground(backgroundCallback).forPath(path);
                    }
                }
                break;
        }
    }

    private void setNewData(ChildData newData) {
        ChildData previousData = data.getAndSet(newData);
        if(!Objects.equals(previousData, newData)) {
            listeners.forEach((listener) -> {
                try {
                    listener.nodeChanged();
                } catch(Exception e) {
                    ThreadUtils.checkInterrupted(e);
                    log.error("Calling listener", e);
                }
            });
        }
    }
}
