package com.yp.designpatterns.map;

public class Door implements MapSite {
    private Room room;
    private Room anotherRoom;
    private boolean isOpen;

    public Door(Room room, Room anotherRoom) {
        this.room = room;
        this.anotherRoom = anotherRoom;
    }
    @Override
    public void enter() {

    }

    public Room otherSideFrom(Room room) {
        if(room == this.room) {
            return anotherRoom;
        } else {
            return this.room;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }
}
