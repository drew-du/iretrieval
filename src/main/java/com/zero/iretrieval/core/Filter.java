package com.zero.iretrieval.core;
 
/**
 * 提供对图像的所有滤波操作
 * @author z
 *
 */
public class Filter {
	private Filter() {
	}

	static final double kernelSizeFactor = 3.5; // times sigma

	public static double[][] gaussKernel2d(double sigma) {
		int rad = (int) (kernelSizeFactor * sigma);
		int size = rad + rad + 1;
		double[][] kernel = new double[size][size];
		double sigma2 = sigma * sigma;
		double scale = 1.0 / (2 * Math.PI * sigma * sigma);
		double sum = 0;
		for (int i = 0; i < size; i++) {
			double x = rad - i;
			for (int j = 0; j < size; j++) {
				double y = rad - j;
				kernel[i][j] = (float) (scale * Math.exp(-0.5 * (x * x + y * y) / sigma2));
				sum = sum + kernel[i][j];
			}
		}
		for (int i = 0; i < size; i++) {// normalize the kernel:
			for (int j = 0; j < size; j++) {
				kernel[i][j] = (float) (kernel[i][j] / sum);
			}
		}
		return kernel;
	}
	
	public static void main(String[] args) {
		double[][] a = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
		double[][] r = gauss(a, 0.6);
		for(int i = 0; i < r.length; i++)
			for(int j = 0; j < r[0].length; j++)
				System.out.println(r[i][j]);
	}

	public static double[][] gauss(double[][] source, double sigma) {
		int height = source.length;
		int width = source[0].length;
		double[][] result = new double[height][width];
		double[][] kernel = gaussKernel2d(sigma);
		int tWH = kernel[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double sum = 0.0;
				for (int m = 0; m < tWH; m++) {
					for (int n = 0; n < tWH; n++) {
						int x = j - (int) tWH / 2 + n;
						int y = i - (int) tWH / 2 + m;
						if (x >= 0 && x < width && y >= 0 && y < height) {
							sum = sum + source[y][x] * kernel[m][n];
						}
					}
				}
				result[i][j] = sum;
			}
		}
		return result;
	}
	
	public static double[][] sampling2(double[][] source){
		int width = (int) source[0].length / 2;
		int height = (int) source.length / 2;
		double[][] result = new double[height][width];
		for (int i = 0; i < height; i++) 
			for (int j = 0; j < width; j++) 
				result[i][j] = source[2 * i][2 * j];
		return result;
	}

}
