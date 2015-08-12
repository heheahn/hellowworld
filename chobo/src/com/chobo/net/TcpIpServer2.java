package com.chobo.net;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer2 {
	public static void main(String[] args) {
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println(getTime() + "서버가 준비되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		while (true) {
			try {
				System.out.println("연결 대기중입니다.");
				Socket s = server.accept();
				System.out.println(getTime() + s.getInetAddress() + "로부터 연결 요청이 들어왔습니다.");
				System.out.println("getPort(): " + s.getPort());
				System.out.println("getLocalPort(): " + s.getLocalPort());
				
				OutputStream os = s.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF("[Notice] Test Message from Server");
				System.out.println(getTime() + "데이터를 전송했습니다.");
				
				dos.close();
				s.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}
	
	static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("[hh:mm:ss] ");
		return df.format(new Date());
	}
}
