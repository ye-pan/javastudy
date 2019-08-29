package com.yp.zookeeper.curator.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Locker implements AutoCloseable {
    private final InterProcessLock lock;
    private final AtomicBoolean acquired = new AtomicBoolean(false);

    public Locker(InterProcessLock lock, long timeout, TimeUnit unit) throws Exception {
        this.lock = lock;
        acquired.set(acquireLock(lock, timeout, unit));
        if(!acquired.get()) {
            throw new TimeoutException("Could not acquire lock within timeout of " + unit.toMillis(timeout) + "ms");
        }
    }

    public Locker(InterProcessLock lock) throws Exception {
        this.lock = lock;
        acquireLock(lock);
        acquired.set(true);
    }
    @Override
    public void close() throws Exception {
        if(acquired.compareAndSet(true, false)) {
            releaseLock();
        }
    }

    private void releaseLock() throws Exception {
        lock.release();
    }

    private void acquireLock(InterProcessLock lock) throws Exception {
        lock.acquire();
    }

    private boolean acquireLock(InterProcessLock lock, long timeout, TimeUnit unit) throws Exception {
        return lock.acquire(timeout, unit);
    }
}
