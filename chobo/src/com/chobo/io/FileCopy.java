package com.chobo.io;

import java.io.*;

public class FileCopy {
	public static void main(String[] args) throws IOException {
		try {
			FileInputStream is = new FileInputStream(args[0]);
			FileOutputStream os = new FileOutputStream(args[1]);

			int data = 0;
			while ((data = is.read()) != -1) {
				os.write(data);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
