package com.yp.ddia.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogDatabaseTest {

	@Test
	public void testSetAndGet() {
		Database<String, String> db = new LogDatabase<>();
		String key = "key";
		String value = "value";
		db.set(key, value);
		String v = db.get(key);
		assertEquals(value, v);
	}

}
