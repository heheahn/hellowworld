package com.chobo.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class NetworkEx1 {
	public static void main(String[] args) {
		InetAddress ip = null;
		InetAddress[] ipArr = null;
		
		try {
			ip = InetAddress.getByName("www.naver.com");
			System.out.println("getHostName():" + ip.getHostName());
			System.out.println("getHostAddress:" + ip.getHostAddress());
			System.out.println("toString():" + ip.toString());
			
			byte[] ipAddr = ip.getAddress();
			System.out.println("getAddress():" + Arrays.toString(ipAddr));
			
			String result = "";
			for (int ii = 0; ii < ipAddr.length; ii++) {
				result += (ipAddr[ii] > 0) ? ipAddr[ii] : ipAddr[ii] + 256;
				result += ".";
			}
			System.out.println("getAddress()+256:" + result);
			System.out.println();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("getHostName():" + ip.getHostName());
			System.out.println("getHostAddress():" + ip.getHostAddress());
			System.out.println();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			ipArr = InetAddress.getAllByName("www.naver.com");
			for (int ii = 0; ii < ipArr.length; ii++) {
				System.out.println("ipArr[" + ii + "]:" + ipArr[ii]);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
