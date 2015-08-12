package com.chobo.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
	
	public static void main(String[] args) {
		new UdpClient().start();
	}

	public void start() {
		try {
			DatagramSocket sock = new DatagramSocket();
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			
			byte[] msg = new byte[100];
			
			DatagramPacket out = new DatagramPacket(msg, 1, addr, 7777);
			DatagramPacket in = new DatagramPacket(msg, msg.length);
			
			sock.send(out);
			sock.receive(in);
			
			System.out.println("current server msg: " + new String(in.getData()));
			
			sock.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
