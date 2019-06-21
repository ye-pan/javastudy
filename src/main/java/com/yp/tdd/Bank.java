package com.yp.tdd;

import java.util.HashMap;

public class Bank {
    private HashMap<Pair, Integer> rates = new HashMap<>();

    public int rate(String from, String to) {
        if(from.equals(to)) {
            return 1;
        }
        return rates.get(new Pair(from, to));
    }

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public void addRate(String from, String to, int i) {
        rates.put(new Pair(from, to), i);
    }
}
