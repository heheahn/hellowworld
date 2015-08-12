package com.chobo.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapEx1 {
	public static void main(String[] args) {
		String[] data = {"A", "K", "A", "K", "D", "K", "A", "K", "K", "K", "Z", "D"};
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		for (int ii = 0; ii < data.length; ii++) {
			String val = data[ii];
			if (map.containsKey(val)) {
				map.put(val, map.get(val) + 1);
			}
			else {
				map.put(val, 1);
			}
		}
		
		Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		
		System.out.println("= 기본 정렬=");
		
		while (it.hasNext()) {
			Map.Entry<java.lang.String,java.lang.Integer> entry = (Map.Entry<java.lang.String,java.lang.Integer>) it
					.next();
			int val = entry.getValue();
			System.out.println(entry.getKey() + " : " + printBar('#', val) + " " + val);
			
		}
		
		System.out.println();
		
		Set set = map.entrySet();
		List list = new ArrayList<Map.Entry<String, Integer>>(set);
		
		Collections.sort(list, new ValueComparator());
		
		it = list.iterator();
		
		System.out.println("= 값의 크기가 큰 순서로 정렬 = ");
		
		while (it.hasNext()) {
			Map.Entry<java.lang.String,java.lang.Integer> entry = (Map.Entry<java.lang.String,java.lang.Integer>) it
					.next();
			int value = entry.getValue();
			System.out.println(entry.getKey() + " : " + printBar('#', value) + " " + value);
		}
		
		
		
	}
	
	static class ValueComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			if (o1 instanceof Map.Entry && o2 instanceof Map.Entry ) {
				Map.Entry<String, Integer> e1 = (Map.Entry<String, Integer>)o1;
				Map.Entry<String, Integer> e2 = (Map.Entry<String, Integer>)o2;
				return e2.getValue() - e1.getValue();
			}
			return -1;
		}
		
	}
	
	static String printBar(char ch, int val) {
		char[] bar = new char[val];
		for (int ii = 0; ii < bar.length; ii++) {
			bar[ii] = ch;
		}
		return new String(bar);
	}
}
