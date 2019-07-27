package com.yp.refactor.before;

public class InlineTemp {
    public boolean isFooPrice(Order order) {
        double basePrice = order.basePrice();
        return (basePrice > 100);
    }
}
