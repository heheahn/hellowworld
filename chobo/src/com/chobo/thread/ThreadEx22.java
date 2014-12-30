package com.chobo.thread;

public class ThreadEx22 {
	public static void main(String args[]) {
		Data d = new Data();
		
		MyThread22 t1 = new MyThread22(d);
		MyThread22 t2 = new MyThread22(d);
		
		t1.start();
		t2.start();
	}
}

class Data {
	int iv = 0;
}

class MyThread22 extends Thread {
	Data d;
	
	MyThread22(Data d) {
		this.d = d;
	}
	
	public void run() {
		int lv = 0;
		String name = Thread.currentThread().getName();
		while (lv < 3) {
			System.out.println(name + " Local Variable: " + ++lv);
			System.out.println(name + " Instance Variable: " + ++d.iv);
			System.out.println();
		}
	}
}