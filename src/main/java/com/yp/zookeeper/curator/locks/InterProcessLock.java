package com.yp.zookeeper.curator.locks;

import java.util.concurrent.TimeUnit;

public interface InterProcessLock {
    void acquire() throws Exception;

    boolean acquire(long time, TimeUnit unit) throws Exception;

    void release() throws Exception;

    boolean isAcquiredInThisProcess();
}
