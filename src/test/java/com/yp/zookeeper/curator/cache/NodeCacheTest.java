package com.yp.zookeeper.curator.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

import static org.junit.Assert.*;

public class NodeCacheTest {
    private CuratorFramework client;
    private String namespace = "zk-test";
    private String path;
    private String init = "init";

    @Before
    public void before() {
        client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace(namespace)
                .build();
        client.start();
        path = "/zk-book/nodecache";
        try {
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(path, init.getBytes());
        } catch(Exception e) {
            throw new RuntimeException("测试用例初始化失败", e);
        }
    }

    @After
    public void after() {
        client.close();
    }

    @Test
    public void testNodeCache() throws Exception {
        NodeCache nodeCache = new NodeCache(client, path);
        try {
            CyclicBarrier barrier = new CyclicBarrier(2);
            nodeCache.start();
            nodeCache.getListenable().addListener(() -> {
                System.out.println("Node data changed, new data: " + new String(nodeCache.getCurrentData().getData()));
                barrier.await();
            });

            String newVal = "update";
            client.setData().forPath(path, newVal.getBytes());
            barrier.await();
            String cacheValue = new String(nodeCache.getCurrentData().getData());
            assertEquals(newVal, cacheValue);
        } finally {
            nodeCache.close();
        }
    }
}