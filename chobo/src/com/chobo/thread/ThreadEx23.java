package com.chobo.thread;

public class ThreadEx23 {
	public static void main(String args[]) {
		MyThreadEx23 r = new MyThreadEx23();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();		
	}
}

class Account {
	int balance = 1000;
	
	public synchronized void withdraw(int money) {
		if (balance >= money) {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {}
			balance -= money;			
		}
	}
}

class MyThreadEx23 implements Runnable {
	Account acc = new Account();
	
	public void run() {
		String name = Thread.currentThread().getName();
		while (acc.balance > 0) {
			int money = (int)(Math.random() * 3 + 1) * 100;
			acc.withdraw(money);
			System.out.println(name + " balance: " + acc.balance);
		}
	}
}