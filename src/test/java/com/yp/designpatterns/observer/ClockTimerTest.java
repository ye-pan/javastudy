package com.yp.designpatterns.observer;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClockTimerTest {

    @Test
    public void testTick() {
        ClockTimer timer = new ClockTimer();
        PrintClock clock = new PrintClock(timer);
        timer.tick();
    }
}