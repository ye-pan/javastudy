package com.yp.designpatterns.maze.enchanted;

import com.yp.designpatterns.maze.Door;
import com.yp.designpatterns.maze.MazeGame;
import com.yp.designpatterns.maze.Room;

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
