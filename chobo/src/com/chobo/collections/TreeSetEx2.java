package com.chobo.collections;

import java.util.TreeSet;

public class TreeSetEx2 {
	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		int[] score = {80, 95, 50, 35, 45, 65, 10, 100};
		
		for (int ii = 0; ii < score.length; ii++) {
			set.add(new Integer(score[ii]));
		}
		
		System.out.println("under 50: " + set.headSet(new Integer(50)));
		System.out.println("up 50: " + set.tailSet(new Integer(50)));
	}
}
