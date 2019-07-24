package com.yp.refactor.after;

public class Rental {
    private Movie movie;
    private int daysRentend;

    public Rental(Movie movie, int daysRentend) {
        this.movie = movie;
        this.daysRentend = daysRentend;
    }

    public int getDaysRentend() {
        return daysRentend;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getCharge() {
        return movie.getCharge(getDaysRentend());
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(getDaysRentend());
    }
}
