package com.zero.iretrieval.core;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * ��ʱ��������࣬������д����࣬�����ֻ��Ҫ����ͼƬ������ȡ���������ݣ��������Ǹ����飬
 * �����Ҫ��ʾͼƬ���߱����ͼƬ��ͨ��awt����ʵ�֣�������ṩ����awt��Image��Ĳ����ķ�����
 * ���ǲ�ֱ�ӵ��ã���������
 * 
 * @author z
 *
 */
public class Image {
	private String imageId;
	private BufferedImage image = null;

	public Image(String pathOrURL, String imageId) {
		this(pathOrURL);
		this.imageId = imageId;
	}

	public Image(String pathOrURL) {
		L.log("load Image " + pathOrURL);
		if (pathOrURL == null || pathOrURL.length() == 0)
			throw new RuntimeException("the path is null or length is 0");
		imageId = imageId == null ? getName(pathOrURL) : imageId;
		//image = IJ.openImage(pathOrURL).getBufferedImage();
	}

	public int height() {
		return image == null ? null : image.getHeight();
	}

	public int width() {
		return image == null ? null : image.getWidth();
	}

	public int[] getRGB(int x, int y) {
		return image == null ? null : Color.rgb(image.getRGB(x, y));
	}

	public float[] getHSV(int x, int y) {
		return image == null ? null : Color.rgb2hsv(image.getRGB(x, y));
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	private static String getName(String path) {
		int index = path.lastIndexOf(File.separator);
		return path.substring(index + 1);
	}

	public BufferedImage genGrayImage() {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				int rgb = image.getRGB(i, j);
				int c_red = (rgb >> 16) & 0xFF;
				int c_green = (rgb >> 8) & 0xFF;
				int c_blue = rgb & 0xFF;
				int grayRGB = (int) (0.3 * c_red + 0.59 * c_green + 0.11 * c_blue);//// �ҶȻ�
				rgb = (255 << 24) | (grayRGB << 16) | (grayRGB << 8) | grayRGB;/// �ҶȻ��ָ�
				result.setRGB(i, j, rgb);
			}
		}
		return result;
	}
	
	public static double[][] imageToGrayArray(BufferedImage image) {

		int width = image.getWidth();
		int height = image.getHeight();

		double[][] result = new double[height][width];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				int rgb = image.getRGB(i, j);
				int grey = (rgb >> 16) & 0xFF;
				result[j][i] = grey;
			}
		}
		return result;
	}
	
	public static BufferedImage arrayToGreyImage(double[][] sourceArray) {
		int width = sourceArray[0].length;
		int height = sourceArray.length;
		BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				int greyRGB = (int) sourceArray[j][i];
				int rgb = (greyRGB << 16) | (greyRGB << 8) | greyRGB;

				targetImage.setRGB(i, j, rgb);
			}
		}
		return targetImage;
	}
	
	public double[][] toArray(){
		return image == null? null: imageToGrayArray(image);
	}
}
