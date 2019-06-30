package com.yp.designpatterns.bombMap;

import com.yp.designpatterns.map.MazeGame;
import com.yp.designpatterns.map.Room;
import com.yp.designpatterns.map.Wall;

public class BombedMazeGame extends MazeGame {
    @Override
    public Wall makeWall() {
        return new BombedWall();
    }

    @Override
    public Room makeRoom(int roomNo) {
        return new RoomWithABomb(roomNo);
    }
}
