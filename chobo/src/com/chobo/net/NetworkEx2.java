package com.chobo.net;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class NetworkEx2 {
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("http://www.javachobo.com:80/sample/" + "hellow.html?referer=javachobo#index1");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("getAuthority(): " + url.getAuthority());
		System.out.println("getDefaultPort() : " + url.getDefaultPort());
		System.out.println(url.getPort());
		System.out.println(url.getFile());
		System.out.println(url.getHost());
		System.out.println(url.getPath());
		System.out.println(url.getProtocol());
		System.out.println(url.getQuery());
		System.out.println(url.getRef());
		System.out.println(url.getUserInfo());
		System.out.println(url.toExternalForm());
		try {
			System.out.println(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
