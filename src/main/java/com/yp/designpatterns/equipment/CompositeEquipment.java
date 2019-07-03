package com.yp.designpatterns.equipment;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CompositeEquipment extends Equipment {
    private List<Equipment> equipmentList;
    protected CompositeEquipment(String name) {
        super(name);
        this.equipmentList = new LinkedList<>();
    }

    @Override
    public Watt power() {
        Iterator<Equipment> iterator = createIterator();
        Watt watt = new Watt(0);
        while(iterator.hasNext()) {
            watt = watt.add(iterator.next().power());
        }
        return watt;
    }

    @Override
    public Currency netPrice() {
        Iterator<Equipment> iterator = createIterator();
        Currency currency = new Currency(0);
        while(iterator.hasNext()) {
            currency = currency.add(iterator.next().netPrice());
        }
        return currency;
    }

    @Override
    public Currency discountPrice() {
        Iterator<Equipment> iterator = createIterator();
        Currency currency = new Currency(0);
        while(iterator.hasNext()) {
            currency = currency.add(iterator.next().discountPrice());
        }
        return currency;
    }

    @Override
    public void add(Equipment equipment) {
        equipmentList.add(equipment);
    }

    @Override
    public void remove(Equipment equipment) {
        equipmentList.remove(equipment);
    }

    @Override
    public Iterator<Equipment> createIterator() {
        return equipmentList.iterator();
    }
}
