package com.yp.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> list;

    public Subject() {
        list = new ArrayList<>();
    }
    public void attach(Observer observer) {
        list.add(observer);
    }

    public void detach(Observer observer) {
        list.remove(observer);
    }

    public void doNotify() {
        list.forEach(observer -> observer.update(this));
    }
}
