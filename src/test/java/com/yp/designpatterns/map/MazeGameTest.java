package com.yp.designpatterns.map;

import com.yp.designpatterns.bombMap.BombedMazeFactory;
import com.yp.designpatterns.bombMap.RoomWithABomb;
import com.yp.designpatterns.enchantedMap.EnchantedMazeFactory;
import com.yp.designpatterns.enchantedMap.EnchantedRoom;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeGameTest {

    @Test
    public void testMazeGame() {
        MazeGame game = new MazeGame();
        Maze maze = game.createMaze();
        assertNotNull(maze);
    }

    @Test
    public void testAbstractFactory() {
        MazeFactory factory = MazeFactory.getInstance();
        MazeGame game = new MazeGame();
        Maze maze = game.createMaze(factory);
        assertTrue(maze.getRoom(1) instanceof Room);
        assertTrue(maze.getRoom(2) instanceof Room);

        factory = EnchantedMazeFactory.getInstance();
        maze = game.createMaze(factory);
        assertTrue(maze.getRoom(1) instanceof EnchantedRoom);
        assertTrue(maze.getRoom(2) instanceof EnchantedRoom);


        factory = BombedMazeFactory.getInstance();
        maze = game.createMaze(factory);
        assertTrue(maze.getRoom(1) instanceof RoomWithABomb);
        assertTrue(maze.getRoom(2) instanceof RoomWithABomb);
    }

}