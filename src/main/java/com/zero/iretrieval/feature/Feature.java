package com.zero.iretrieval.feature;

import java.util.Arrays;

public class Feature implements IFeature {
	private final String id;
	private static int type;
	private final float[] value;
	
	public Feature(String id, float[] value) {
		this.id = id;
		this.value = value;
	}
	public static void setType(int type) {
		Feature.type = type;
	}
	
	public String id() {
		return id;
	}

	public int type() {
		return type;
	}

	public float[] value() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("%s:%s", id,  Arrays.toString(value));
	}
}
