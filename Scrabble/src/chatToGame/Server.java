package chatToGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
				PlayerHandler clientHandler = new PlayerHandler(socket);
				
				Thread thread = new Thread(clientHandler);
				thread.start();
				
				
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
