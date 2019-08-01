package com.yp.refactor.after;

import com.yp.refactor.before.Entry;

import java.util.List;

public class Account {

    private AccountType type;

    private int daysOverdrawn;

    private List<Entry> entries;

    public double bankCharge() {
        double result = 4.5;
        if(daysOverdrawn > 0) {
            result += type.overdraftCharge(daysOverdrawn);
        }
        return result;
    }

    public double interestForAccountDays(double amount, int days) {
        return getInterestRate() * amount * days / 365;
    }

    public double getInterestRate() {
        return type.getInterestRate();
    }

    public void setInterestRate(double interestRate) {
        type.setInterestRate(interestRate);
    }

    public double getFlowBetween(DateRange range) {
        double result = 0;
        for (Entry entry : entries) {
            if(range.includes(entry.getDate())) {
                result += entry.getValue();
            }
        }
        return result;
    }
}
