package com.yp.designpatterns.maze.bomb;

import com.yp.designpatterns.maze.MazeGame;
import com.yp.designpatterns.maze.Room;
import com.yp.designpatterns.maze.Wall;

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
