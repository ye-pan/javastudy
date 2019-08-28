package com.yp.zookeeper.curator.locks;

import com.yp.zookeeper.curator.ZKSimpleTest;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class InterProcessMutexTest extends ZKSimpleTest {

    @Test
    public void test() throws Exception {
        String basePath = "/zkbook/interprocessmutex";
        client.create().creatingParentsIfNeeded().forPath(basePath);
        InterProcessMutex mutex = new InterProcessMutex(client, basePath);
        int threads = 30;
        CountDownLatch latch = new CountDownLatch(30);
        CountDownLatch await = new CountDownLatch(1);
        for(int i = 0; i < threads; i++) {
            new Thread(() -> {
                try {
                    await.await();
                    mutex.acquire(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String orderNo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss|SSS"));
                System.out.println("生成订单号是: " + orderNo);
                try {
                    mutex.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }).start();
        }
        await.countDown();
        latch.await();
    }
}