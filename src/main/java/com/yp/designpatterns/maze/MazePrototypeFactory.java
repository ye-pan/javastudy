package com.yp.designpatterns.maze;

import java.util.ArrayList;
import java.util.List;

public class MazePrototypeFactory {
    private ClassBuilder<? extends Maze> mazeBuilder;
    private ClassBuilder<? extends Room> roomBuilder;
    private ClassBuilder<? extends Wall> wallBuilder;
    private ClassBuilder<? extends Door> doorBuilder;

    public MazePrototypeFactory(Class<? extends Maze> mazeClass, Class<? extends Room> roomClass,
                                Class<? extends Wall> wallClass, Class<? extends Door> doorClass) {
        this.mazeBuilder = new ClassBuilder<>(mazeClass);
        this.roomBuilder = new ClassBuilder<>(roomClass).argumentType(int.class);
        this.wallBuilder = new ClassBuilder<>(wallClass);
        this.doorBuilder = new ClassBuilder<>(doorClass).argumentType(Room.class).argumentType(Room.class);
    }
    public Maze makeMaze() {
        return mazeBuilder.build();
    }

    public Room makeRoom(int roomNo) {
        return roomBuilder.build(roomNo);
    }

    public Wall makeWall() {
        return wallBuilder.build();
    }

    public Door makeDoor(Room room1, Room room2) {
        return doorBuilder.build(room1, room2);
    }

    /**
     * 通过Java反射功能实例化一个功能，达到书中重写clone()方法的能力
     */
    private static class ClassBuilder<T> {
        private Class<T> klass;
        private List<Class<?>> typeList;

        ClassBuilder(Class<T> klass) {
            this.klass = klass;
            typeList = new ArrayList<>();
        }

        ClassBuilder<T> argumentType(Class<?> type) {
            typeList.add(type);
            return this;
        }

        T build(Object... arguments) {
            try {
                if (typeList.size() > 0 && arguments.length == typeList.size()) {
                    return klass.getDeclaredConstructor(typeList.toArray(new Class<?>[0])).newInstance(arguments);
                } else {
                    return klass.getDeclaredConstructor().newInstance();
                }
            } catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
