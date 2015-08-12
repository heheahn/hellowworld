package com.chobo.collections;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListEx1 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(5));
		list.add(new Integer(4));
		list.add(new Integer(2));
		list.add(new Integer(0));
		list.add(new Integer(1));
		
		ArrayList<Integer> list2 = new ArrayList<Integer>(list.subList(1, 4));
		print(list, list2);
		
		Collections.sort(list);
		Collections.sort(list2);
		print(list, list2);
		
		System.out.println("list.containsAll(list2): " + list.containsAll(list2));
		list2.add(6);
		list2.add(7);
		list2.add(3, 8);
		print(list, list2);
		
		System.out.println("list.retainsAll(list2): " + list.retainAll(list2));
		print(list, list2);
		
		for (int ii = list2.size()-1; ii >=0; ii--) {
			if ( list.contains(list2.get(ii)) ) {
				list2.remove(ii);
			}
		}
		print(list, list2);
		
		
		
	}
	
	static void print(ArrayList<Integer> list, ArrayList<Integer> list2) {
		System.out.println("list1: " + list);
		System.out.println("list2: " + list2);
		System.out.println();
	}
}
