package com.yp.sleepycat;

import com.sleepycat.je.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class DatabaseTest {

    protected Database database;

    private String home = "F:\\data\\sleepycat";

    protected Charset charset = StandardCharsets.UTF_8;

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
    public void testSave() {
        DatabaseEntry dbKey = new DatabaseEntry(charset.encode("key").array());
        DatabaseEntry dbValue = new DatabaseEntry(charset.encode("value").array());
        database.put(null, dbKey, dbValue);

        assertEquals(1, database.count());

    }
}
