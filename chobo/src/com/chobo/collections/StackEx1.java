package com.chobo.collections;

import java.util.Stack;

public class StackEx1 {
	static Stack forward = new Stack<String>();
	static Stack back = new Stack<String>();
	
	public static void main(String[] args) {
		goURL("1. 다음");
		goURL("2. 네이버");
		goURL("3. 네이트");
		goURL("4. 야후");
		
		printStatus();
		
		goBack();
		System.out.println("= 뒤로가기 버튼");
		printStatus();
		
		goBack();
		System.out.println("= 뒤로가기 버튼");
		printStatus();
		
		goForward();
		System.out.println("= 앞으로 가기 버튼");
		printStatus();
		
		goURL("www.java.com");
		System.out.println("= 새 주소 =");
		printStatus();
	}
	
	public static void goURL(String url) {
		back.push(url);
		if (!forward.isEmpty()) {
			forward.clear();
		}
	}
	
	public static void goBack() {
		if (!back.isEmpty()) {
			forward.push(back.pop());
		}
	}
	
	public static void goForward() {
		if (!forward.isEmpty()) {
			back.push(forward.pop());
		}
	}
	
	public static void printStatus() {
		System.out.println("back: " + back);
		System.out.println("forward: " + forward);
		System.out.println("현재화면: " + back.peek());
		System.out.println();
	}
}
