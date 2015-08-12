package com.chobo.collections;

import java.util.HashSet;

public class HashSetEx3 {
	public static void main(String[] args) {
		HashSet<String> set = new HashSet<String>();
		
		set.add("abc");
		set.add("abc");
		set.add(new Person("json", 5) + "");
		set.add(new Person("json", 5) + "");
		
		System.out.println(set);
	}
}

class Person {
	String name;
	int age;
	
	Person () {
	}
	
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {			
			Person tmp = (Person)obj;
			return this.name == tmp.name && this.age == tmp.age;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (name+age).hashCode();
	}
	
	@Override
	public String toString() {
		return name + ":" + age;
	}
}