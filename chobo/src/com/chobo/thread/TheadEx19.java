package com.chobo.thread;

public class TheadEx19 {
	public static void main(String[] args) {
		MyThreadEx19 t1 = new MyThreadEx19("*");
		MyThreadEx19 t2 = new MyThreadEx19("**");
		MyThreadEx19 t3 = new MyThreadEx19("***");
		
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
			System.out.println("end");
		}
		catch (InterruptedException e) {}
	}
}

class MyThreadEx19 implements Runnable {
	boolean stopped = false;
	boolean suspended = false;
	
	Thread th;
	
	MyThreadEx19 (String name) {
		th = new Thread(this, name);
	}
	
	public void run() {
		String name = Thread.currentThread().getName();
		while (!stopped) {
			if ( !suspended ) {
					System.out.println(name);
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {
						System.out.println(name + " - Interrupted");
					}
			}
			else {
				Thread.yield();
			}
		}
		System.out.println(name + " - stop");
	}
	
	public void suspend() {
		suspended = true;
		th.interrupt();
		System.out.println("interrupt() in suspend()");
	}
	
	public void resume() {
		suspended = false;
	}
	
	public void stop() {
		stopped = true;
		th.interrupt();
		System.out.println("interrupt() in stop()");
	}
	
	public void start() {
		th.start();
	}
}
