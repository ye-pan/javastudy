package com.yp.sleepycat;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.tuple.IntegerBinding;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.collections.StoredList;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;
public class StoredStructTest {

    private String home = "F:\\data\\sleepycat";
    private Database database;

    @Before
    public void beforeEachMethod() {
        EnvironmentConfig config = new EnvironmentConfig();
        config.setAllowCreate(true);

        Environment environment = new Environment(new File(home), config);

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setAllowCreate(true);

        database = environment.openDatabase(null, "storedStructTest", databaseConfig);
    }

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
