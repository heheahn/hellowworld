package com.chobo.excercise;

public class Excercise12_5 {
	public static void main(String[] args) throws Exception {
		Thread3 t = new Thread3();
		t.start();
		
		try {
			Thread.sleep(5*1000);
		}
		catch (Exception e) {}
		
		throw new Exception("꽝!!!!");
	}
}

class Thread3 extends Thread {
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