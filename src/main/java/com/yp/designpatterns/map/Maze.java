package com.yp.designpatterns.map;

import java.util.HashMap;
import java.util.Map;

public class Maze {

    private Map<Integer, Room> roomMap;

    public Maze() {
        roomMap = new HashMap<>();
    }
    void addRoom(Room room) {
        roomMap.put(room.roomNumber(), room);
    }

    Room getRoom(int roomNo) {
        return roomMap.get(roomNo);
    }
}
