package com.chobo.thread;

public class ThreadEx9 {
	public static void main(String[] args) {
		ThreadEx9_1 t1 = new ThreadEx9_1();
		ThreadEx9_2 t2 = new ThreadEx9_2();
		
		t2.setPriority(7);
		
		System.out.println("Priority of th1(-) : " + t1.getPriority());
		System.out.println("Priority of th2(|) : " + t2.getPriority());
		
		t1.start();
		t2.start();
	}
}

class ThreadEx9_1 extends Thread {
	public void run() {
		for (int ii = 0; ii < 300; ii++) {
			System.out.print('-');
			for (int jj = 0; jj < 100000000; jj++)
				;
		}
	}
}

class ThreadEx9_2 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 300; ii++) {
			System.out.print('|');
			for ( int jj = 0; jj < 100000000; jj++);
		}
	}
}
