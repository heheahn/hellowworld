package com.chobo.thread;

public class ThreadEx18 {
	public static void main(String[] args) {
		MyThreadEx18 t1 = new MyThreadEx18("*");
		MyThreadEx18 t2 = new MyThreadEx18("**");
		MyThreadEx18 t3 = new MyThreadEx18("***");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(2000);
			t1.suspend();
			Thread.sleep(2000);
			t2.suspend();
			Thread.sleep(2000);
			t1.resume();
			Thread.sleep(3000);
			t1.stop();
			t2.stop();
			Thread.sleep(2000);
			t3.stop();
		}
		catch (InterruptedException e) {}
	}
}

class MyThreadEx18 implements Runnable {
	boolean stopped = false;
	boolean suspended = false;
	
	Thread th;
	
	MyThreadEx18(String name) {
		th = new Thread(this, name);
	}
	
	public void run() {
		while (!stopped) {
			if ( !suspended ) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {}				
			}
		}
		System.out.println(Thread.currentThread().getName() + " - stop");
	}
	
	public void suspend() {
		suspended = true;
	}
	
	public void resume() {
		suspended = false;
	}
	
	public void stop() {
		stopped = true;
	}
	
	public void start() {
		th.start();
	}
}