package com.sundd.myspring.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Random;

import com.sundd.myspring.App;

public class SunddUtils {
	
	//对象序列化
	public static byte[] objSer(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}
	
	//反序列化
	public static Object objDeser(byte[] bytes){
		Object obj=null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
	
	/**
	 * String转int
	 * @param obj
	 * @return
	 */
	public static int parseInt(Object obj) {
		return parseInt(obj.toString());
	}
	public static int parseInt(String str) {
		return Integer.parseInt(str);
	}
	
	public static String getRealPath() {
		// 获取项目根目录URL
//		ClassLoader classLoader = App.class.getClassLoader();
//		URL rootUrl = classLoader.getResource("");
		String realpath = System.getProperty("user.dir");
		return realpath;
	}
	
	private static final int MIN_UNICODE = 0x4e00; // 汉字的最小Unicode编码
	private static final int MAX_UNICODE = 0x9fff; // 汉字的最大Unicode编码
	public static String getRandomChinese(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int unicode = MIN_UNICODE + random.nextInt(MAX_UNICODE - MIN_UNICODE + 1);
			sb.append((char) unicode);
		}
		return sb.toString();
	}
}
