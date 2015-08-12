package com.chobo.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapEx3 {
	static HashMap<String, HashMap> phoneBook = new HashMap<String, HashMap>();
	
	public static void main(String[] args) {
		addPhoneNo("친구", "김자바", "010-111-2222");
		addPhoneNo("친구", "이자바", "010-222-2222");
		addPhoneNo("친구", "박자바", "010-333-3333");
		addPhoneNo("친구", "안자바", "010-444-4444");
		addPhoneNo("회사", "김과장", "010-555-5555");
		addPhoneNo("회사", "이과장", "010-666-6666");
		addPhoneNo("회사", "박과장", "010-777-7777");
		addPhoneNo("세탁소", "010-888-9999");
		
		printList();
	}
	
	static void addGroup(String group) {
		if (!phoneBook.containsKey(group)) {
			phoneBook.put(group, new HashMap<String, String>());
		}
	}
	
	static void addPhoneNo(String group, String name, String number) {
		addGroup(group);
		HashMap map = phoneBook.get(group);
		map.put(number, name);
	}
	
	static void addPhoneNo(String name, String number) {
		addPhoneNo("기타", name, number);		
	}
	
	static void printList() {
		Set<Entry<String,HashMap>> entrySet = phoneBook.entrySet();
		Iterator<Entry<String, HashMap>> it = entrySet.iterator();
		
		while (it.hasNext()) {
			Map.Entry<java.lang.String,HashMap> entry = (Map.Entry<java.lang.String,HashMap>) it.next();
			Set subSet = entry.getValue().entrySet();
			Iterator subIterator = subSet.iterator();
			
			System.out.println(" * " + entry.getKey() + " [" + subSet.size() + "]");
			
			while (subIterator.hasNext()) {
				Map.Entry<String, String> setEl = (Map.Entry)subIterator.next();
				String number = setEl.getKey();
				String name = setEl.getValue();
				System.out.println("이름: " + name + " " + number);
			}
			System.out.println();
		}
	}
}
