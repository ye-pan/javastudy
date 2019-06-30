package com.yp.designpatterns.bombMap;

import com.yp.designpatterns.map.MazeFactory;
import com.yp.designpatterns.map.Room;
import com.yp.designpatterns.map.Wall;

public class BombedMazeFactory extends MazeFactory {
    @Override
    public Wall createWall() {
        return new BombedWall();
    }

    @Override
    public Room createRoom(int roomNo) {
        return new RoomWithABomb(roomNo);
    }

    public  static BombedMazeFactory getInstance() {
        return BombedMazeFactoryHolder.INSTANCE;
    }
    private static class BombedMazeFactoryHolder {
        static final BombedMazeFactory INSTANCE = new BombedMazeFactory();
    }
}
