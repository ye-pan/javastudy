package com.yp.designpatterns.enchantedMap;

import com.yp.designpatterns.map.Room;

public class EnchantedRoom extends Room {
    private Spell spell;
    public EnchantedRoom(int roomNumber, Spell spell) {
        super(roomNumber);
        this.spell = spell;
    }

    public Spell spell() {
        return spell;
    }
}
