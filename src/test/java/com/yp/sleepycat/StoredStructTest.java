package com.yp.sleepycat;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.collections.StoredList;
import org.junit.Test;
import static org.junit.Assert.*;
public class StoredStructTest extends DatabaseTest {

    @Test
    public void testStoredList() {
        int size = 1000000;
        EntryBinding<Integer> binding = TupleBinding.getPrimitiveBinding(Integer.class);
        StoredList<Integer> list = new StoredList<>(database, binding, true);
        for(int i = 0; i < size; i++) {
            list.add(i);
        }

        assertEquals(size, list.size());
    }
}
