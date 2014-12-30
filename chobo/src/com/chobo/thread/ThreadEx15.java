package com.chobo.thread;

public class ThreadEx15 {
	public static void main(String[] args) {
		ThreadEx15_1 t1 = new ThreadEx15_1();
		ThreadEx15_2 t2 = new ThreadEx15_2();
		
		t1.start();
		t2.start();
		
		try {
			t1.sleep(5000);
		}
		catch (InterruptedException e) {}
		System.out.println("<<main 종료>>");
	}
}

class ThreadEx15_1 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 300; ii++ ) {
			System.out.print('-');
		}
		System.out.println("<<t1 종료>>");
	}
}

class ThreadEx15_2 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 300; ii++ ) {
			System.out.print('|');
		}
		System.out.println("<<t2 종료>>");
	}
}
