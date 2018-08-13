package com.zero.iretrieval.match;

import com.zero.iretrieval.feature.IFeature;

/**
 * ����ƥ�����ӿ�
 * 
 * @author z
 *
 */
public interface IMatchCalculator {
	int LSH = 1;
	int CHI2D = 4;
	/**
	 * ������������ֵƥ�����
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	float score(IFeature f1, IFeature f2);
}
