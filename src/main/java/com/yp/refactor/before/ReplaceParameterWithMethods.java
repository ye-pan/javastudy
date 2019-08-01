package com.yp.refactor.before;

public class ReplaceParameterWithMethods {

    private int quantity;

    private double itemPrice;

    public double getPrice() {
        double basePrice = quantity * itemPrice;
        int discountLevel;
        if(quantity > 100) {
            discountLevel = 2;
        } else {
            discountLevel = 1;
        }
        return discountedPrice(basePrice, discountLevel);
    }

    private double discountedPrice(double basePrice, int discountLevel) {
        if(discountLevel == 2) {
            return basePrice * 0.1;
        } else {
            return basePrice * 0.05;
        }
    }
}
