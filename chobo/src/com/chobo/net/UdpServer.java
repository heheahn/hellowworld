package com.chobo.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	public static void main(String[] args) {
		new UdpServer().start();
	}

	public void start() {
		try {
			DatagramSocket sock = new DatagramSocket(7777);
			DatagramPacket in, out;

			byte[] inMsg = new byte[10];
			byte[] outMsg;

			while (true) {
				in = new DatagramPacket(inMsg, inMsg.length);
				sock.receive(in);

				InetAddress addr = in.getAddress();
				int port = in.getPort();
				
				SimpleDateFormat df = new SimpleDateFormat("[hh:mm:ss]");
				String now = df.format(new Date());
				outMsg = now.getBytes();
				
				out = new DatagramPacket(outMsg, outMsg.length, addr, port);
				sock.send(out);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
