package com.zero.iretrieval.test;

import com.zero.iretrieval.core.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class Test {
	public static File[] images() {
		try {
			File root = new File("");
			if (!root.exists() || !root.isDirectory())
				return null;
			return root.listFiles();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static void hsv() throws IOException {
		File file = images()[0];
		BufferedImage img = ImageIO.read(file);
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				int binartColor = img.getRGB(i, j);
				if (binartColor == 16777215)
					continue;
				Color c = new Color(16777215);
//				float[] hsv = RGB2HSV(c.getRed(), c.getGreen(), c.getBlue());
//				System.out.println(
//						String.format("%s %s %s %s", c.getRed(), c.getGreen(), c.getBlue(), Arrays.toString(hsv)));
			}
		}
	}

	public static void main(String[] args) throws IOException {
//		int w = 400, h = 400;
//		ImageProcessor ip = new ByteProcessor(w, h);
//		String title = "Image";
//		ImagePlus imp = new ImagePlus(title, ip);
//		imp.show();

//		ImagePlus imp = IJ.openImage("E:/data/samples/aloeL.jpg");
//		imp.show();  
//		  
//		ImagePlus imp1 = IJ.openImage("http://ww1.sinaimg.cn/mw690/6941baebjw1enxaontp8xj20m81hcjv8.jpg");  
//		imp1.show();
//		imp1.getProcessor().getHistogram();
//		int binartColor = 0;
//		binartColor = 16777215;
//		Color c = new Color(binartColor);
//		System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());

//		int[] rgb = {255, 24, 127};
//		Color color = new Color(rgb[0], rgb[1], rgb[2]);
//		System.out.println(String.format("r:%s, g:%s, b:%s", color.getRed(), color.getGreen(), color.getBlue()));
//		float[] hsv = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
////		hsv[0] *= 255;
////		hsv[1] *= 255;
////		hsv[2] *= 255;
//		
//		System.out.println(Arrays.toString(rgb));
//		System.out.println(Arrays.toString(hsv));
//		System.out.println(Arrays.toString(new int[] {Math.round(hsv[0]), Math.round(hsv[1]), Math.round(hsv[2])}));

//		int[] h = {1, 2, 3};
//		int[] s = {4, 5, 6, 7};
//		int[] v = {8, 9, 0, 11};
//		int[] vector = new int[h.length + s.length + v.length];
//		System.arraycopy(h, 0, vector, 0, h.length);
//		System.arraycopy(s, 0, vector, h.length, s.length);
//		System.arraycopy(v, 0, vector, h.length + s.length, v.length);
//		System.out.println(Arrays.toString(vector));

//		System.out.println(Math.pow(3.1, 2));;
		test();
	}

	private static void test() throws IOException {
		BufferedImage image1 = new Image(String.format("E:\\data\\input\\10300%s.png", 1)) .genGrayImage();
		// image1 =
		// Image_Utility.doubleArrayToGreyImage(ImageTransform.gaussTran(Image_Utility.imageToDoubleArray(image1),
		// 2));
//		HashMap<Integer, double[][]> result1 = ImageTransform.getGaussPyramid(Image_Utility.imageToDoubleArray(image1),
//				20, 3, 1.6);
//
//		HashMap<Integer, double[][]> dog1 = ImageTransform.gaussToDog(result1, 6);
//		HashMap<Integer, List<MyPoint>> keyPoints1 = ImageTransform.getRoughKeyPoint(dog1, 6);
//		keyPoints1 = ImageTransform.filterPoints(dog1, keyPoints1, 10, 0.03);
 
	}
	private static void print(double[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static int index(int i, float f) {
		int index = (int) Math.ceil(i * f);
		return index == 0 ? 0 : index - 1;
	}
}
