package com.yp.refactor.after;

import com.yp.refactor.before.Order;

public class InlineTemp {
    public boolean isFooPrice(Order order) {
        return (order.basePrice() > 100);
    }
}
