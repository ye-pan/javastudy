package com.yp.zookeeper.curator.locks;

public class PredicateResults {
    private final boolean getsTheLock;
    private final String pathToWatch;

    public PredicateResults(String pathToWatch, boolean getsTheLock) {
        this.pathToWatch = pathToWatch;
        this.getsTheLock = getsTheLock;
    }

    public boolean getsTheLock() {
        return getsTheLock;
    }

    public String getPathToWatch() {
        return pathToWatch;
    }
}
