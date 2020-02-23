package com.yp.ddia.index;

public interface Index<K, I> {
	void store(K key, I index);
	I get(K key);
}
