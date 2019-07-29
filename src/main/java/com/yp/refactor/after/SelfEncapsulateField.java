package com.yp.refactor.after;

public class SelfEncapsulateField {
    private int low;
    private int high;

    public SelfEncapsulateField(int low, int high) {
        this.setLow(low);
        this.setHigh(high);
    }
    boolean includes(int arg) {
        return (arg >= getLow() && arg <= getHigh());
    }

    public void grow(int factor) {
        setHigh(getHigh() * factor);
    }


    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }
}
