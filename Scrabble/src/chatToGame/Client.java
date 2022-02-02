package chatToGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;

	public Client(Socket socket,String username) {
		
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.username = username;
		}catch (IOException e) {
			e.printStackTrace();
			closeEverything(socket,bufferedReader, bufferedWriter);
		}
		
		
	}
	
	public void sendMessage() {
		try {
			bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			Scanner scanner = new Scanner(System.in);
			while(socket.isConnected()) {
				String messageToSend = scanner.nextLine();
				bufferedWriter.write(username + " : " +messageToSend);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
		}catch (IOException e) {
			e.printStackTrace();
			//closeEverything(socket,bufferedReader, bufferedWriter);
		}
		
	}
	
	public void listenForMessage() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String message;
				while(socket.isConnected()){
					try {
						message = bufferedReader.readLine();
						System.out.println(message);
						
						//String [] splitMessage = message.split(message);
						//String command = splitMessage[0];
						
						
						
						
						
						
						
						
						
					}catch (IOException e) {
						e.printStackTrace();
						//closeEverything(socket,bufferedReader, bufferedWriter);
					}
				}
			}
		}).start();
	}

	public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter) {
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
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter username");
		String username = scanner.nextLine();
		Socket socket = new Socket("localhost",8888);
		Client client = new Client(socket, username);
		client.listenForMessage();
		client.sendMessage();
	}
}
