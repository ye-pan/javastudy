package com.yp.refactor.after;

public class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    double getCharge(int daysRentend) {
        double result = 0;
        result += 1.5;
        if(daysRentend > 3) {
            result += (daysRentend - 3) * 1.5;
        }
        return result;
    }
}
