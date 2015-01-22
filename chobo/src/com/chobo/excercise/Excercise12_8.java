package com.chobo.excercise;

public class Excercise12_8 {
	public static void main(String[] args) {
		Thread6 t1 = new Thread6();
		Thread7 t2 = new Thread7();
		t1.start();
		t2.start();
	}
}

class Thread6 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 300; ii++ ) {
			System.out.print('-');
			Thread.yield();
		}
	}
}

class Thread7 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 300; ii++ ) {
			System.out.print('|');
			Thread.yield();
		}
	}
}