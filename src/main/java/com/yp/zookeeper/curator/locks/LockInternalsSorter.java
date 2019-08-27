package com.yp.zookeeper.curator.locks;

public interface LockInternalsSorter {
    String fixForSorting(String str, String lockName);
}
