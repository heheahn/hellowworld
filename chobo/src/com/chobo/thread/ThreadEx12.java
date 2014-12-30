package com.chobo.thread;
import java.util.*;

public class ThreadEx12 {
	public static void main(String[] args) throws Exception {
		ThreadEx12_1 t1 = new ThreadEx12_1("Thread1");
		ThreadEx12_2 t2 = new ThreadEx12_2("Thread2");
		t1.start();
		t2.start();
	}
}

class ThreadEx12_1 extends Thread {
	ThreadEx12_1(String name) {
		super(name);
	}
	
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
	}
}

class ThreadEx12_2 extends Thread {
	ThreadEx12_2(String name) {
		super(name);
	}
	
	public void run () {
		Map map = getAllStackTraces();
		Iterator it = map.keySet().iterator();
		
		int x = 0;
		
		while ( it.hasNext() ) {
			Object obj = it.next();
			Thread t = (Thread)(obj);
			StackTraceElement[] ste = (StackTraceElement[])(map.get(obj));
			
			System.out.println("[" + ++x + "] name:" + t.getName()
					+ ", group name: " + t.getThreadGroup().getName()
					+ ", daemon: " + t.isDaemon());
			
			for ( int ii = 0; ii < ste.length; ii++ ) {
				System.out.println(ste[ii]);
			}
			System.out.println();
		}
	}
}