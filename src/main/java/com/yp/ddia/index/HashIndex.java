package com.yp.ddia.index;

import java.util.HashMap;
import java.util.Map;

public class HashIndex<K, I> implements Index<K, I> {
	private final Map<K, I> store;
	
	public HashIndex() {
		this.store = new HashMap<>();
	}
	
	@Override
	public void store(K key, I index) {
		store.put(key, index);
	}

	@Override
	public I get(K key) {
		return store.get(key);
	}
}
