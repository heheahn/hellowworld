package com.chobo.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpIpMultiChatClient {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java TcpIpMultiChatClient 대화명");
			System.exit(0);
		}
		
		try {
			String addr = "127.0.0.1";
			Socket s = new Socket(addr, 7777);
			System.out.println("서버와 접속되었습니다.");
			
			ClientSender sender = new ClientSender(s, args[0]);
			ClientReceiver receiver = new ClientReceiver(s);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

class ClientSender extends Thread {
	Socket socket = null;
	DataOutputStream out = null;
	String name = "";
	
	ClientSender(Socket s, String name) {
		this.socket = s;
		this.name = name;
		try {
			out = new DataOutputStream(s.getOutputStream());			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void run() {
		Scanner in = new Scanner(System.in);
		try {
			if (out != null) {
				out.writeUTF(name);
			}
			
			while (out != null) {
				out.writeUTF("[" + name + "] " + in.nextLine());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			in.close();
		}
	}
}

class ClientReceiver extends Thread {
	Socket socket = null;
	DataInputStream in = null;
	
	ClientReceiver(Socket s) {
		this.socket = s;
		try {
			in = new DataInputStream(s.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void run() {
		try {
			while (in != null) {
				System.out.println(in.readUTF());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}