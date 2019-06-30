package com.yp.designpatterns.enchantedMap;

import com.yp.designpatterns.map.Door;
import com.yp.designpatterns.map.MazeGame;
import com.yp.designpatterns.map.Room;

public class EnchantedMazeGame extends MazeGame {
    @Override
    public Room makeRoom(int roomNo) {
        return new EnchantedRoom(roomNo, castSpell());
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return new DoorNeedingSpell(room1, room2);
    }

    private Spell castSpell() {
        return new Spell();
    }
}
