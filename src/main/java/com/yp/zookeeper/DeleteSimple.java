package com.yp.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class DeleteSimple {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, (event) -> {
            if(event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                latch.countDown();
            }
        });
        latch.await();
        Stat stat = zooKeeper.exists("/for-delete-path", false);
        if(stat != null) {
            zooKeeper.delete("/for-delete-path", stat.getVersion());
        }
        stat = zooKeeper.exists("/for-delete-path2", false);
        if(stat != null) {
            zooKeeper.delete("/for-delete-path2", stat.getVersion());
        }

        zooKeeper.create("/for-delete-path", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/for-delete-path2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        stat = zooKeeper.exists("/for-delete-path", false);
        zooKeeper.delete("/for-delete-path", stat.getVersion());

        CountDownLatch async = new CountDownLatch(1);
        stat = zooKeeper.exists("/for-delete-path2", false);
        zooKeeper.delete("/for-delete-path2", stat.getVersion(), (rc, path, ctx) -> {
            System.out.println("rc: " + rc + ", path: " + path);
            async.countDown();
        }, "");
        async.await();
    }
}
