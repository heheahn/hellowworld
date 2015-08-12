package com.chobo.net;

import java.net.Socket;

public class TcpIpClient5 {
	public static void main(String[] args) {
		Socket s = null;
		String addr = "127.0.0.1";
		
		try {
			s = new Socket(addr, 7777);
			System.out.println("서버에 연결되었습니다.");
			
			Sender sender = new Sender(s);
			Receiver receiver = new Receiver(s);
			
			sender.start();
			receiver.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
