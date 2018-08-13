package com.zero.iretrieval.feature;

/**
 * �����ӿڣ����ݲ�ͬ�������ݽṹ����ʽ�Ĳ�ͬ��ʵ�ֲ�ͬ��������
 * 
 * @author z
 */
public interface IFeature {
	int COLOR_HSV = 0x0001;
	int COLOR_RGB = 0x0002;
	int COLOR_GRAY = 0x0003;
	
	int EMOTION_1 = 0x0004;
	int EMOTION_2 = 0x0005;
	int EMOTION_3 = 0x0006;
	
	int SHAPE_1 = 0x0007;
	int SHAPE_2 = 0x0008;
	int SHAPE_3 = 0x0009;
	
	int TEXTURE_SIFT = 0x00010;
	int TEXTURE_2 = 0x00011;
	int TRXTURE_3 = 0x00012;
	/**
	 * ����id
	 * 
	 * @return
	 */
	String id();

	/**
	 * ��������
	 * 
	 * @return
	 */
	int type();
	/**
	 * ����ֵ
	 * 
	 * @return
	 */
	float[] value();
}
