package com.yp.designpatterns.maze;

public class MazeGame {

    /**
     * 工厂方法组装迷宫基本对象
     * @return
     */
    public Maze createMaze() {
        Maze maze = makeMaze();
        Room room1 = makeRoom(1);
        Room room2 = makeRoom(2);
        Door theDoor = makeDoor(room1, room2);

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


    public Maze makeMaze() {
        return new Maze();
    }

    public Room makeRoom(int roomNo) {
        return new Room(roomNo);
    }

    public Wall makeWall() {
        return new Wall();
    }

    public Door makeDoor(Room room1, Room room2) {
        return new Door(room1, room2);
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

    /**
     * 生成器组装迷宫基本对象
     * @param builder
     * @return
     */
    Maze createMaze(MazeBuilder builder) {
        return builder.buildMaze()
                .buildRoom(1)
                .buildRoom(2)
                .buildDoor(1, 2)
                .getMaze();
    }

    /**
     * 原型组装迷宫基本对象
     * @param factory
     * @return
     */
    Maze createMaze(MazePrototypeFactory factory) {
        Maze maze = factory.makeMaze();
        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);
        Door door = factory.makeDoor(r1, r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide(Direction.NORTH, factory.makeWall());
        r1.setSide(Direction.EAST, door);
        r1.setSide(Direction.SOUTH, factory.makeWall());
        r1.setSide(Direction.WEST, factory.makeWall());

        r2.setSide(Direction.NORTH, factory.makeWall());
        r2.setSide(Direction.EAST, factory.makeWall());
        r2.setSide(Direction.SOUTH, factory.makeWall());
        r2.setSide(Direction.WEST, door);
        return maze;
    }
}
