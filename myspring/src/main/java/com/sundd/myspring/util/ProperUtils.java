package com.sundd.myspring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ProperUtils {
	private static Logger log = Logger.getLogger(ProperUtils.class);
	
	public static Properties getProp(String path){	
		Properties prop = new Properties();
		
		File file = new File(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("加载配置失败:" + path);
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				
			}
		}
		return prop;
	}

}
