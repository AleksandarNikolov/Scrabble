package com.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.Server.Controller.PlayerHandler;
import com.Server.Model.Game;

public class Server {
	
	public Game game;
	public int currentPlayer;
	public static ArrayList<PlayerHandler> playerHandlers = new ArrayList<>();
	public ArrayList<String> queue = new ArrayList<>();
	
	public String namePlayer1;
	private ServerSocket serverSocket;
	public int otherPlayerIndex;
	public int currentPlayerIndex;
	
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	
	public void startServer() {
		try {
			System.out.println("Server started on port 8888");
			while(!serverSocket.isClosed()) {
				
				Socket socket = serverSocket.accept();
				System.out.println("New player has connected");
				PlayerHandler playerHandler = new PlayerHandler(socket, null,this);
				playerHandlers.add(playerHandler);
				
				Thread thread = new Thread(playerHandler);
				thread.start();
				playerHandler.listenForPlayerMessage();
				
				
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
	
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}
	public int getOtherPlayerIndex() {
		if(currentPlayerIndex == 1) {
			otherPlayerIndex = 0 ;
			return otherPlayerIndex;
		}else if(currentPlayerIndex == 0)
			otherPlayerIndex = 1;
		return otherPlayerIndex;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(8888);
		Server server = new Server(serverSocket);
		server.startServer();
	}
	
	
	
}
