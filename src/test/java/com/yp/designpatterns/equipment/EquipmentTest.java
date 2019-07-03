package com.yp.designpatterns.equipment;

import org.junit.Test;

import static org.junit.Assert.*;

public class EquipmentTest {

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