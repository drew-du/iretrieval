package com.zero.iretrieval.engine;

import com.zero.iretrieval.core.Image;
import com.zero.iretrieval.feature.IFeature;
import com.zero.iretrieval.feature.IFeatureExtractor;
import com.zero.iretrieval.match.IMatchCalculator;

import java.util.List;

/**
 * ��������ӿڣ�����ȡ��������֯�ɲ�ͬ�����ݽṹ��ϲ�ͬ�㷨����߼������ܣ�������Ӧ��ʵ��
 */
public interface IRetrievalEngine {
	int DEFAULT_FEATURE = IFeature.COLOR_HSV;
	int DEFAULT_EXTRACTOR = IFeatureExtractor.COLOR;
	int DEFAULT_MATCHOR = IMatchCalculator.CHI2D;
	
	int ENGINE_SIMPLE_SORT = 0;
	int ENGINE_KDTREE = 1;
	
	int PARAM_FEATURE= 1;
	int PARAM_EXTRACTOR = 2;
	int PARAM_MATCHOR = 4;
	int PARAM_TOP_N = 3;
	void clean();
	void build();
	void loadFromURI(List<String> uriList);
	void loadFeature(List<IFeature> features);
	List<String> retrieval(Image image);
	void setParam(int name, int param);
	int getParam(int name);
}
