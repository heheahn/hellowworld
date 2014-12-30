package com.chobo.thread;

public class ThreadEx10 {
	public static void main(String[] args) {
		ThreadGroup main = Thread.currentThread().getThreadGroup();
		ThreadGroup group1 = new ThreadGroup("Group1");
		ThreadGroup group2 = new ThreadGroup("Group2");
		
		ThreadGroup sub1 = new ThreadGroup(group1, "SubGroup1");
		
		group1.setMaxPriority(3);
		
		Thread th1 = new Thread(group1, "th1");
		Thread th2 = new Thread(sub1, "th2");
		Thread th3 = new Thread(group2, "th3");
		
		th1.start();
		th2.start();
		th3.start();
		
		System.out.println(">>List of ThreadGroup : " + main.getName()
				              + ", Active ThreadGroup : " + main.activeGroupCount()
				              + ", Active Thread : " + main.activeCount());
		
		main.list();
	}
}
