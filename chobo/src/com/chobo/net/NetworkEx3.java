package com.chobo.net;

import java.net.URL;
import java.net.URLConnection;

public class NetworkEx3 {
	public static void main(String[] args) {
		URL url = null;
		String addr = "http://www.nate.com";
		String line = "";
		
		try {
			url = new URL(addr);
			URLConnection conn = url.openConnection();
			
			System.out.println("toString:" + conn);
			System.out.println("getAllowUserInteraction: " + conn.getAllowUserInteraction());
			System.out.println("getConnectTimeout: " + conn.getConnectTimeout());
			System.out.println("getContent: " + conn.getContent());
			System.out.println("getContentLength: " + conn.getContentLength());
			System.out.println(conn.getContentType());
			System.out.println(conn.getDate());
			System.out.println(conn.getDefaultAllowUserInteraction());
			System.out.println(conn.getDefaultUseCaches());
			System.out.println(conn.getDoInput());
			System.out.println(conn.getDoOutput());
			System.out.println(conn.getExpiration());
			System.out.println(conn.getHeaderFields());
			System.out.println(conn.getIfModifiedSince());
			System.out.println(conn.getLastModified());
			System.out.println(conn.getReadTimeout());
			System.out.println(conn.getURL());
			System.out.println(conn.getUseCaches());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
