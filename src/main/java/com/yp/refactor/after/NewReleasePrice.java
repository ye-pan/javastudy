package com.yp.refactor.after;

public class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRentend) {
        return daysRentend * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRentend) {
        return (daysRentend > 1) ? 2 : 1;
    }
}
