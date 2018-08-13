package com.zero.iretrieval.core;

public class L {
	public static boolean debug = true;
	public static boolean save_feature = false;

	public static void log(Object obj) {
		if (debug)
			System.out.println(obj.toString());
	}

	static long time = System.currentTimeMillis();

	public static void logStart(Object obj, String msg) {
		time = System.currentTimeMillis();
		if (debug)
			System.out.println(String.format("=== %s === %s ===", obj.getClass(), msg));
	}

	public static void logEnd(Object obj, String msg) {
		if (debug)
			System.out.println(String.format("=== %s === %s ===used time:%s===", obj.getClass(), msg,
					System.currentTimeMillis() - time));
	}

	public static void log(Object obj, String msg) {
		if (debug)
			System.out.println(String.format("=== %s === %s ===", obj, msg));
	}
}
