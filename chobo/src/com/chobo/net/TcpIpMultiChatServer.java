package com.chobo.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class TcpIpMultiChatServer {
	HashMap<String, OutputStream> clients;
	
	TcpIpMultiChatServer() {
		clients = new HashMap<String, OutputStream>();
		Collections.synchronizedMap(clients);
	}
	
	public void start() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while (true) {
				Socket s = server.accept();
				System.out.println(s.getInetAddress() + "에서 접속하였습니다.");
				ServerReceiver receiver = new ServerReceiver(s);
				receiver.start();							
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TcpIpMultiChatServer().start();
	}
	
	class ServerReceiver extends Thread {
		Socket socket = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		public void run() {
			String name = "";
			try {
				if (in != null) {
					name = in.readUTF();
				}
				clients.put(name, out);
				sendAll("[" + name + "]" + "님이 입장하셨습니다.");
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "입니다.");
				
				while (in != null) {
					sendAll(in.readUTF());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				sendAll("[" + name + "]" + "님이 퇴장하셨습니다.");
				clients.remove(name);
				System.out.println(socket.getInetAddress() + "에서 접속을 종료하였습니다.");
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
			}
		}
	}
	
	public void sendAll(String msg) {
		Iterator<String> it = clients.keySet().iterator();
		try {
			while (it.hasNext()) {
				DataOutputStream out = (DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
