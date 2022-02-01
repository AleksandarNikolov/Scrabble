package com.Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	
	private int port;
	private ServerSocket serverSocket;
	private boolean running = false;
	private String name = "";
	
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
		Connection connection = new Connection(socket,name, null);
		ConnectionHandler.players.put(name,connection);
		new Thread(connection).start();
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