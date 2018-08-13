package com.zero.iretrieval.match;

import com.zero.iretrieval.feature.IFeature;

/**
 * 特征匹配计算接口
 * 
 * @author z
 *
 */
public interface IMatchCalculator {
	int LSH = 1;
	int CHI2D = 4;
	/**
	 * 计算两个特征值匹配分数
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	float score(IFeature f1, IFeature f2);
}
