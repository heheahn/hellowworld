package com.chobo.excercise;

public class Excercise15_1 {
	public static void main(String[] args) {
		byte[] ip = {(byte)192, (byte)168, (byte)10, (byte)100};
		byte[] subnet = {(byte)255, (byte)255, (byte)255, (byte)0};
		
		byte[] netAddr = new byte[4];
		byte[] hostAddr = new byte[4];
				
		System.out.println("네트워크 주소:");
		
		for (int ii = 0; ii < ip.length; ii++) {
			netAddr[ii] = (byte)(ip[ii] & subnet[ii]);
			System.out.print(netAddr[ii] >= 0 ? netAddr[ii] : netAddr[ii] + 256);
			System.out.print(".");
		}
		
		System.out.println();
		System.out.println("호스트 주소:");
		
		for (int ii = 0; ii < ip.length; ii++) {
			netAddr[ii] = (byte)(ip[ii] & ~subnet[ii]);
			System.out.print(netAddr[ii] >= 0 ? netAddr[ii] : netAddr[ii] + 256);
			System.out.print(".");
		}
	}
}
