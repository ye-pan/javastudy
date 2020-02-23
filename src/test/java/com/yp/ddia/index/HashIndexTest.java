package com.yp.ddia.index;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashIndexTest {

	@Test
	public void testStoreAndGet() {
		Index<String, Integer> i = new HashIndex<>();
		String key = "key";
		Integer index = 1;
		i.store(key, index);
		assertEquals(index, i.get(key));
	}
}
