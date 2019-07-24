package com.yp.refactor.after;

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
        Enumeration<Rental> enumeration = rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while(enumeration.hasMoreElements()) {
            Rental each = enumeration.nextElement();
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }

        result += "Amount owed is " + String.valueOf(getTotalCharge())+ "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        for (Rental rental : rentals) {
            result += rental.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        for (Rental rental : rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }

    public String htmlStatement() {
        String result = "<H1> Rentals for <EM> " + getName() + "</EM></H1><P>\n";
        for (Rental rental : rentals) {
            result += rental.getMovie().getTitle() + ": " + rental.getCharge() + "<BR>\n";
        }
        result += "On this rental you earned <EM>" + getTotalFrequentRenterPoints() + "</EM> frequent renter points <P>";
        return result;
    }
}
