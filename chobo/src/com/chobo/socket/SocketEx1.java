package com.chobo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class SocketEx1 {
	public static void main(String args[]) {
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(5000);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			Socket
		}
	}
}
