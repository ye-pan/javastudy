package com.yp.designpatterns.maze;

public class Room implements MapSite {

    private final int roomNumber;

    private MapSite[] sides;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.sides = new MapSite[4];
    }

    MapSite getSide(Direction direction) {
        return sides[direction.ordinal()];
    }

    void setSide(Direction direction, MapSite side) {
        sides[direction.ordinal()] = side;
    }
    @Override
    public void enter() {

    }

    public int roomNumber() {
        return this.roomNumber;
    }
}
