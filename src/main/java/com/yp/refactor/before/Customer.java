package com.yp.refactor.before;

import java.util.Enumeration;
import java.util.Vector;

class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration<Rental> enumeration = rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while(enumeration.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = enumeration.nextElement();
            switch(each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if(each.getDaysRentend() > 2) {
                        thisAmount += (each.getDaysRentend() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRentend() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if(each.getDaysRentend() > 3) {
                        thisAmount += (each.getDaysRentend() - 3) * 1.5;
                    }
                    break;
            }

            frequentRenterPoints ++;
            if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRentend() > 1) {
                frequentRenterPoints ++;
            }
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is " + String.valueOf(totalAmount)+ "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
}
