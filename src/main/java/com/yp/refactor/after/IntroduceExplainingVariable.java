package com.yp.refactor.after;

public class IntroduceExplainingVariable {

    private int quantity;

    private double itemPrice;

    public double price() {
        final double basePrice = quantity * itemPrice;
        final double quantityDiscount = Math.max(0, quantity - 500) * itemPrice * 0.05;
        double shipping = Math.min(basePrice * 0.1, 100);
        return basePrice -
                quantityDiscount +
                shipping;
    }


    public double priceByExtractMethod() {
        return basePrice() -
                quantityDiscount() +
                shipping();
    }

    private double shipping() {
        return Math.min(basePrice() * 0.1, 100);
    }

    private double quantityDiscount() {
        return Math.max(0, quantity - 500) * itemPrice * 0.05;
    }

    private double basePrice() {
        return quantity * itemPrice;
    }
}
