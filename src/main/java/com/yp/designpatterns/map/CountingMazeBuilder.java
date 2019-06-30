package com.yp.designpatterns.map;


public class CountingMazeBuilder implements MazeBuilder {
    private int rooms;
    private int doors;
    public CountingMazeBuilder() {
        rooms = 0;
        doors = 0;
    }
    @Override
    public MazeBuilder buildMaze() {
        return this;
    }

    @Override
    public MazeBuilder buildRoom(int roomNo) {
        rooms++;
        return this;
    }

    @Override
    public MazeBuilder buildDoor(int roomFrom, int roomTo) {
        doors++;
        return this;
    }

    @Override
    public Maze getMaze() {
        return null;
    }

    public int getRooms() {
        return rooms;
    }

    public int getDoors() {
        return doors;
    }
}
