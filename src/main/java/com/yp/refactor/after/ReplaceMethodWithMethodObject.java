package com.yp.refactor.after;

public class ReplaceMethodWithMethodObject {

    public int gamma(int inputVal, int quantity, int yearToDate) {
        return new Gamma(this, inputVal, quantity, yearToDate).compute();
    }

    int delta() {
        return 0;
    }
}
