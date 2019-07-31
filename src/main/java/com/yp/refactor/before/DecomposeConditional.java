package com.yp.refactor.before;

import java.util.Date;

public class DecomposeConditional {
    public static final Date SUMMER_START = new Date();

    public static final Date SUMMER_END = new Date();

    private double winterRate;

    private double winterServiceCharge;

    private double summerRate;

    public double sample(int quantity, Date date) {
        double charge = 0;
        if(date.before(SUMMER_START) || date.after(SUMMER_END)) {
            charge = quantity * winterRate + winterServiceCharge;
        } else {
            charge = quantity * summerRate;
        }
        return charge;
    }
}
