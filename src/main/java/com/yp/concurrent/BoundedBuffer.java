package com.yp.concurrent;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public BoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws InterruptedException {
        while(isFull()) {
            System.out.println("put wait......");
            wait();
        }
        doPut(v);
        notifyAll();
    }

   public synchronized V take() throws InterruptedException {
        while(isEmpty()) {
            System.out.println("take wait.....");
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
   }
}
