package com.yp.designpatterns.equipment;

public class Card extends Equipment {
    public Card(String name) {
        super(name);
    }

    @Override
    public Watt power() {
        return new Watt(10);
    }

    @Override
    public Currency netPrice() {
        return new Currency(15);
    }

    @Override
    public Currency discountPrice() {
        return new Currency(13);
    }
}
