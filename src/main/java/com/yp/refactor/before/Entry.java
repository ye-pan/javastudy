package com.yp.refactor.before;

import java.util.Date;

public class Entry {
    private final Date chargeDate;

    private final double value;

    Entry(double value, Date chargeDate) {
        this.value = value;
        this.chargeDate = chargeDate;
    }

    public Date getDate() {
        return chargeDate;
    }

    public double getValue() {
        return value;
    }
}
