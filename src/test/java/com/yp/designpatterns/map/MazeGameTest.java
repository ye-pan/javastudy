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


    @Test
    public void testBuilder() {
        MazeBuilder builder = new StandardMazeBuilder();
        MazeGame game = new MazeGame();
        Maze maze = game.createMaze(builder);
        assertNotNull(maze.getRoom(1));
        assertNotNull(maze.getRoom(2));

        CountingMazeBuilder countingMazeBuilder = new CountingMazeBuilder();
        game.createMaze(countingMazeBuilder);
        assertTrue(countingMazeBuilder.getRooms() == 2);
        assertTrue(countingMazeBuilder.getDoors() == 1);
    }

}