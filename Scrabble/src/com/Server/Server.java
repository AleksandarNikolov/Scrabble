package com.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.Server.Controller.PlayerHandler;

public class Server {
	
	private ServerSocket serverSocket;
	
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	
	public void startServer() {
		try {
			
			while(!serverSocket.isClosed()) {
				
				Socket socket = serverSocket.accept();
				System.out.println("New player has connected");
				PlayerHandler clientHandler = new PlayerHandler(socket, null);
				
				Thread thread = new Thread(clientHandler);
				thread.start();
				clientHandler.listenForPlayerMessage();
				
				
			}
		}catch (IOException e) {
			e.printStackTrace();
			
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
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(8888);
		Server server = new Server(serverSocket);
		server.startServer();
	}
	
	
	
}
