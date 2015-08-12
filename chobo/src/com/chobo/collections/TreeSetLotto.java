package com.chobo.collections;

import java.util.TreeSet;

public class TreeSetLotto {
	public static void main(String args[]) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		for (int ii = 0; ii < 6; ii++) {
			int num = (int)(Math.random() * 45) + 1;
			set.add(num);
		}
		System.out.println(set);
	}
}
