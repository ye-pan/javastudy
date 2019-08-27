package com.yp.zookeeper.curator.locks;

import java.util.concurrent.Executor;

public interface Revocable<T> {
    void makeRevocable(RevocationListener<T> listener);

    void makeRevocable(RevocationListener<T> listener, Executor executor);
}
