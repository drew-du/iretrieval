package com.zero.iretrieval.core;

import java.util.HashMap;
import java.util.Map;

public class Param {
	private Map<Integer, Integer> kv;
	public Param() {
		kv = new HashMap<Integer, Integer>();
	}
	
	public static Param instance() {
		return new Param();
	}
	
	public Param set(int key, Integer value) {
		kv.put(key, value);
		return this;
	}
	
	public Integer get(int key) {
		return kv.get(key);
	}
}
