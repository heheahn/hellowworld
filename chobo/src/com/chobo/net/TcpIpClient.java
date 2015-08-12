package com.chobo.net;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class TcpIpClient {
	public static void main(String[] args) {
		try {
			String addr = "127.0.0.1";
			System.out.println("연결 중입니다.");
			Socket s = new Socket(addr, 7777);
			InputStream is = s.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			System.out.println("서버로부터 받은 메세지: " + dis.readUTF());
			System.out.println("연결을 종료합니다.");
			
			dis.close();
			s.close();
			System.out.println("연결이 종료되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
