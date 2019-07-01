package com.yp.designpatterns.maze;

import com.yp.designpatterns.maze.bomb.BombedMazeFactory;
import com.yp.designpatterns.maze.bomb.BombedMazeGame;
import com.yp.designpatterns.maze.bomb.BombedWall;
import com.yp.designpatterns.maze.bomb.RoomWithABomb;
import com.yp.designpatterns.maze.enchanted.EnchantedMazeFactory;
import com.yp.designpatterns.maze.enchanted.EnchantedMazeGame;
import com.yp.designpatterns.maze.enchanted.EnchantedRoom;
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

    @Test
    public void testFactoryMethod() {
        MazeGame game = new MazeGame();
        Maze maze = game.createMaze();
        assertTrue(maze.getRoom(1) instanceof Room);
        assertTrue(maze.getRoom(2) instanceof Room);

        game = new BombedMazeGame();
        maze = game.createMaze();
        assertTrue(maze.getRoom(1) instanceof RoomWithABomb);
        assertTrue(maze.getRoom(2) instanceof RoomWithABomb);

        game = new EnchantedMazeGame();
        maze = game.createMaze();
        assertTrue(maze.getRoom(1) instanceof EnchantedRoom);
        assertTrue(maze.getRoom(2) instanceof EnchantedRoom);
    }


    @Test
    public void testPrototypeFactory() {
        MazeGame game = new MazeGame();
        MazePrototypeFactory factory = new MazePrototypeFactory(Maze.class, Room.class, Wall.class, Door.class);
        Maze maze = game.createMaze(factory);
        assertNotNull(maze);

        factory = new MazePrototypeFactory(Maze.class, RoomWithABomb.class, BombedWall.class, Door.class);
        maze = game.createMaze(factory);
        assertNotNull(maze);
    }
}