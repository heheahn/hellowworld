package com.chobo.thread;

public class ThreadEx11 implements Runnable {
	static boolean autoSave = false;
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadEx11());
		t1.setDaemon(true);
		t1.start();
		
		for	(int ii = 0; ii< 20; ii++ ) {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {}
			System.out.println(ii);
			
			if ( ii == 5 ) {
				autoSave = true;
			}
		}
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void run() {
		while ( true ) {
			try {
				Thread.sleep(3000);
			}
		    catch (InterruptedException e) {}
			
			if ( autoSave ) {
				autoSave();
			}
		}
	}
	
	public void autoSave() {
		System.out.println("자동 저장!!!!");
	}
}
