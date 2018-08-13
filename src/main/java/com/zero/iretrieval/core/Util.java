package com.zero.iretrieval.core;

import com.zero.iretrieval.feature.Feature;
import com.zero.iretrieval.feature.IFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
	
	
	public static void output(String input, String output, List<String> imageNames) {
		File ipath = new File(input);
		File opath = new File(output);
		if (!ipath.exists() || !opath.exists()) {
			System.out.println("path is not exists!");
			return;
		}
		if (imageNames == null || imageNames.isEmpty()) {
			System.out.println("imageNames is null or is empty!");
			return;
		}
		for (String name : imageNames) {
			String newPath = opath.getPath() + File.separator + name;
			String oldPath = ipath.getPath() + File.separator + name;
			copyFile(oldPath, newPath);
		}
	}

	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // �ļ�����ʱ
				InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // �ֽ��� �ļ���С
					if (bytesum == -10)
						System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("���Ƶ����ļ���������");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// clean("E:\\data\\image\\output");
//		List<String> list = new ArrayList<>();
//		list.add("100000.png");
//		list.add("100001.png");
//		list.add("100002.png");
//		output("E:/data/image/input", "E:\\data\\image\\output", list);
		System.out.println(readFeatures().toString());
	}

	public static void clean(String path) {
		File fpath = new File(path);
		if (!fpath.exists()) {
			System.out.println("path is not exists!");
			return;
		}
		for (File file : fpath.listFiles())
			if (file.isFile())
				file.delete();
	}

	public static void saveFeatures(List<IFeature> features) {
		File file = new File("E:\\data\\image\\feature\\feature.txt");
		FileWriter writer = null;
		BufferedWriter bw = null;
		try {
			if (!file.exists())
				file.createNewFile();
			else
				return;
			writer = new FileWriter(file);
			bw = new BufferedWriter(writer);
			for (IFeature f : features) {
				String value = Arrays.toString(f.value());
				value = value.substring(1, value.length() - 1);
				String outstr = String.format("%s:%s", f.id(), value) + "\r\n";
				System.out.println(outstr);
				bw.write(outstr);
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				bw.close();
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void saveFeature(IFeature feature) {
		if(!L.save_feature)
			return;
		File file = new File("E:\\data\\image\\feature\\feature1.txt");
		FileWriter writer = null;
		BufferedWriter bw = null;
		try {
			if (!file.exists())
				file.createNewFile();
			writer = new FileWriter(file, true);
			bw = new BufferedWriter(writer);
			String value = Arrays.toString(feature.value());
			value = value.substring(1, value.length() - 1);
			String outstr = String.format("%s:%s", feature.id(), value) + "\r\n";
			System.out.println(outstr);
			bw.write(outstr);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				bw.close();
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static List<IFeature> readFeatures() {
		List<IFeature> features = new ArrayList<IFeature>();
		File file = new File("E:\\data\\image\\feature\\feature.txt");
		if (!file.exists())
			return features;
		FileReader fr = null;
		BufferedReader br = null;
		Feature.setType(IFeature.COLOR_HSV);
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null) {
				String[] str = line.split(":");
				if(str == null || str.length != 2)
					continue;
				String[] vector = str[1].split(",");
				if(vector == null || vector.length == 0)
					continue;
				float[] value = new float[vector.length];
				for(int i = 0; i < vector.length; i++)
					value[i] = Float.valueOf(vector[i]);
				features.add(new Feature(str[0], value));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return features;
	}
	
}
