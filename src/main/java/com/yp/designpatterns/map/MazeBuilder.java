package com.yp.designpatterns.map;

/**
 * Builder
 */
public interface MazeBuilder {

    MazeBuilder buildMaze();

    MazeBuilder buildRoom(int roomNo);

    MazeBuilder buildDoor(int roomFrom, int roomTo);

    Maze getMaze();
}
