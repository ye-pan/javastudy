package com.yp.zookeeper.curator.locks;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.MoreExecutors;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.PathUtils;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class InterProcessMutex implements InterProcessLock, Revocable<InterProcessMutex> {
    private final LockInternals internals;
    private final String basePath;
    private final ConcurrentMap<Thread, LockData> threadData = Maps.newConcurrentMap();
    private static final String LOCK_NAME = "lock-";

    private static class LockData {
        final Thread owningThread;
        final String lockPath;
        final AtomicInteger lockCount = new AtomicInteger(1);
        private LockData(Thread owningThread, String lockPath) {
            this.owningThread = owningThread;
            this.lockPath = lockPath;
        }
    }

    public InterProcessMutex(CuratorFramework client, String path) {
        this(client, path, new StandardLockInternalsDriver());
    }

    public InterProcessMutex(CuratorFramework client, String path, LockInternalsDriver driver) {
        this(client, path, LOCK_NAME, 1, driver);
    }

    public InterProcessMutex(CuratorFramework client, String path, String lockName, int maxLeases, LockInternalsDriver driver) {
        basePath = PathUtils.validatePath(path);
        internals = new LockInternals(client, driver, path, lockName, maxLeases);
    }

    public boolean isOwnedByCurrentThread() {
        LockData lockData = threadData.get(Thread.currentThread());
        return (lockData != null) && (lockData.lockCount.get() > 0);
    }

    protected byte[] getLockNodeBytes() {
        return null;
    }

    protected String getLockPath() {
        LockData lockData = threadData.get(Thread.currentThread());
        return lockData != null ? lockData.lockPath : null;
    }

    private boolean internalLock(long time, TimeUnit unit) throws Exception {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        if(lockData != null) {
            lockData.lockCount.incrementAndGet();
            return true;
        }
        String lockPath = internals.attempLock(time, unit, getLockNodeBytes());
        if(lockPath != null) {
            LockData newLockData = new LockData(currentThread, lockPath);
            threadData.put(currentThread, newLockData);
            return true;
        }
        return false;
    }

    @Override
    public void acquire() throws Exception {
        if(!internalLock(-1, null)) {
            throw new IOException("Lost connection while trying to acquire lock: " + basePath);
        }
    }

    @Override
    public boolean acquire(long time, TimeUnit unit) throws Exception {
        Preconditions.checkArgument(time > 0);
        Preconditions.checkNotNull(unit);
        return internalLock(time, unit);
    }

    @Override
    public void release() throws Exception {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        if(lockData == null) {
            throw new IllegalMonitorStateException("You do not own the lock: " + basePath);
        }

        int newLockCount = lockData.lockCount.decrementAndGet();
        if(newLockCount > 0) {
            return;
        }
        if(newLockCount < 0) {
            throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + basePath);
        }
        try {
            internals.releaseLock(lockData.lockPath);
        } finally {
            threadData.remove(currentThread);
        }
    }

    @Override
    public boolean isAcquiredInThisProcess() {
        return threadData.size() > 0;
    }

    @Override
    public void makeRevocable(RevocationListener<InterProcessMutex> listener) {
        makeRevocable(listener, MoreExecutors.directExecutor());
    }

    @Override
    public void makeRevocable(RevocationListener<InterProcessMutex> listener, Executor executor) {
        internals.makeRevocable(new RevocationSpec(executor, () -> listener.revocationRequested(InterProcessMutex.this)));
    }
}
