package com.yp.designpatterns.enchantedMap;

import com.yp.designpatterns.map.Door;
import com.yp.designpatterns.map.MazeFactory;
import com.yp.designpatterns.map.Room;

/**
 * 该类和{@link MazeFactory}一起展示了Abstract Factory抽象了对象创建部分，体现Abstract Factory是如何应对创建对象变化的情况。
 */
public class EnchantedMazeFactory extends MazeFactory {
    @Override
    public Room createRoom(int roomNo) {
        return new EnchantedRoom(roomNo, castSpell());
    }

    @Override
    public Door createDoor(Room room1, Room room2) {
        return new DoorNeedingSpell(room1, room2);
    }

    private Spell castSpell() {
        return new Spell();
    }

    public static EnchantedMazeFactory getInstance() {
        return EnchantedMazeFactoryHolder.INSTANCE;
    }

    private static class EnchantedMazeFactoryHolder {
        static final EnchantedMazeFactory INSTANCE = new EnchantedMazeFactory();
    }
}
