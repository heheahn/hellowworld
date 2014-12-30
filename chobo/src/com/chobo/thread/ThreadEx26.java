package com.chobo.thread;

public class ThreadEx26 {
	public static void main(String[] args) {
		MyThreadEx26 t1 = new MyThreadEx26("*");
		MyThreadEx26 t2 = new MyThreadEx26("**");
		MyThreadEx26 t3 = new MyThreadEx26("***");
		
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
			t2.resume();
			t2.stop();
			Thread.sleep(2000);
			t3.stop();
		}
		catch (InterruptedException e) {}
	}
}

class MyThreadEx26 implements Runnable {
	static final int RUNNING = 0;
	static final int SUSPENDED = 1;
	static final int STOPPED = 2;
	
	private int state = 0;
	Thread th;
	
	MyThreadEx26(String name) {
		th = new Thread(this, name);
	}
	
	public synchronized void setState(int state) {
		this.state = state;
		
		if ( state == SUSPENDED ) {
			notify();
		}
		else {
			th.interrupt();
		}
	}
	
	public synchronized boolean checkState() {
		while ( this.state == SUSPENDED ) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		return state == STOPPED;
	}
	
	public void run() {
		String name = Thread.currentThread().getName();
		while (true) {
			System.out.println(name);
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {}
			
			if ( checkState() ) {
				break;
			}
		}
		System.out.println(name + " - END.");
	}
	
	public void suspend() {
		setState(SUSPENDED);
	}
	
	public void resume() {
		setState(RUNNING);
	}
	
	public void stop() {
		setState(STOPPED);
	}
	
	public void start() {
		th.start();
	}
}