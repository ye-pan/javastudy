package com.yp.algorithm;

import org.bouncycastle.util.Strings;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;
public class IPWhiteListTest {

    @Test
    public void testAddWhiteIpAddress() {
        IPWhiteList ipWhiteList = new IPWhiteList();
        String ip = "192.168.1.1";
        boolean val = ipWhiteList.addWhiteIpAddress(ip);
        assertTrue(val);
    }


    @Test
    public void testIsWhiteIpAddress() {
        IPWhiteList ipWhiteList = new IPWhiteList();
        String ip = "192.168.1.1";
        boolean val = ipWhiteList.addWhiteIpAddress(ip);
        assertTrue(val);
        boolean exists = ipWhiteList.isWhiteIpAddress(ip);
        assertTrue(exists);
        exists = ipWhiteList.isWhiteIpAddress("192.168.1.2");
        assertFalse(exists);
    }

    @Test
    public void testConcurrentAddWhiteIpAddress() {
        CountDownLatch await = new CountDownLatch(2);
        IPWhiteList ipWhiteList = new IPWhiteList();
        Thread thread = new Thread(()->{
            try {
                for (int i = 0; i <= 255; i++) {
                    for (int j = 0; j <= 255; j++) {
                        ipWhiteList.addWhiteIpAddress("192.168." + i + "." + j);
                    }
                }
            } finally {
                await.countDown();
            }
        });

        Thread thread2 = new Thread(()->{
            try {
                for (int i = 0; i <= 255; i++) {
                    for (int j = 0; j <= 255; j++) {
                        ipWhiteList.addWhiteIpAddress(i + "." + j + "1.1");
                    }
                }
            } finally {
                await.countDown();
            }
        });
        thread.start();
        thread2.start();

        try {
            await.await();
        } catch (InterruptedException ignore) {
        }

        assertEquals(256 * 256 * 2, ipWhiteList.size());
    }

    @Test
    public void testWholeAddIpAddress() {
        IPWhiteList ipWhiteList = new IPWhiteList();
        StopWatch time = new StopWatch();
        time.start("整个IP段");
        for(int ip2 = 0; ip2 <= 255; ip2++) {
            for(int ip3 = 0; ip3 <= 255; ip3++) {
                for(int ip4 = 0; ip4 <= 255; ip4++) {
                    ipWhiteList.addWhiteIpAddress("192." + ip2 + "." + ip3 + "." + ip4);
                }
            }
        }
        time.stop();
        System.out.println(time.prettyPrint());

        assertEquals(256 * 256 * 256, ipWhiteList.size());
    }
}
