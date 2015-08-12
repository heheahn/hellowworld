package com.chobo.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapEx4 {
	public static void main(String[] args) {
		String[] data = {"A", "K", "A", "K", "D", "K", "A", "K", "K", "K", "Z", "D"};
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (int ii = 0; ii < data.length; ii++) {
			if (map.containsKey(data[ii])) {
				map.put(data[ii], new Integer(new Integer(map.get(data[ii])).intValue() + 1));
			} else {
				map.put(data[ii], new Integer(1));
			}
		}
		
		Iterator<Entry<String, Integer>> iterator = map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String,java.lang.Integer> entry = (Map.Entry<java.lang.String,java.lang.Integer>) iterator.next();
			System.out.println(entry.getKey() + ": " + printBar('#', entry.getValue()) + entry.getValue());
		}
	}
	
	public static String printBar(char ch, int num) {
		char[] bar = new char[num];
		for (int ii = 0; ii < bar.length; ii++) {
			bar[ii] = ch;
		}
		return new String(bar);
	}
}
