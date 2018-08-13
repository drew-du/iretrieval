package com.zero.iretrieval.feature;

import com.zero.iretrieval.core.Image;

/**
 * 特征提取器接口，根据特征的不同类型，可以实现不同的提取器
 * 
 * @author z
 *
 */
public interface IFeatureExtractor {
	int COLOR = 0x0001;
	int EMOTION = 0x0004;
	int SHAPE = 0x0007;
	int TEXTURE = 0x00010;
	/**
	 * 
	 * @param img
	 * @return
	 */
	IFeature extract(Image img, int type);
}
