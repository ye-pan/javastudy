package com.yp.designpatterns.map;

public class MazeGame {

    /**
     * 硬编码组装迷宫基本对象
     * @return
     */
    public Maze createMaze() {
        Maze maze = new Maze();
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Door theDoor = new Door(room1, room2);

        maze.addRoom(room1);
        maze.addRoom(room2);

        room1.setSide(Direction.NORTH, new Wall());
        room1.setSide(Direction.EAST, theDoor);
        room1.setSide(Direction.SOUTH, new Wall());
        room1.setSide(Direction.WEST, new Wall());

        room2.setSide(Direction.NORTH, new Wall());
        room2.setSide(Direction.EAST, new Wall());
        room2.setSide(Direction.SOUTH, new Wall());
        room2.setSide(Direction.WEST, theDoor);

        return maze;
    }

    /**
     * 抽象工厂组装迷宫基本对象
     * @param factory 抽象工厂类
     * @return
     */
    public Maze createMaze(MazeFactory factory) {
        Maze maze = factory.createMaze();
        Room r1 = factory.createRoom(1);
        Room r2 = factory.createRoom(2);
        Door door = factory.createDoor(r1, r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide(Direction.NORTH, factory.createWall());
        r1.setSide(Direction.EAST, door);
        r1.setSide(Direction.SOUTH, factory.createWall());
        r1.setSide(Direction.WEST, factory.createWall());

        r2.setSide(Direction.NORTH, factory.createWall());
        r2.setSide(Direction.EAST, factory.createWall());
        r2.setSide(Direction.SOUTH, factory.createWall());
        r2.setSide(Direction.WEST, door);

        return maze;
    }
}
