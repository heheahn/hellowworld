package com.chobo.collections;

import java.util.HashMap;
import java.util.Scanner;

public class HashMapEx1 {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("abc", "111");
		map.put("ddd", "222");
		map.put("xxx", "444");
		map.put("sss", "123");
		map.put("sdf", "000");

		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.print("ID: ");
			String id = in.nextLine().trim();
			
			System.out.print("PW: ");
			String pw = in.nextLine().trim();
			
			if (!map.containsKey(id)) {
				System.out.println("No ID.");
				continue;
			} else {
				if (!map.containsValue(pw)) {
					System.out.println("No PW.");
					continue;
				} else {
					System.out.println("OK.");
					break;
				}
			}
		}

	}
}
