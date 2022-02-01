package com.Server;

import java.net.SocketException;
import java.util.Scanner;

import com.Server.Controller.Server;



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
	
	
	public static synchronized void main(String[] args) throws Exception {
		
		Server server = new Server(5000);
		server.start();
		

		Scanner sc = new Scanner(System.in);	
		
		//Ask for command
		System.out.println("Enter a command");
		// Add player 1
		String input = sc.nextLine();
		input = input.toUpperCase();
		String [] command = input.split(" ");
		
		
		
		
		
		switch(command[0]) {
		  case ANNOUNCE:
		    // code block
			  break;
		  case WELCOME:
			  
			  
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
			server.shutdown();
			throw new SocketException("Server shutdown");
		}
		
	}

}