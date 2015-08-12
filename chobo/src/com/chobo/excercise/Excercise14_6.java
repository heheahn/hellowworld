package com.chobo.excercise;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Excercise14_6 {
	static String[] argArr;
	static File curDir;

	static {
		try {
			curDir = new File(System.getProperty("user.dir"));
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			try {				
				String prompt = curDir.getCanonicalPath() + ">>";
				System.out.println(prompt);
				
				String input = in.nextLine();
				
				input = input.trim();
				argArr = input.split(" +");
				
				String cmd = argArr[0].trim();
				
				if ( "".equals(cmd) ) {
					continue;
				}
				
				if ( cmd.equals("q") ) {
					System.exit(0);
				}
				else if ( cmd.equals("cd") ) {
					cd();
				}
				else {
					for (int ii = 0; ii < argArr.length; ii++) {
						System.out.println(argArr[ii]);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				in.close();
			}
		}
	}

	public static void cd() {
		if ( argArr.length == 1) {
			System.out.println(curDir);
		}
		else if ( argArr.length > 2 ) {
			System.out.println("USAGE: cd directory");
			return;
		}
		
		String subDir = argArr[1];
		
		if ( subDir.equals("..") ) {
			curDir = curDir.getParentFile();
			return;
		}
		else if ( subDir.equals(".") ) {
			try {
				System.out.println(curDir.getCanonicalPath() + ">>");				
			}
			catch (Exception e) {}

		}
		else {
			File newFile = new File(curDir, subDir);
			if ( newFile.exists() && newFile.isDirectory() ) {
				curDir = newFile;
			}
			else {
				System.out.println("no dir");
			}
		}
	}
}
