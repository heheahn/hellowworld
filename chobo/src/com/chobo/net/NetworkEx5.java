package com.chobo.net;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class NetworkEx5 {
	public static void main(String[] args) {
		URL url = null;
		String addr = "http://www.nate.com";
		BufferedReader in = null;
		FileWriter fos = null;
		String line = "";
		
		try {
			url = new URL(addr);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			fos = new FileWriter("index.html");
			
			while ((line=in.readLine()) != null) {
				fos.write(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
