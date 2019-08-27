package com.yp.zookeeper.curator.locks;

public interface RevocationListener<T> {
    void revocationRequested(T forLock);
}
