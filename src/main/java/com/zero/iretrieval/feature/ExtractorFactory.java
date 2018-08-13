package com.zero.iretrieval.feature;

import com.zero.iretrieval.feature.color.ColorFeatureExtractor;

public class ExtractorFactory {
	private ExtractorFactory() {
	}

	public static IFeatureExtractor newExtractor(int type, int... param) {
		switch (type) {
		case IFeatureExtractor.COLOR:
			return new ColorFeatureExtractor();
		default:
			return null;
		}
	}
}
