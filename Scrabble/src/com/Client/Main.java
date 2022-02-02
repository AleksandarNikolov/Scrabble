package com.Client;



import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.Client.Controller.Client;

import application.Player;
import packets.AddConnectionPacket;

public class Main {
	
	static final String ANNOUNCE = "ANNOUNCE";
	static final String WELCOME = "WELCOME";
	static final String REQUESTGAME = "REQUESTGAME";
	static final String INFORMQUEUE = "INFORMQUEUE";
	static final String STARTGAME = "STARTGAME";
	static final String NOTIFYTURN = "NOTIFYTURN";
	static final String MAKEMOVE = "MAKEMOVE";
	static final String NEWTILES = "NEWTILES";
	static final String INFORMMOVE = "INFORMMOVE";
	static final String ERROR = "ERROR";
	static final String GAMEOVER = "GAMEOVER";
	static final String PLAYERDISCONNECTED = "PLAYERDISCONNECTED";
	
	static final String SENDCHAT = "SENDCHAT";
	static final String NOTIFYCHAT = "NOTIFYCHAT";


	public static void main(String[] args) throws UnknownHostException, IOException {
		
		
		
		

				// Assign players to game
				System.out.println("Enter your name");
				// Add player 1
				Scanner sc = new Scanner(System.in);
				String username = sc.nextLine();
				
				Socket socket = new Socket("localhost",8888);
				Client client = new Client(socket, username);
				
				client.connect();
				
				AddConnectionPacket packet = new AddConnectionPacket();
				client.sendObject(packet);
				
				
				
				// Assign players to game
				System.out.println("Enter a command");
				// Add player 1
				String input = sc.nextLine();
				input = input.toUpperCase();
				String [] command = input.split(" ");
				
				
				switch(command[0]) {
				  case ANNOUNCE:
					  
				client.sendObject(input);
				
				client.listenForMessage();
					  
					  break;
				  case WELCOME:
				    // code block
					  break;
				  case REQUESTGAME:
					    // code block
					  break;
				  case INFORMQUEUE:
					    // code block
					  break;
				  case STARTGAME:
						    // code block
					  break;
				  case NOTIFYTURN:
						    // code block
					  break;
				  case MAKEMOVE:
							    // code block
					  break;
				  case NEWTILES:
							    // code block
					  break;
				  case INFORMMOVE:
								    // code block
					  break;
				  case ERROR:
								    // code block
					  break;
				  case GAMEOVER:
					    // code block
					  break;
				  case PLAYERDISCONNECTED:
					    // code block
					  break;
					  
				  case SENDCHAT:
					    // code block
					  break;
				  case NOTIFYCHAT:
					    // code block
					  break;
				  default:
				    // code block
				}
				
				
				if(command.equals("exit")) {
					client.close();
				}
				
		
		
	}

}