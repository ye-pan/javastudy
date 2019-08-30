package com.yp.concurrent;

public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    protected GrumpyBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) {
        if(isFull()) {
            throw new BufferFullException();
        }
        doPut(v);
    }

    public synchronized V take() {
        if(isEmpty()) {
            throw new BufferEmptyException();
        }
        return doTake();
    }
}
