package com.yp.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZooKeeperSimple {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, (event)->{
            System.out.println("Receive watched event: " + event);
            if(Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                latch.countDown();
            }
        });

        latch.await();
    }
}
