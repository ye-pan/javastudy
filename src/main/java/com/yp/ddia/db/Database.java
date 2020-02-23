package com.yp.ddia.db;

public interface Database<K, V> {
	
	void set(K key, V value);
	
	V get(K key);
}
