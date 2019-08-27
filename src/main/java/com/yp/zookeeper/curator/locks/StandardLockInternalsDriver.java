package com.yp.zookeeper.curator.locks;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import java.util.List;

public class StandardLockInternalsDriver implements LockInternalsDriver {

    @Override
    public PredicateResults getsTheLock(CuratorFramework client, List<String> children, String sequenceNodeName, int maxLeases) throws Exception {
        int ourIndex = children.indexOf(sequenceNodeName);
        validateOurIndex(sequenceNodeName, ourIndex);
        boolean getsTheLock = ourIndex < maxLeases;
        String pathToWatch = getsTheLock ? null : children.get(ourIndex - maxLeases);
        return new PredicateResults(pathToWatch, getsTheLock);
    }

    @Override
    public String createsTheLock(CuratorFramework client, String path, byte[] lockNodeBytes) throws Exception {
        String ourPath;
        if(lockNodeBytes != null) {
            ourPath = client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, lockNodeBytes);
        } else {
            ourPath = client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        }
        return ourPath;
    }

    @Override
    public String fixForSorting(String str, String lockName) {
        return standardFixForSorting(str, lockName);
    }

    public static String standardFixForSorting(String str, String lockName) {
        int index = str.lastIndexOf(lockName);
        if(index >= 0) {
            index += lockName.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }

    static void validateOurIndex(String sequenceNodeName, int ourIndex) throws KeeperException.NoNodeException {
        if(ourIndex < 0) {
            throw new KeeperException.NoNodeException("Sequential path not found: " + sequenceNodeName);
        }
    }
}
