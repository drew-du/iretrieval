package com.zero.iretrieval.core;

import com.zero.iretrieval.feature.IFeature;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class IO {

	/**
	 * �������ļ���ȡ ����˳���� ��������(�ļ� ���ݿ�) ͼƬ(�ļ� ����) ��������λ��(�ļ� ���ݿ�) Ĭ�ϱ���λ��(�ļ� ���ݿ�)
	 */
	private static final String PROPERTIES_PATH = "config/config.properties";
	private static Properties properties = new Properties();
	static {
		load();
	}

	private IO() {
	}

	public static List<IFeature> readFeature() {
		String path = null;
		if((path = properties.getProperty("feature_db")) != null) {
			
		} else if((path = properties.getProperty("feature_file")) != null) {
			
		} 
		return null;
	}

	public static void saveFeature(List<IFeature> features) {

	}

	public static void reload() {
		load();
	}

	private static void load() {
		InputStream in = IO.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//	public static void main(String[] args) throws IOException {
//		System.out.println(properties.get("default_feature"));
//		properties.put("default_feature", 12345);
//		System.out.println(properties.get("default_feature"));
//		reload();
//		System.out.println(properties.get("default_feature"));
//	}
}
