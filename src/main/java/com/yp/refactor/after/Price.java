package com.yp.refactor.after;

public abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRentend);

    int getFrequentRenterPoints(int daysRentend) {
        return 1;
    }
}
