package com.yp.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorSimple {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("learn-zookeeper").build();
        client.start();

        String path = "/path";
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());

        String deletePath = "/path-delete";
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(deletePath);

        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(deletePath);
        client.delete()
                .deletingChildrenIfNeeded()
                .withVersion(stat.getVersion())
                .forPath(deletePath);

        byte[] bytes = client.getData()
                .storingStatIn(stat).forPath(path);
        System.out.println(new String(bytes));

    }
}
