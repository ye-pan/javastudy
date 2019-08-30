package com.yp.concurrent;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Runs {
    public static void run(Runnable run, int n) {
        CountDownLatch wait = new CountDownLatch(1);
        CountDownLatch latch = new CountDownLatch(n);
        for(int i = 0; i < n; i++) {
            new Thread(() -> {
                try {
                    wait.await();
                    run.run();
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        wait.countDown();
        try {
            latch.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void run(List<Runnable> runs) {
        Preconditions.checkNotNull(runs);
        Preconditions.checkArgument(runs.size() > 0);

        int n = runs.size();
        CountDownLatch wait = new CountDownLatch(1);
        CountDownLatch latch = new CountDownLatch(n);
        for(int i = 0; i < n; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    wait.await();
                    runs.get(index).run();
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        wait.countDown();
        try {
            latch.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
