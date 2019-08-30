package com.yp.concurrent;

public class VolatileVariableIncrement {
    private volatile int val;

    public VolatileVariableIncrement() {
        val = 0;
    }

    public void increment() {
        val++;
    }

    public int get() {
        return val;
    }
}
