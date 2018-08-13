package com.zero.iretrieval.feature.color;

import com.zero.iretrieval.feature.IFeature;

public class RGBFeature implements IFeature {
	private final String id;
	private static final int type = IFeature.COLOR_RGB;
	private float[] value;
	/**
	 * �����Ĳ����������������ͨ����bin��Ҳ����ֱ��ͼ�����
	 */
	private static int[] param = { 8, 12, 3 };

	public RGBFeature(String id) {
		this.id = id;
	}

	public int type() {
		return type;
	}

	public float[] value() {
		return value;
	}
	
	public void setValue(float[] value) {
		this.value = value;
	}

	public void setParam(int[] param) {
		if (param == null || param.length != 3)
			throw new RuntimeException("the param is null or len != 3");
		RGBFeature.param = param;
	}

	public int[] getParam() {
		return param;
	}

	public String id() {
		return id;
	}

}
