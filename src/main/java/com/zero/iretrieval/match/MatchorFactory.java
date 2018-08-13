package com.zero.iretrieval.match;

import com.zero.iretrieval.match.impl.CHI2DMatchor;
import com.zero.iretrieval.match.impl.LSHMacher;

public class MatchorFactory {
	private MatchorFactory() {}
	public static IMatchCalculator newMatchor(int type){
		switch (type) {
		case IMatchCalculator.LSH:
			return new LSHMacher();
		case IMatchCalculator.CHI2D:
			return new CHI2DMatchor();
		default:
			return null;
		}
	}
}
