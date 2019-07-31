package com.yp.refactor.before;

public class ConsolidateDuplicateConditionalFragments {
    public double sample(double price) {
        double total;
        if(isSpecialDeal()) {
            total = price * 0.95;
            send();
        } else {
            total = price * 0.98;
            send();
        }
        return total;
    }

    private void send() {

    }

    private boolean isSpecialDeal() {
        return false;
    }
}
