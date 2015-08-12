package com.chobo.net;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer {
	public static void main(String[] args) {
		ServerSocket server = null;

		try {
			server = new ServerSocket(7777);

			while (true) {
				System.out.println(getTime() + "연결 대기");
				Socket s = server.accept();
				OutputStream os = s.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);

				System.out.println(getTime() + s.getInetAddress() + "로부터 연결 요청이 들어왔습니다.");
				
				dos.writeUTF("[Notice] Test Message1 from Server");
				System.out.println(getTime() + "데이터를 전송했습니다.");
				
				s.close();
				dos.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		return df.format(new Date());
	}
}
