package com.yp.zookeeper.curator.cache;

import org.apache.curator.framework.CuratorFramework;

public interface PathChildrenCacheListener {
    void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception;
}
