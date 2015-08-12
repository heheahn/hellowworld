package com.chobo.collections;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesEx2 {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java PropertiesEx2 filepath");
			System.exit(0);
		}
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(args[0]));
		}
		catch (IOException e) {
			System.out.println("지정된 파일을 찾을 수 없습니다.");
			System.exit(0);
		}
		String name = prop.getProperty("name");
		String[] values = prop.getProperty("data").split(",");
		int min = 0;
		int max = 0;
		int sum = 0;
		for (int ii = 0; ii < values.length; ii++) {
			int val = Integer.parseInt(values[ii]);
			if (ii == 0)
				min = max = val;
			if (max < val) {
				max = val;
			}
			else if (min > val) {
				min = val;
			}
			sum += val;
		}
		
		System.out.println("이름: " + name);
		System.out.println("최소값: " + min);
		System.out.println("최대값: " + max);
		System.out.println("합계: " + sum);
		System.out.println("평균: " + sum/values.length);
		
	}
}
