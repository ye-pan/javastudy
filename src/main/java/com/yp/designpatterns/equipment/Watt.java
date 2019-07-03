package com.yp.designpatterns.equipment;

public class Watt {
    private final int watt;

    public Watt(int watt) {
        this.watt = watt;
    }

    public int watt() {
        return watt;
    }

    public Watt add(Watt power) {
        return new Watt(this.watt + power.watt());
    }

    public Watt repeat(int i) {
        return new Watt(watt * i);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof  Watt)) {
            return false;
        }
        if(obj == this) {
            return true;
        }

        return watt == ((Watt)obj).watt;
    }

    @Override
    public String toString() {
        return "[watt=" + watt + "]";
    }
}
