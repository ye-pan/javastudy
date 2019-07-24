package com.yp.refactor.after;

public class RegularPrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    double getCharge(int daysRentend) {
        double result = 0;
        result += 2;
        if(daysRentend > 2) {
            result += (daysRentend - 2) * 1.5;
        }
        return result;
    }
}
