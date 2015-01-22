package com.chobo.excercise;

public class Excercise12_7 {
	static boolean stopped = false;
	
	public static void main(String[] args) {
		Thread5 t = new Thread5();
		t.start();
		
		try {
			Thread.sleep(6*1000);
		}
		catch (Exception e) {}
		
		stopped = true;
		t.interrupt();
		System.out.println("stopped");
	}
}

class Thread5 extends Thread {
	public void run() {
		for ( int ii = 0; !Excercise12_7.stopped; ii++ ) {
			System.out.println(ii);
			
			try {
				Thread.sleep(3*1000);
			}
			catch (InterruptedException e) {
			}
		}
	}
}