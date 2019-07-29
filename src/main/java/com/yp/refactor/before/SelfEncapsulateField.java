package com.yp.refactor.before;

public class SelfEncapsulateField {
    private int low;
    private int high;

    public SelfEncapsulateField(int low, int high) {
        this.low = low;
        this.high = high;
    }
    boolean includes(int arg) {
        return (arg >= low && arg <= high);
    }

    public void grow(int factor) {
        high = high * factor;
    }


}
