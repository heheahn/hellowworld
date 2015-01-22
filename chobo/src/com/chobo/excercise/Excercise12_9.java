package com.chobo.excercise;
import java.util.*;

public class Excercise12_9 {
	int interval = 2;
	Vector words = new Vector();
	String[] data = {"태연", "유리", "윤아", "효연", "수영", "서현", "티파니", "써니", "제시카"};
	
	WordGenerator wg = new WordGenerator();
	
	public static void main(String[] args) {
		Excercise12_9 game = new Excercise12_9();
		game.wg.start();
		Vector words = game.words;
		
		while (true) {
			System.out.println(words);
			
			System.out.print(">>");
			
			Scanner s = new Scanner(System.in);
			String input = s.nextLine().trim();
			int index = words.indexOf(input);
			
			if ( index != -1 ) {
				words.remove(index);
			}
		}
		
	}
	
	class WordGenerator extends Thread {
		public void run() {
			while (true) {
				int rIndex = (int)(Math.random() * 9);
				String name = data[rIndex];
				if ( words.indexOf(name) == -1 ) {					
					words.add(name);
				}
				try {
					Thread.sleep(2*1000);
				}
				catch (InterruptedException e) {}
			}
		}
	}
}

