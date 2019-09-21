package com.yp.concurrent;

public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new ReaderThread();
        thread.start();
        number = 42;
        ready = true;
        thread.join();
    }
}
