package com.yp.concurrent;

public class VariableVisibility {

    public static void main(String[] args) throws InterruptedException {
        final ShardObject shard = new ShardObject(1, "hello world");
        Thread t1 = new Thread(shard::cTask);
        Thread t2 = new Thread(shard::cTask);
        Thread t3 = new Thread(() -> {
            shard.change(2, "changed");
        });
        Thread t4 = new Thread(shard::cTask);
        Thread t5 = new Thread(shard::cTask);
        Thread t6 = new Thread(shard::cTask);
        Thread t7 = new Thread(shard::cTask);
        t1.start();
        t2.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t3.start();
    }

}

class ShardObject {
    private int i;
    private String str;
    private boolean isChange;

    ShardObject(int i, String str) {
        this.i = i;
        this.str = str;
        isChange = false;
    }

    public void cTask() {
        while (isNotChange()) {
            System.out.println(Thread.currentThread() + " " + this);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void change(int i, String str) {
        isChange = true;
        this.i = i;
        this.str = str;
        System.out.println("change value");
    }

    boolean isNotChange() {
        return !isChange;
    }

    @Override
    public String toString() {
        return "{i = " + i + ", str = " + str + "}";
    }
}
