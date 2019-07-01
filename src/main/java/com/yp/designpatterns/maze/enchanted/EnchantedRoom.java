package com.yp.designpatterns.maze.enchanted;

import com.yp.designpatterns.maze.Room;

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
