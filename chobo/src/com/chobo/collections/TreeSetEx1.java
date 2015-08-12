package com.chobo.collections;

import java.util.TreeSet;

public class TreeSetEx1 {
	public static void main(String[] args) {
		TreeSet<String> set = new TreeSet<String>();
		String from = "b";
		String to = "d";
		
		set.add("abc");
		set.add("alien");
		set.add("bat");
		set.add("cat");
		set.add("Car");
		set.add("disc");
		set.add("dance");
		set.add("dZZZ");
		set.add("dzzz");
		set.add("delephant");
		set.add("delevator");
		set.add("fan");
		set.add("flower");
		
		System.out.println("range search : from " + from + " to " + to);
		System.out.println("result1: " + set.subSet(from, to));
		System.out.println("result2: " + set.subSet(from, to + "zzz"));
	}
}
