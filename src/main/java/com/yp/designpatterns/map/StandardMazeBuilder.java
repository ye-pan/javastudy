package com.yp.designpatterns.map;

import java.util.Random;

public class StandardMazeBuilder implements MazeBuilder {

    private Maze maze;


    @Override
    public MazeBuilder buildMaze() {
        maze = new Maze();
        return this;
    }

    @Override
    public MazeBuilder buildRoom(int roomNo) {
        if(!maze.containsRoom(roomNo)) {
            Room room = new Room(roomNo);
            maze.addRoom(room);

            room.setSide(Direction.NORTH, new Wall());
            room.setSide(Direction.EAST, new Wall());
            room.setSide(Direction.SOUTH, new Wall());
            room.setSide(Direction.WEST, new Wall());
        }
        return this;
    }

    @Override
    public MazeBuilder buildDoor(int roomFrom, int roomTo) {
        Room room1 = maze.getRoom(roomFrom);
        Room room2 = maze.getRoom(roomTo);
        Door door = new Door(room1, room2);

        Direction direction = commonWall(room1);
        if(direction != null) {
            room1.setSide(direction, door);
        }
        direction = commonWall(room2);
        if(direction != null) {
            room2.setSide(direction, door);
        }
        return this;
    }

    private Direction commonWall(Room room1) {
        for (Direction value : Direction.values()) {
            MapSite site = room1.getSide(value);
            if(site instanceof Door) {
                return null;
            }
        }

        Random random = new Random();
        int val = random.nextInt(4);
        for (Direction value : Direction.values()) {
            if(value.ordinal() == val) {
                return value;
            }
        }
        return null;
    }


    @Override
    public Maze getMaze() {
        return maze;
    }
}
