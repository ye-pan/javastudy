package com.yp.designpatterns.maze;

/**
 * Abstract Factory
 */
public class MazeFactory {

    public Maze createMaze() {
        return new Maze();
    }

    public Wall createWall() {
        return new Wall();
    }

    public Room createRoom(int roomNo) {
        return new Room(roomNo);
    }

    public Door createDoor(Room room1, Room room2) {
        return new Door(room1, room2);
    }

    public static MazeFactory getInstance() {
        return MazeFactoryHolder.INSTANCE;
    }

    private static class MazeFactoryHolder {
        static final MazeFactory INSTANCE = new MazeFactory();
    }
}
