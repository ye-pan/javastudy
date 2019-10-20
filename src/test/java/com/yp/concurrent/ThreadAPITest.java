package com.yp.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ThreadAPITest {

    private static final Logger log = LoggerFactory.getLogger(ThreadAPITest.class);

    @Test
    public void testInterrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //抛出InterruptedException后，线程中断状态会恢复，
                // 所以有些代码在捕获了异常后又重新interrupt()恢复了中断状态
                Thread.currentThread().interrupt();
                //此处为什么为false?，看下Thread.sleep方法的注释，InterruptedException有说明
                assertFalse(Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        thread.interrupt();
        thread.join();
    }

    @Test
    public void testWait() throws InterruptedException {
        final int timeToWait = 3000;
        final int timeToSleep = 5000;
        Object mutex = new Object();
        Thread thread = new Thread(() -> {
           synchronized (mutex) {
               try {
                   long start = System.currentTimeMillis();
                   mutex.wait(timeToWait);
                   long end = System.currentTimeMillis() - start;
                   assertTrue(end >= timeToSleep);
                   log.debug("time wait done...");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (mutex) {
                try {
                    long start = System.currentTimeMillis();
                    Thread.sleep(timeToSleep);
                    long end = System.currentTimeMillis() - start;
                    assertTrue(end >= timeToSleep);
                    log.debug("sleep done...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
        thread2.start();
        thread2.join();
    }
}
