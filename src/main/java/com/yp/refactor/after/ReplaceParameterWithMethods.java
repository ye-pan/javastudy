package com.yp.refactor.after;

public class ReplaceParameterWithMethods {

    private int quantity;

    private double itemPrice;

    public double getPrice() {
        if(getDiscountLevel() == 2) {
            return getBasePrice() * 0.1;
        } else {
            return getBasePrice() * 0.05;
        }
    }

    private double getBasePrice() {
        return quantity * itemPrice;
    }

    private int getDiscountLevel() {
        if(quantity > 100) {
            return 2;
        } else {
            return 1;
        }
    }

}
