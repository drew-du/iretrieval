package com.zero.iretrieval.feature.color;

import com.zero.iretrieval.core.Image;
import com.zero.iretrieval.core.L;
import com.zero.iretrieval.feature.IFeature;
import com.zero.iretrieval.feature.IFeatureExtractor;

public class ColorFeatureExtractor implements IFeatureExtractor {

	public IFeature extract(Image img, int type) {
		L.log("ColorFeatureExtractor extract " + img.getImageId());
		switch (type) {
		case IFeature.COLOR_HSV:
			return genHSVFeature(img);
		case IFeature.COLOR_RGB:
			return genRGBFeature(img);
		case IFeature.COLOR_GRAY:
			return genGRAYFeature(img);
		default:
			throw new RuntimeException(String.format("the type \"%s\" is not support!", type));
		}
	}

	private IFeature genGRAYFeature(Image img) {
		throw new RuntimeException(String.format("the type \"%s\" is not support!", IFeature.COLOR_GRAY));
	}

	private IFeature genRGBFeature(Image img) {
		L.log("genRGBFeature " + img.getImageId());
		RGBFeature feature = new RGBFeature(img.getImageId());
		int[] bins = feature.getParam();
		float[] r = new float[bins[0]];
		float[] g = new float[bins[1]];
		float[] b = new float[bins[2]];
		int[] temp;
		for(int i = 0; i< img.height(); i++) {
			for(int j = 0; j < img.width(); j++) {
				temp = img.getRGB(j, i);
				r[index(bins[0], temp[0]/255.0f)] += 1;
				g[index(bins[1], temp[1]/255.0f)] += 1;
				b[index(bins[2], temp[2]/255.0f)] += 1;
			}
		}
		float[] vector = new float[r.length + g.length + b.length];
		System.arraycopy(r, 0, vector, 0, r.length);
		System.arraycopy(g, 0, vector, r.length, g.length);
		System.arraycopy(b, 0, vector, r.length + g.length, b.length);
		feature.setValue(vector);
		return feature;
	}
	
	private IFeature genHSVFeature(Image img) {
		L.log("genHSVFeature " + img.getImageId());
		HSVFeature feature = new HSVFeature(img.getImageId());
		int[] bins = feature.getParam();
		float[] h = new float[bins[0]];
		float[] s = new float[bins[1]];
		float[] v = new float[bins[2]];
		float[] temp;
		for(int i = 0; i< img.height(); i++) {
			for(int j = 0; j < img.width(); j++) {
				temp = img.getHSV(j, i);
				h[index(bins[0], temp[0])] += 1;
				s[index(bins[1], temp[1])] += 1;
				v[index(bins[2], temp[2])] += 1;
			}
		}
		float[] vector = new float[h.length + s.length + v.length];
		System.arraycopy(h, 0, vector, 0, h.length);
		System.arraycopy(s, 0, vector, h.length, s.length);
		System.arraycopy(v, 0, vector, h.length + s.length, v.length);
		feature.setValue(vector);
		return feature;
	}

	private int index(int i, float f) {
		int index = (int)Math.ceil(i * f);
		return index == 0 ? 0 : index - 1;
	}

}
