package com.yp.designpatterns.observer;

public class PrintClock implements Observer {
    private ClockTimer timer;
    public PrintClock(ClockTimer timer) {
        this.timer = timer;
        timer.attach(this);
    }
    public void destory() {
        timer.detach(this);
    }
    @Override
    public void update(Subject subject) {
        ClockTimer timer = (ClockTimer)subject;
        System.out.println(timer.getHour() + ":" + timer.getMinute() + ":" + timer.getSecond());
    }
}
