package com.yp.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 顺序死锁
 */
public class LeftRightDeadLock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                System.out.println("holder left-right lock");
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                System.out.println("holder right-left lock");
            }
        }
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        LeftRightDeadLock deadLock = new LeftRightDeadLock();
        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deadLock.leftRight();
        }).start();
        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deadLock.rightLeft();
        }).start();
        latch.countDown();
    }
}
