package com.chobo.collections;

import java.util.Enumeration;
import java.util.Properties;

public class PropertiesEx1 {
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		prop.setProperty("timeout", "20");
		prop.setProperty("language", "kr");
		prop.setProperty("size", "20");
		prop.setProperty("capacity", "20");
		
		Enumeration<String> e = (Enumeration<String>) prop.propertyNames();
		while (e.hasMoreElements()) {
			String el = (String) e.nextElement();
			System.out.println(el + "=" + prop.getProperty(el));
		}
		System.out.println();
		prop.setProperty("size", "30");
		System.out.println("size:" + prop.getProperty("size"));
		System.out.println("loadfactor:" + prop.getProperty("loadfactor", "0.75"));
		System.out.println(prop);
		prop.list(System.out);
	}
}
