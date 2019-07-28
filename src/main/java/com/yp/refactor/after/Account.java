package com.yp.refactor.after;

public class Account {

    private AccountType type;

    private int daysOverdrawn;

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
}
