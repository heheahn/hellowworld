package com.chobo.io;
import java.io.*;

public class FileViewer {
	public static void main(String[] args) throws IOException {
		FileInputStream is = new FileInputStream(args[0]);
		int data = 0;
		
		while ( (data = is.read()) > -1 ) {
			char c = (char)data;
			System.out.print(c);
		}
		is.close();
	}
}
