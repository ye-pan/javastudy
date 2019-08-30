package com.yp.concurrent;

import org.junit.Test;

import static org.junit.Assert.*;

public class VolatileVariableIncrementTest {

    @Test
    public void testVolatileIncrement() {
        //这里并发操作volatile修饰的变量，用例会随机失败
        VolatileVariableIncrement val = new VolatileVariableIncrement();
        Runs.run(val::increment, 5);
        assertEquals(5, val.get());
    }
}