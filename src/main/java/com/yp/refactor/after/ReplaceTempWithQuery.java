package com.yp.refactor.after;

public class ReplaceTempWithQuery {

    private int quantity;

    private double itemPrice;

    public double getPrice() {
        return basePrice() * discountFactor();
    }

    private double discountFactor() {
        double discountFactor;
        if(basePrice() > 100) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return discountFactor;
    }

    private double basePrice() {
        return quantity * itemPrice;
    }
}
