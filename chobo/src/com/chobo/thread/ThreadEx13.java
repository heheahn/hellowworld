package com.chobo.thread;

public class ThreadEx13 {
	static long startTime = 0;
	
	public static void main(String args[]) {
		ThreadEx13_1 t1 = new ThreadEx13_1();
		ThreadEx13_2 t2 = new ThreadEx13_2();
		
		t1.start();
		t2.start();
		startTime = System.currentTimeMillis();
		
		try {
			t1.join();
			t2.join();
		}
		catch (InterruptedException e) {}
		
		System.out.println("소요시간: " + (System.currentTimeMillis()	- startTime));
		
	}
}

class ThreadEx13_1 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 300; ii++ ) {
			System.out.print('-');
		}
	}
}

class ThreadEx13_2 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 300; ii++ ) {
			System.out.print('|');
		}
	}
}