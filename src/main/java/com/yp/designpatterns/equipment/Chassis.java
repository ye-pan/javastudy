package com.yp.designpatterns.equipment;

/**
 * 底座
 */
public class Chassis extends CompositeEquipment {
    public Chassis(String name) {
        super(name);
    }

    @Override
    public Watt power() {
        Watt watt = super.power();
        return watt.add(new Watt(10));
    }

    @Override
    public Currency netPrice() {
        Currency currency = super.netPrice();
        return currency.add(new Currency(15));
    }

    @Override
    public Currency discountPrice() {
        Currency currency = super.discountPrice();
        return currency.add(new Currency(13));
    }
}
