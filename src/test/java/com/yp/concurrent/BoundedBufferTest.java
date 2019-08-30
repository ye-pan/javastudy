package com.yp.concurrent;

import org.junit.Test;

import static org.junit.Assert.*;
public class BoundedBufferTest {

    @Test
    public void testPutAndTake() throws InterruptedException {
        BoundedBuffer<Integer> buf = new BoundedBuffer<>(5);
        Thread putThread = new Thread(() -> {
            for(int i = 0; i < 9999999; i++) {
                try {
                    buf.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread takeThread = new Thread(() -> {
            while(true) {
                try {
                    buf.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        takeThread.start();
        putThread.start();
        putThread.join();
    }
}