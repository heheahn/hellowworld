package com.chobo.thread;

public class ThreadEx14 {
	static long startTime = 0;
	
	public static void main(String args[]) {
		ThreadEx14_1 t1 = new ThreadEx14_1();
		ThreadEx14_2 t2 = new ThreadEx14_2();
		
		t1.start();
		
		try {
			t1.join();
		}
		catch (InterruptedException e) {}
		t2.start();
		
		startTime = System.currentTimeMillis();
		
	}
}

class ThreadEx14_1 extends Thread {
	public void run () {
		for ( int ii = 0; ii < 300; ii++ ) {
			System.out.print('-');
		}
	}
}

class ThreadEx14_2 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 300; ii++ ) {
			System.out.print('|');
		}
	}
}