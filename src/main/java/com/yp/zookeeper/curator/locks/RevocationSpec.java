package com.yp.zookeeper.curator.locks;

import java.util.concurrent.Executor;

public class RevocationSpec {
    private final Runnable runnable;
    private final Executor executor;

    public RevocationSpec(Executor executor, Runnable runnable) {
        this.executor = executor;
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public Executor getExecutor() {
        return executor;
    }
}
