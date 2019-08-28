package com.yp.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;

public class ZKSimpleTest {
    protected CuratorFramework client;
    protected String namespace = "zk-test";
    protected String init = "init";

    @Before
    public void before() {
        client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace(namespace)
                .build();
        client.start();
    }

    @After
    public void after() {
        client.create();
    }
}
