package com.yp.refactor.after;

import java.util.Date;

public class DecomposeConditional {
    public static final Date SUMMER_START = new Date();

    public static final Date SUMMER_END = new Date();

    private double winterRate;

    private double winterServiceCharge;

    private double summerRate;

    public double sample(int quantity, Date date) {
        double charge;
        if(notSummer(date)) {
            charge = winterCharge(quantity);
        } else {
            charge = summerCharge(quantity);
        }
        return charge;
    }

    private double summerCharge(int quantity) {
        return quantity * summerRate;
    }

    private double winterCharge(int quantity) {
        return quantity * winterRate + winterServiceCharge;
    }

    private boolean notSummer(Date date) {
        return date.before(SUMMER_START) || date.after(SUMMER_END);
    }
}
