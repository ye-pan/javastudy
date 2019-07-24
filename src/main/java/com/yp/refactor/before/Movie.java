package com.yp.refactor.before;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    double getCharge(int daysRentend) {
        double result = 0;
        switch(getPriceCode()) {
            case REGULAR:
                result += 2;
                if(daysRentend > 2) {
                    result += (daysRentend - 2) * 1.5;
                }
                break;
            case NEW_RELEASE:
                result += daysRentend * 3;
                break;
            case CHILDRENS:
                result += 1.5;
                if(daysRentend > 3) {
                    result += (daysRentend - 3) * 1.5;
                }
                break;
        }
        return result;
    }

    int getFrequentRenterPoints(int daysRentend) {
        if((getPriceCode() == NEW_RELEASE) && daysRentend > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
