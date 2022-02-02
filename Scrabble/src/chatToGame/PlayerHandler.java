package chatToGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class PlayerHandler implements Runnable {
	
	
	public static ArrayList<PlayerHandler> playerHandlers = new ArrayList<>();
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String clientUserName;
	
	
	public PlayerHandler(Socket socket) {
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.clientUserName = bufferedReader.readLine();
			playerHandlers.add(this);
			broadCastMessage("WELCOME: " + clientUserName);
			
		}catch (IOException e) {
			closeEverything(socket,bufferedReader,bufferedWriter);
		}
	}

	@Override
	public void run() {
		
	String messageFromClient;
	while(socket.isConnected()) {
		try {
			
			messageFromClient = bufferedReader.readLine();
			

			String [] splitMessage = messageFromClient.split(" ");
			String command = splitMessage[0];
			
			
			
			
			
		
			
			
			//broadCastMessage(messageFromClient);
			
			
			
			
			
			
		}catch(IOException e) {
			closeEverything(socket,bufferedReader,bufferedWriter);
			break;
		}
		
		
	}
		
	}
	public void broadCastMessage(String messageToSend) {
		for(PlayerHandler playerHandler : playerHandlers) {
			try {
				if(!playerHandler.clientUserName.equals(clientUserName)) {
					playerHandler.bufferedWriter.write(messageToSend);
					playerHandler.bufferedWriter.newLine();
					playerHandler.bufferedWriter.flush();
				}
			}catch(IOException e) {
				closeEverything(socket,bufferedReader,bufferedWriter);
			}
		}
	}
	
	public void removeClientHandler() {
		playerHandlers.remove(this);
		broadCastMessage("Server" + clientUserName + " has left");
	}
	
	public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter) {
		removeClientHandler();
		try {
			if(bufferedReader != null) {
				bufferedReader.close();
			}
			if(bufferedWriter != null) {
				bufferedReader.close();
			}
			if(socket != null) {
				bufferedReader.close();
			}
		}catch (IOException e) {
			e.printStackTrace();;
		}
	}

}
