package com.yp.designpatterns.equipment;

import java.math.BigDecimal;

public class Currency {
    private final BigDecimal amount;
    private final String currency;

    public Currency(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    public Currency(BigDecimal amount) {
        this.amount = amount;
        this.currency = "RMB";
    }

    public BigDecimal amount() {
        return this.amount;
    }

    public String currency() {
        return this.currency;
    }

    public Currency add(Currency netPrice) {
        return new Currency(amount.add(netPrice.amount));
    }

    public Currency repeat(int n) {
        return new Currency(amount.multiply(BigDecimal.valueOf(n)));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Currency)) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        return amount.equals(((Currency)obj).amount) && currency.equals(((Currency)obj).currency);
    }

    @Override
    public String toString() {
        return "[amount = " + amount + ", currency=" + currency + "]";
    }
}
