package com.chobo.excercise;

public class Excercise12_6 {
	public static void main(String[] args) throws Exception {
		Thread4 t = new Thread4();
		t.setDaemon(true);
		t.start();
		
		try {
			Thread.sleep(5*1000);
		}
		catch (Exception e) {}
		
		throw new Exception("꽝!!!!!");
	}
}

class Thread4 extends Thread {
	public void run() {
		for ( int ii = 0; ii < 10; ii++ ) {
			System.out.println(ii);
			
			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {}
		}
	}
}