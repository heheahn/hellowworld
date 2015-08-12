package com.chobo.collections;

import java.util.*;

public class ArrayListEx2 {
	public static void main(String[] args) {
		final int LIMIT = 10;
		String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()zzz";
		int length = source.length();
		
		List list = new ArrayList<String>(length/LIMIT + 10);
		
		for ( int ii = 0; ii < length; ii += LIMIT) {
			if (ii+LIMIT < length) {
				list.add(source.substring(ii, ii+LIMIT));
			} else {
				list.add(source.substring(ii));
			}
		}
		
		for (int ii = 0; ii < list.size(); ii++) {
			System.out.println(list.get(ii));
		}
	}
}
