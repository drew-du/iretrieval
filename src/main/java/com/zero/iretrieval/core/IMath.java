package com.zero.iretrieval.core;

/**
 * 提供算法相关的数学计算方法
 * @author z
 *
 */
public class IMath {
	private IMath() {}
	
	public static void normalize(float[] arr) {
		if(arr == null || arr.length < 2)
			return;
		float max = arr[0], min = arr[0], range;
		for(int i = 1; i < arr.length; i++) {
			if(max < arr[i])
				max = arr[i];
			if(min > arr[i])
				min = arr[i];
		}
		range = max - min;
		if(range == 0)
			return;
		for(int i = 0; i < arr.length; i++)
			arr[i] = (arr[i] - min) / range;
	}
	
	public static double[] normalize(double[] source,double sum){
		double[] result=new double[source.length];
		for(int i=0;i<source.length;i++){
			result[i]=source[i]/sum;
			if(result[i]<0.0001){
				result[i]=0;
			}
		}
		return result;
	}
}
