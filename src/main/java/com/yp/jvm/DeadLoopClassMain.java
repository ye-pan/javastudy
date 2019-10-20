package com.yp.jvm;

/**
 * 类加载时，执行类构造方法是单线程执行且只会执行一次，如果类构造器阻塞了，会造成线程死阻塞
 */
public class DeadLoopClassMain {
    static void deadLoadClass() throws InterruptedException {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();

        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        deadLoadClass();
    }

}

class DeadLoopClass {
    static {
        if (true) {
            System.out.println(Thread.currentThread() + " init DeadLoopClass");
            while (true) {

            }
        }
    }
}
