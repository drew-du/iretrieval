package com.zero.iretrieval.core;

/**
 * 提供所有距离、相似度的计算，
 * @author z
 *
 */
public class Distance {
	public static float norm_L0(float[] arg0, float[] arg1) {
		return 0;
	}

	public static float norm_L1(float[] arg0, float[] arg1) {
		return 0;
	}

	public static float norm_L2(float[] arg0, float[] arg1) {
		return 0;
	}

	public static float person(float[] p1, float[] p2) {
		check(p1, p2);
		float d = 0.0f;
		for (int i = 0; i < p1.length; i++)
			d += Math.pow(p1[i] - p2[i], 2) / (p1[i] + p2[i] + 1e-10);
		return d * 0.5f;
	}

	private static void check(float[] p1, float[] p2) {
		if (p1 == null || p2 == null || p1.length != p2.length)
			throw new RuntimeException("the param is null or different length");
	}
}
