package com.chobo.collections;

import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorEx1 {
	public static void main(String[] args) {
		TreeSet<Integer> set1 = new TreeSet<Integer>();
		TreeSet<Integer> set2 = new TreeSet<Integer>(new Descending());
		
		int[] score = { 30, 70, 20, 90, 100 };
		
		for (int ii = 0; ii < score.length; ii++) {
			set1.add(ii);
			set2.add(ii);
		}
		
		System.out.println("set1: " + set1);
		System.out.println("set2: " + set2);
	}
}

class Descending implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		if (o1 instanceof Comparable && o2 instanceof Comparable ) {
			Comparable c1 = (Comparable)o1;
			Comparable c2 = (Comparable)o2;
			return c1.compareTo(c2) * -1;
		}
		return -1;
	}

}
