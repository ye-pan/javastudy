package com.yp.zookeeper.curator.locks;

import com.google.common.base.Preconditions;
import org.apache.curator.RetryLoop;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class LockInternals {
    private static final Logger log = LoggerFactory.getLogger(LockInternals.class);
    private static final byte[] REVOKE_MESSAGE = "__REVOKE__".getBytes();
    private WatcherRemoveCuratorFramework client;
    private final String path;
    private final String basePath;
    private final LockInternalsDriver driver;
    private final String lockName;
    private final AtomicReference<RevocationSpec> revocable = new AtomicReference<>(null);
    private volatile int maxLeases;

    private final CuratorWatcher revocableWatcher = (event) -> {
        if(event.getType() == Watcher.Event.EventType.NodeDataChanged) {
            checkRevocableWatcher(event.getPath());
        }
    };
    private final Watcher watcher = (event) -> {
        client.postSafeNotify(LockInternals.this);
    };
    public static final int WAIT_NO_TIME = -1;


    public LockInternals(CuratorFramework client, LockInternalsDriver driver, String path, String lockName, int maxLeases) {
        this.driver = driver;
        this.lockName = lockName;
        this.maxLeases = maxLeases;
        this.client = client.newWatcherRemoveCuratorFramework();
        this.basePath = PathUtils.validatePath(path);
        this.path = ZKPaths.makePath(path, lockName);
    }

    public void clean() throws Exception {
        try {
            client.delete().forPath(basePath);
        } catch(KeeperException.BadVersionException | KeeperException.NotEmptyException e) {
            log.debug("ignore error.", e);
        }
    }

    public synchronized void setMaxLeases(int maxLeases) {
        this.maxLeases = maxLeases;
        notifyAll();
    }

    public void makeRevocable(RevocationSpec entry) {
        revocable.set(entry);
    }

    public final void releaseLock(String lockPath) throws Exception {
        client.removeWatchers();
        revocable.set(null);
        deleteOurPath(lockPath);
    }

    public CuratorFramework getClient() {
        return client;
    }

   /* public static Collection<String> getParticipantNodes(CuratorFramework client, String basePath, String lockName, LockInternalsSorter sorter) throws Exception {
        List<String> names = getSortedChildren(client, basePath, lockName, sorter);
        Iterable<String> transformed = names.stream().map((name) -> ZKPaths.makePath(basePath, name)).collect(Collectors.toList());
        return ImmutableList.copyOf(transformed);
    }*/

    /*public static List<String> getSortedChildren(String lockName, LockInternalsSorter sorter, List<String> children) {
        List<String> sortedList = Lists.newArrayList(children);
        sortedList.sort(Comparator.comparing(path -> sorter.fixForSorting(path, lockName)));
        return sortedList;
    }*/

    public List<String> getSortedChildren() throws Exception {
        try {
            List<String> children = client.getChildren().forPath(basePath);//array list
            children.sort(Comparator.comparing(path1 -> driver.fixForSorting(path1, lockName)));
            return children;
        } catch(KeeperException.NoNodeException e) {
            log.debug("ignore error.", e);
            return Collections.emptyList();
        }
    }

    public String getLockName() {
        return lockName;
    }

    public LockInternalsDriver getDriver() {
        return driver;
    }

    public String attempLock(long time, TimeUnit unit, byte[] lockNodeBytes) throws Exception {
        long startMillis = System.currentTimeMillis();
        long millisToWait = (unit != null) ? unit.toMillis(time) : WAIT_NO_TIME;
        byte[] localLockNodeBytes = revocable.get() != null ? new byte[0] : lockNodeBytes;
        int retryCount = 0;
        String ourPath = null;
        boolean hasTheLock = false;
        boolean isDone = false;
        while(!isDone) {
            isDone = true;
            try {
                ourPath = driver.createTheLock(client, path, localLockNodeBytes);
                hasTheLock = internalLockLoop(startMillis, millisToWait, ourPath);
            } catch(KeeperException.NoNodeException e) {
                if(client.getZookeeperClient().getRetryPolicy().allowRetry(retryCount++, System.currentTimeMillis() - startMillis, RetryLoop.getDefaultRetrySleeper())) {
                    isDone = false;
                } else {
                    throw e;
                }
            }
        }
        if(hasTheLock) {
            return ourPath;
        } else {
            return null;
        }
    }

    private void checkRevocableWatcher(String path) throws Exception {
        RevocationSpec entry = revocable.get();
        if(entry != null) {
            try {
                byte[] bytes = client.getData().usingWatcher(revocableWatcher).forPath(path);
                if(Arrays.equals(bytes, REVOKE_MESSAGE)) {
                    entry.getExecutor().execute(entry.getRunnable());
                }
            } catch(KeeperException.NoNodeException ignore) { }
        }
    }

    private boolean internalLockLoop(long startMillis, long millisToWait, String ourPath) throws Exception {
        boolean haveTheLock = false;
        boolean doDelete = false;
        try {
            if(revocable.get() != null) {
                client.getData().usingWatcher(revocableWatcher).forPath(ourPath);
            }
            while(client.getState() == CuratorFrameworkState.STARTED && !haveTheLock) {
                List<String> children = getSortedChildren();
                String sequenceNodeName = ourPath.substring(basePath.length() + 1);
                PredicateResults predicateResults = driver.getsTheLock(client, children, sequenceNodeName, maxLeases);
                if(predicateResults.getsTheLock()) {
                    haveTheLock = true;
                } else {
                    String previousSequencePath = basePath + "/" + predicateResults.getPathToWatch();
                    synchronized(this) {
                        try {
                            client.getData().usingWatcher(watcher).forPath(previousSequencePath);
                            if(millisToWait != WAIT_NO_TIME) {
                                millisToWait -= (System.currentTimeMillis() - startMillis);
                                startMillis = System.currentTimeMillis();
                                if(millisToWait <= 0) {
                                    doDelete = true;
                                    break;
                                }
                                wait(millisToWait);
                            } else {
                                wait();
                            }
                        } catch(KeeperException.NoNodeException ignore) { }
                    }
                }
            }
            return haveTheLock;
        } catch(Exception e) {
            ThreadUtils.checkInterrupted(e);
            doDelete = true;
            throw e;
        } finally {
            if(doDelete) {
                deleteOurPath(ourPath);
            }
        }
    }

    private void deleteOurPath(String ourPath) throws Exception {
        try {
            client.delete().guaranteed().forPath(ourPath);
        } catch(KeeperException.NoNodeException ignore) { }
    }
}
