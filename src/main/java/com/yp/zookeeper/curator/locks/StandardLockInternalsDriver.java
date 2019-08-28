package com.yp.zookeeper.curator.locks;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import java.util.List;

public class StandardLockInternalsDriver implements LockInternalsDriver {

    @Override
    public PredicateResults getsTheLock(CuratorFramework client, List<String> children, String sequenceNodeName, int maxLeases) throws Exception {
        int ourIndex = children.indexOf(sequenceNodeName);
        if(ourIndex < 0) {
            throw new KeeperException.NoNodeException("Sequential path not found: " + sequenceNodeName);
        }
        boolean getsTheLock = ourIndex < maxLeases;
        String pathToWatch = getsTheLock ? null : children.get(ourIndex - maxLeases);
        return new PredicateResults(pathToWatch, getsTheLock);
    }

    @Override
    public String createTheLock(CuratorFramework client, String path, byte[] lockNodeBytes) throws Exception {
        if(lockNodeBytes != null) {
            return client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, lockNodeBytes);
        } else {
            return client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        }
    }

    @Override
    public String fixForSorting(String path, String lockName) {
        int index = path.lastIndexOf(lockName);
        if(index >= 0) {
            index += lockName.length();
            return index <= path.length() ? path.substring(index) : "";
        }
        return path;
    }
}
