package com.Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.Client.Controller.ChatHandler;

public class ChatServer {
	
	private ServerSocket serverSocket;
	
	public ChatServer(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	
	public void startServer() {
		try {
			while(!serverSocket.isClosed()) {
				
				Socket socket = serverSocket.accept();
				System.out.println("New client has connected");
				ChatHandler clientHandler = new ChatHandler(socket);
				
				Thread thread = new Thread(clientHandler);
				thread.start();
				
				
			}
		}catch (IOException e) {
			
		}
	}
	
	public void closeServerSocket() {
		try {
			if(serverSocket != null) {
				serverSocket.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
