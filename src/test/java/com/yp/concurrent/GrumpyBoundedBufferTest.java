package com.yp.concurrent;

import org.junit.Test;

import static org.junit.Assert.*;

public class GrumpyBoundedBufferTest {

    @Test(expected = BufferFullException.class)
    public void testPut() {
        GrumpyBoundedBuffer<Integer> buf = new GrumpyBoundedBuffer<>(5);
        while(true) {
            buf.put(1);
        }
    }

    @Test(expected = BufferEmptyException.class)
    public void testTake() {
        int size = 5;
        GrumpyBoundedBuffer<Integer> buf = new GrumpyBoundedBuffer<>(size);
        for(int i = 0; i < size; i++) {
            buf.put(i);
        }
        while(true) {
            buf.take();
        }
    }
}