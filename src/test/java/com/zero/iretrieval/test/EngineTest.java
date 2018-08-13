package com.zero.iretrieval.test;

import com.zero.iretrieval.core.Image;
import com.zero.iretrieval.core.L;
import com.zero.iretrieval.core.Util;
import com.zero.iretrieval.engine.EngineFactory;
import com.zero.iretrieval.engine.IRetrievalEngine;
import com.zero.iretrieval.feature.IFeature;
import com.zero.iretrieval.feature.IFeatureExtractor;
import com.zero.iretrieval.match.IMatchCalculator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EngineTest {

	static String input_path = "E:\\data\\image\\statesman\\";
	static String output_path = "E:\\data\\image\\output\\";
	static File path = new File(input_path);
	static List<String> urlList = new ArrayList<String>();
	static List<IFeature> features = Util.readFeatures();
	static {
		for (String name : path.list())
			urlList.add(input_path + name);
	}
	
	public static void main(String[] args) {
		Util.clean(output_path);//������Ŀ¼
		long t1 = System.currentTimeMillis();
		IRetrievalEngine engine = EngineFactory.newEngine(IRetrievalEngine.ENGINE_SIMPLE_SORT);//��������
		engine.setParam(IRetrievalEngine.PARAM_FEATURE, IFeature.COLOR_HSV);//����ѡ�õ�����
		engine.setParam(IRetrievalEngine.PARAM_EXTRACTOR, IFeatureExtractor.COLOR);//����������ȡ��
		engine.setParam(IRetrievalEngine.PARAM_MATCHOR, IMatchCalculator.CHI2D);//���������ȶԷ���
		engine.setParam(IRetrievalEngine.PARAM_TOP_N, 3);//���ü�������
		engine.build();
		
		long t2 = System.currentTimeMillis();
		if(features.isEmpty())
			engine.loadFromURI(urlList);//��ͼƬ��ַ��ȡ�ͼ�������
		else
			engine.loadFeature(features);//ֱ�Ӽ�������
		long t3 = System.currentTimeMillis();
		
		List<String> result = engine.retrieval(new Image("E:\\data\\image\\statesman\\dxp_7.jpg"));
		
		long t4 = System.currentTimeMillis();
		System.out.println(result);
		Util.output(input_path, output_path, result);
		long t5 = System.currentTimeMillis();
		L.log(String.format("����������ʱ��%s", t2-t1));
		L.log(String.format("��ȡ������ʱ��%s", t3-t2));
		L.log(String.format("����ͼƬ��ʱ��%s", t4-t3));
		L.log(String.format("��������ʱ��%s", t5-t4));
	}
	
//	����������ʱ��7
//	��ȡ������ʱ��24510
//	����ͼƬ��ʱ��36
//	��������ʱ��21
	
//	����������ʱ��7
//	��ȡ������ʱ��0
//	����ͼƬ��ʱ��326
//	��������ʱ��143

}
