package com.zero.iretrieval.test;

import com.zero.iretrieval.core.Distance;
import com.zero.iretrieval.core.IMath;
import com.zero.iretrieval.core.Image;
import com.zero.iretrieval.feature.ExtractorFactory;
import com.zero.iretrieval.feature.IFeature;
import com.zero.iretrieval.feature.IFeatureExtractor;

import java.util.Arrays;

public class ColorTest {

	public static void main(String[] args) {
		Image img1 = new Image("E:/data/samples/aloeL.jpg", "image1");
		Image img2 = new Image("E:/data/samples/aloeR.jpg", "iamge2");
		IFeatureExtractor extractor = ExtractorFactory.newExtractor(IFeatureExtractor.COLOR);
		IFeature feature1 = extractor.extract(img1, IFeature.COLOR_HSV);
		IFeature feature2 = extractor.extract(img2, IFeature.COLOR_HSV);
		System.out.println(Arrays.toString(feature1.value()));
		System.out.println(Arrays.toString(feature2.value()));
		
		IFeature feature3 = extractor.extract(img1, IFeature.COLOR_RGB);
		IFeature feature4 = extractor.extract(img2, IFeature.COLOR_RGB);
		System.out.println(Arrays.toString(feature3.value()));
		System.out.println(Arrays.toString(feature4.value()));
		
		IMath.normalize(feature1.value());
		IMath.normalize(feature2.value());
		IMath.normalize(feature3.value());
		IMath.normalize(feature4.value());
		System.out.println(Arrays.toString(feature1.value()));
		System.out.println(Arrays.toString(feature2.value()));
		System.out.println(Arrays.toString(feature3.value()));
		System.out.println(Arrays.toString(feature4.value()));
		
		System.out.println(Distance.person(feature1.value(), feature2.value()));
		
	}
}
