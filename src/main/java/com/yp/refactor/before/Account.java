package com.yp.refactor.before;

import java.util.Date;
import java.util.List;

public class Account {

    private AccountType type;

    private int daysOverdrawn;

    private double interestRate;

    private List<Entry> entries;

    public double overdraftCharge() {
        if(type.isPremium()) {
            double result = 10;
            if(daysOverdrawn > 7) {
                result += (daysOverdrawn -  7) * 0.85;
            }
            return result;
        } else {
            return daysOverdrawn * 1.75;
        }
    }

    public double bankCharge() {
        double result = 4.5;
        if(daysOverdrawn > 0) {
            result += overdraftCharge();
        }
        return result;
    }

    public double interestForAccountDays(double amount, int days) {
        return interestRate * amount * days / 365;
    }

    public double getFlowBetween(Date start, Date end) {
        double result = 0;
        for (Entry entry : entries) {
            if(entry.getDate().equals(start) ||
                    entry.getDate().equals(end) ||
                    (entry.getDate().after(start) && entry.getDate().before(end))) {
                result += entry.getValue();
            }
        }
        return result;
    }
}
