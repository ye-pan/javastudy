package com.yp.tdd;

public class Money implements Expression{
    protected int amount;
    private String currency;

    public Money(int amout, String currency) {
        this.amount = amout;
        this.currency = currency;
    }

    public static Money dollar(int amout) {
        return new Money(amout, "USD");
    }

    public static Money franc(int amout) {
        return new Money(amout, "CHF");
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && currency.equals(money.currency);
    }

    public String currency() {
        return currency;
    }

    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency());
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public String toString() {
        return amount + ", " + currency;
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

}
