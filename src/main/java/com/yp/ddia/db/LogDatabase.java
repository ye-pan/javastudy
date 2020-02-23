package com.yp.ddia.db;

import java.util.ArrayList;
import java.util.List;

import com.yp.ddia.index.HashIndex;
import com.yp.ddia.index.Index;


public class LogDatabase<K, V> implements Database<K, V> {
	
	private class Item {
		final K key;
		final V value;
		
		Item(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private final List<Item> list;
	
	private final Index<K, Integer> keyIndex;

	public LogDatabase() {
		this.list = new ArrayList<>();
		this.keyIndex = new HashIndex<>();
	}
	
	@Override
	public void set(K key, V value) {
		list.add(new Item(key, value));
		int index = list.size() - 1;
		keyIndex.store(key, index);
	}

	@Override
	public V get(K key) {
		Integer index = keyIndex.get(key);
		if(index != null) {
			return list.get(index).value;
		} else {
			return null;
		}
	}
	
}
