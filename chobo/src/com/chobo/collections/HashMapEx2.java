package com.chobo.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapEx2 {
	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("김자바", 30);
		map.put("이자바", 90);
		map.put("박자바", 60);
		map.put("안자바", 10);
		map.put("차자바", 100);
		
		Set set = map.entrySet();
		Iterator<Map.Entry<String, Integer>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) it.next();
			System.out.println("이름: " + entry.getKey() + ", 점수: " + entry.getValue());
		}
		
		set = map.keySet();
		System.out.println("명단: " + set);
		
		int total = 0;
		
		Collection<Integer> values = map.values();
		Iterator<Integer> it2 = values.iterator();
		
		while (it2.hasNext()) {
			Integer i = (Integer) it2.next();
			total += i;
		}
		
		System.out.println("총점: " + total);
		System.out.println("평균: " + (float)total / values.size());
		System.out.println("최고점수: " + Collections.max(values));
		System.out.println("최저점수: " + Collections.min(values));
		
	}
}
