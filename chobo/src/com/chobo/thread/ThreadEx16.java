package com.chobo.thread;

public class ThreadEx16 {
	public static void main() {
		RunImplEx16 r = new RunImplEx16();
		Thread t1 = new Thread(r, "*");
		Thread t2 = new Thread(r, "**");
		Thread t3 = new Thread(r, "***");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(2000);
			t1.suspend();
			Thread.sleep(2000);
			t2.suspend();
			Thread.sleep(3000);
			t1.resume();
			Thread.sleep(3000);
			t1.stop();
			t2.stop();
			Thread.sleep(3000);
			t3.stop();
		}
		catch (InterruptedException e) {}
	}
}

class RunImplEx16 implements Runnable {
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {}
		}
	}
}