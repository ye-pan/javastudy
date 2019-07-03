package com.yp.designpatterns.equipment;

import java.util.Iterator;

public class Equipment {
    private String name;

    protected Equipment(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Watt power() {
        return new Watt(0);
    }

    public Currency netPrice() {
        return new Currency(0);
    }

    public void add(Equipment equipment) {
        throw new UnsupportedOperationException();
    }

    public void remove(Equipment equipment) {
        throw new UnsupportedOperationException();
    }

    public Currency discountPrice() {
        return new Currency(0);
    }

    public Iterator<Equipment> createIterator() {
        throw new UnsupportedOperationException();
    }
}
