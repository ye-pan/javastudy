package com.yp.zookeeper.curator.locks;

import org.apache.curator.framework.CuratorFramework;

import java.util.List;

public interface LockInternalsDriver extends LockInternalsSorter {
    PredicateResults getsTheLock(CuratorFramework client, List<String> children, String sequenceNodeName, int maxLeases) throws Exception;

    String createsTheLock(CuratorFramework client, String path, byte[] lockNodeBytes) throws Exception;
}
