package com.chobo.collections;

import java.util.Properties;

public class PropertiesEx4 {
	public static void main(String[] args) {
		Properties prop = System.getProperties();
		System.out.println("java.version: " + prop.getProperty("java.version"));
		System.out.println("user.language: " + prop.getProperty("user.language"));
		prop.list(System.out);
	}
}
