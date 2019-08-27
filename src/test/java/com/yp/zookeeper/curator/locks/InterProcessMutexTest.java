package com.yp.zookeeper.curator.locks;

import com.yp.zookeeper.curator.ZKSimpleTest;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class InterProcessMutexTest extends ZKSimpleTest {

    @Test
    public void test() throws InterruptedException {
        //TODO-yepan 测试出来有问题，需要排查代码
        InterProcessMutex mutex = new InterProcessMutex(client, path);
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