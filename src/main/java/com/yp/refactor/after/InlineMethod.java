package com.yp.refactor.after;

public class InlineMethod {
    private int numberOfLateDeliveries;
    public int getRating() {
        return (numberOfLateDeliveries > 5) ? 2 : 1;
    }

}
