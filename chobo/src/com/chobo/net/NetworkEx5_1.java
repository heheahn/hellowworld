package com.chobo.net;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class NetworkEx5_1 {
	public static void main(String[] args) {
		URL url = null;
		String addr = "http://main.nateimg.co.kr/img/cms/content_pool/2015/02/0(162).jpg";
		InputStream input = null;
		FileOutputStream fs = null;
		int ch = 0;
		
		try {
			url = new URL(addr);
//			input = url.openStream();
			input = url.openConnection().getInputStream();
			fs = new FileOutputStream("test.jpg");
			
			while ((ch = input.read()) != -1) {
				fs.write(ch);
			}
			input.close();
			fs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
