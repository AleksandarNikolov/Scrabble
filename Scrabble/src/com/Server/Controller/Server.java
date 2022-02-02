package com.Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.Client.Controller.ChatHandler;

public class Server implements Runnable{
	
	private int port;
	private ServerSocket serverSocket;
	private boolean running = false;
	private String name;
	
	public Server(int port) {
		this.port = port;
		
		try {
			serverSocket = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		new Thread(this).start();
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

	@Override
	public void run() {
		running = true;
		System.out.println("Server started on port: " + port);
		
		while(running) {
			try {
				Socket socket = serverSocket.accept();
				initSocket(socket);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		shutdown();
	}
	
	private void initSocket(Socket socket) {
		Player player = new Player(socket,name, null);
		PlayerHandler.players.put(name,player);
		new Thread(player).start();
	}
	
	public void shutdown() {
		running = false;
		
		try {
			serverSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}