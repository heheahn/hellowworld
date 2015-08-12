package com.chobo.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpIpServer5 {
	public static void main(String[] args) {
		ServerSocket server = null;

		try {
			server = new ServerSocket(7777);
			Socket s = server.accept();
			
			Sender sender = new Sender(s);
			Receiver receiver = new Receiver(s);
			
			sender.start();
			receiver.start();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

class Sender extends Thread {
	Socket socket;
	DataOutputStream os;
	String name;

	Sender(Socket s) {
		try {
			this.socket = s;
			this.os = new DataOutputStream(s.getOutputStream());
			this.name = "[" + s.getInetAddress() + ":" + s.getPort() + "]";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void run() {
		Scanner in = new Scanner(System.in);
		try {
			while (os != null) {
				os.writeUTF(name + in.nextLine());
			}
			os.close();
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

class Receiver extends Thread {
	Socket socket;
	DataInputStream is;
	String name;
	
	Receiver (Socket s) {
		try {
			this.socket = s;
			this.is = new DataInputStream(s.getInputStream());			
			this.name = "[" + s.getInetAddress() + ":" + s.getPort() + "]";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void run() {
		try {
			while (is != null) {
				System.out.println(name + is.readUTF());
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}