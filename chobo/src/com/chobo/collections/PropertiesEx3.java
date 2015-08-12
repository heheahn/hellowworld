package com.chobo.collections;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Properties;

public class PropertiesEx3 {
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("timeout", "30");
		prop.setProperty("lang", "한글");
		prop.setProperty("size", "10");
		prop.setProperty("capacity", "10");
		
		try {
			prop.store(new FileWriter("/home/heheahn/test.txt"), "test");
			prop.storeToXML(new FileOutputStream("/home/heheahn/test.xml"), "xml test");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
