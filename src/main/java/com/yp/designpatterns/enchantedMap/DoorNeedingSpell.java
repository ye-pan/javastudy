package com.yp.designpatterns.enchantedMap;

import com.yp.designpatterns.map.Door;
import com.yp.designpatterns.map.Room;

public class DoorNeedingSpell extends Door {
    public DoorNeedingSpell(Room room, Room anotherRoom) {
        super(room, anotherRoom);
    }
}
