package com.yp.refactor.after;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int priceCode) {
        switch(priceCode) {
            case CHILDRENS:
                price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case REGULAR:
                price = new RegularPrice();
                break;
                default:
                    throw new IllegalArgumentException("" + priceCode);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    double getCharge(int daysRentend) {
        return price.getCharge(daysRentend);
    }

    int getFrequentRenterPoints(int daysRentend) {
        return price.getFrequentRenterPoints(daysRentend);
    }

}
