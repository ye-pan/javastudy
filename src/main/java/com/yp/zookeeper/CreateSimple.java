package com.yp.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import java.util.concurrent.CountDownLatch;

public class CreateSimple {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, (event) -> {
            if(event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                latch.countDown();
            }
        });
        latch.await();

        String path = zooKeeper.create("/zk-test-ephemeral", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Success create znode " + path);

        path = zooKeeper.create("/zk-test-ephemeral-sequential", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success crate znode " + path);

        CountDownLatch asyncLatch = new CountDownLatch(2);

        zooKeeper.create("/zk-test-ephemeral-async", "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                (rc, p, ctx, name) -> {
                    String msg = String.format("Create path result: [%d, %s, %s], real path name: %s", rc, p, ctx, name);
                    System.out.println(msg);
                    asyncLatch.countDown();
                },
                "Context");

        zooKeeper.create("/zk-test-ephemeral-sequential-async", "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                (rc, p, ctx, name) -> {
                    String msg = String.format("Create path result: [%d, %s, %s], real path name: %s", rc, p, ctx, name);
                    System.out.println(msg);
                    asyncLatch.countDown();
                },
                "Context");

        asyncLatch.await();
    }
}
