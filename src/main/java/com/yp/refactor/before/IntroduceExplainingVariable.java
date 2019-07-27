package com.yp.refactor.before;

public class IntroduceExplainingVariable {

    private int quantity;

    private double itemPrice;

    public double price() {
        return quantity * itemPrice -
                Math.max(0, quantity - 500) * itemPrice * 0.05 +
                Math.min(quantity * itemPrice * 0.1, 100);
    }
}
