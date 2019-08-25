package com.yp.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import java.util.concurrent.CountDownLatch;

public class SessionIdPasswdSimple {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, (event)->{
            System.out.println("event: " + event);
            if(event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                latch.countDown();
            }
        });
        latch.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();
        zooKeeper = new ZooKeeper("localhost:2181", 5000, (event) -> {
            System.out.println("event: " + event);
        }, 1L, "test".getBytes());

        zooKeeper = new ZooKeeper("localhost:2181", 5000, (event) -> {
            System.out.println("event: " + event);
        }, sessionId, passwd);
        Thread.sleep(10000);
    }
}
