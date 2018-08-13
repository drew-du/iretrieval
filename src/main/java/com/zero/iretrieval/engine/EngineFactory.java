package com.zero.iretrieval.engine;

import com.zero.iretrieval.engine.impl.KDTreeEngine;
import com.zero.iretrieval.engine.impl.SimpleSortEngine;

public class EngineFactory {
	private EngineFactory() {}
	public static IRetrievalEngine newEngine(int type) {
		switch (type) {
		case IRetrievalEngine.ENGINE_SIMPLE_SORT:
			return new SimpleSortEngine();
		case IRetrievalEngine.ENGINE_KDTREE:
			return new KDTreeEngine();
		default:
			return null;
		}
	}
}
