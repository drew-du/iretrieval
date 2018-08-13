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
		Util.clean(output_path);//清空输出目录
		long t1 = System.currentTimeMillis();
		IRetrievalEngine engine = EngineFactory.newEngine(IRetrievalEngine.ENGINE_SIMPLE_SORT);//创建引擎
		engine.setParam(IRetrievalEngine.PARAM_FEATURE, IFeature.COLOR_HSV);//设置选用的特征
		engine.setParam(IRetrievalEngine.PARAM_EXTRACTOR, IFeatureExtractor.COLOR);//设置特征提取器
		engine.setParam(IRetrievalEngine.PARAM_MATCHOR, IMatchCalculator.CHI2D);//设置特征比对方法
		engine.setParam(IRetrievalEngine.PARAM_TOP_N, 3);//设置检索数量
		engine.build();
		
		long t2 = System.currentTimeMillis();
		if(features.isEmpty())
			engine.loadFromURI(urlList);//从图片地址提取和加载特征
		else
			engine.loadFeature(features);//直接加载特征
		long t3 = System.currentTimeMillis();
		
		List<String> result = engine.retrieval(new Image("E:\\data\\image\\statesman\\dxp_7.jpg"));
		
		long t4 = System.currentTimeMillis();
		System.out.println(result);
		Util.output(input_path, output_path, result);
		long t5 = System.currentTimeMillis();
		L.log(String.format("创建引擎用时：%s", t2-t1));
		L.log(String.format("提取特征用时：%s", t3-t2));
		L.log(String.format("检索图片用时：%s", t4-t3));
		L.log(String.format("输出结果用时：%s", t5-t4));
	}
	
//	创建引擎用时：7
//	提取特征用时：24510
//	检索图片用时：36
//	输出结果用时：21
	
//	创建引擎用时：7
//	提取特征用时：0
//	检索图片用时：326
//	输出结果用时：143

}
