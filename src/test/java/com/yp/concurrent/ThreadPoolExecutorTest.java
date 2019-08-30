package com.yp.concurrent;

import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ThreadPoolExecutorTest {

    @Test
    public void testMaxThreadGrow() throws InterruptedException {
        int taskQueueSize = 10;
        int maximumPoolSize = 10;
        int corePoolSize = 1;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(taskQueueSize));
        int taskCount = 30;
        CountDownLatch latch = new CountDownLatch(taskCount);
        for(int i = 0; i < taskCount; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("done " + Thread.currentThread().getName());
                latch.countDown();
            });
            if(i == 0) {
                Thread.sleep(1000);
            }
            //有任务队列的执行池，只有任务数量大于任务队列数量的时候，才会开启新线程去处理任务，并且新线程数量不会超过最大线程数
            if(i > (maximumPoolSize + taskQueueSize)) {
                assertEquals(maximumPoolSize, executor.getActiveCount());
            }
            if(i > taskQueueSize && i < (maximumPoolSize + taskQueueSize)) {
                Assert.assertTrue(executor.getActiveCount() > corePoolSize);
            }
            if(i < maximumPoolSize) {
                assertEquals(corePoolSize, executor.getActiveCount());
            }
        }

        latch.await();
    }

    @Test
    public void testFixedThreadPool() throws InterruptedException {
        int nThread = 5;
        int taskCount = 100;
        CountDownLatch latch = new CountDownLatch(taskCount);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(nThread, nThread, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for(int i = 0; i < taskCount; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
            if(i < nThread) {
                //在未达到corePoolSize之前，会创建线程来执行对应任务
                assertEquals(i + 1, executor.getActiveCount());
            } else {
                //达到corePoolSize后，先往任务队列里面放
                assertEquals(nThread, executor.getActiveCount());
            }
        }

        latch.await();
    }
}
