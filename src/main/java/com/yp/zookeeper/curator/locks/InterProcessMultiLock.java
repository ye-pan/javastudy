package com.yp.zookeeper.curator.locks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.ThreadUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InterProcessMultiLock implements InterProcessLock {
    private final List<InterProcessLock> locks;

    public InterProcessMultiLock(CuratorFramework client, List<String> paths) {
        this(makeLocks(client, paths));
    }

    public InterProcessMultiLock(List<InterProcessLock> locks) {
        this.locks = locks;
    }

    private static List<InterProcessLock> makeLocks(CuratorFramework client, List<String> paths) {
        ImmutableList.Builder<InterProcessLock> builder = ImmutableList.builder();
        paths.forEach(path -> {
            InterProcessLock lock = new InterProcessMutex(client, path);
            builder.add(lock);
        });
        return builder.build();
    }
    @Override
    public void acquire() throws Exception {
        acquire(-1, null);
    }

    @Override
    public boolean acquire(long time, TimeUnit unit) throws Exception {
        Exception exception = null;
        List<InterProcessLock> acquired = Lists.newArrayList();
        boolean success = true;
        for (InterProcessLock lock : locks) {
            try {
                if(unit == null) {
                    lock.acquire();
                    acquired.add(lock);
                } else {
                    if(lock.acquire(time, unit)) {
                        acquired.add(lock);
                    } else {
                        success = false;
                        break;
                    }
                }
            } catch(Exception e) {
                ThreadUtils.checkInterrupted(e);
                success = false;
                exception = e;
            }
        }
        if(!success) {
            for (InterProcessLock lock : Lists.reverse(acquired)) {
                try {
                    lock.release();
                } catch(Exception e) {
                    ThreadUtils.checkInterrupted(e);
                }
            }
        }
        if(exception != null) {
            throw exception;
        }
        return false;
    }

    @Override
    public void release() throws Exception {
        Exception exception = null;
        for (InterProcessLock lock : Lists.reverse(locks)) {
            try {
                lock.release();
            } catch(Exception e) {
                ThreadUtils.checkInterrupted(e);
                if(exception == null) {
                    exception = e;
                } else {
                    exception = new Exception(exception);
                }
            }
        }
        if(exception != null) {
            throw exception;
        }
    }

    @Override
    public boolean isAcquiredInThisProcess() {
        for (InterProcessLock lock : locks) {
            if(!lock.isAcquiredInThisProcess()) {
                return false;
            }
        }
        return true;
    }
}
