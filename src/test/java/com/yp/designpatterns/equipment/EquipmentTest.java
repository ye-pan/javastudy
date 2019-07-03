package com.yp.designpatterns.equipment;

import org.junit.Test;

import static org.junit.Assert.*;

public class EquipmentTest {

    /**
     * 通过Composite模式实现了部分-整体这种组合对象结构。
     * 最终展现出来就是，使用组合对象和使用普通对象没有区别。
     * 但同时实现了组合对象的复杂功能。
     */
    @Test
    public void testCompositeEquipment() {
        Cabinet cabinet = new Cabinet("PC Cabinet");
        Chassis chassis = new Chassis("PC Chassis");

        cabinet.add(chassis);

        Bus bus = new Bus("MCA Bus");
        bus.add(new Card("16Mbs Token Ring"));
        chassis.add(bus);
        chassis.add(new FloppyDisk("3.5in Floppy"));

        assertEquals(chassis.netPrice(), new Currency(15).repeat(4));
        assertEquals(chassis.discountPrice(), new Currency(13).repeat(4));
        assertEquals(chassis.power(), new Watt(10).repeat(4));
    }
}