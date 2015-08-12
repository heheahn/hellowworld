package com.chobo.net;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer4 implements Runnable {
	ServerSocket server = null;
	Thread[] threads = null;
	
	public static void main(String[] args) {
		TcpIpServer4 tis = new TcpIpServer4(5);
		tis.start();
	}
	
	TcpIpServer4(int num) {
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			threads = new Thread[num];			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void start() {
		for ( int ii = 0; ii < threads.length; ii++ ) {
			threads[ii] = new Thread(this);
			threads[ii].start();
		}
	}
	
	public void run() {
		while (true) {
			try {
				System.out.println(getTime() + "가 접속 대기 중입니다.");
				Socket s = server.accept();
				System.out.println(getTime() + s.getInetAddress() + "로부터 접속 요청이 들어왔습니다.");				
				
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
		String threadName = Thread.currentThread().getName();
		SimpleDateFormat df = new SimpleDateFormat("[hh:mm:ss]");
		return df.format(new Date()) + threadName;
	}
}
