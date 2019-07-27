package com.yp.refactor.before;

public class ReplaceTempWithQuery {

    private int quantity;

    private double itemPrice;

    public double getPrice() {
        double basePrice = quantity * itemPrice;
        double discountFactor;
        if(basePrice > 100) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }
}
