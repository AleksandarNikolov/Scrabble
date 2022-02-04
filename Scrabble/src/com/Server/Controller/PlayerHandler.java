package com.Server.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import com.Server.Server;
import com.Server.Model.Game;

public class PlayerHandler implements Runnable {
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String clientUserName;
	private Server server;

	public static final String MESSAGE_SEPARATOR = "\u001e";
	public static final String UNIT_SEPARATOR = "\u001f";

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

	public String namePlayer1;
	public String namePlayer2;
	int currentPlayer;
	public String[] players;
	String[] splitMessage;
	Game game;
	Thread gameThread;
	int otherPlayerIndex;
	int currentPlayerIndex;
	boolean gameStarted = false;
	boolean gameRunning = false;
	ArrayList<String> queue = new ArrayList<String>();

	public PlayerHandler(Socket socket, String username, Server server) {
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.clientUserName = username;
			this.server = server;
		} catch (IOException e) {
			
			closeEverything(socket,bufferedReader,bufferedWriter);
		}
	}

	@Override
	public void run() {

		String messageFromClient;
		broadCastResponse(
				"WELCOME: Here is a command list " + "\n" + "ANNOUNCE (PlayerName) (CHAT)" + "\n" + "REQUESTGAME" + "\n"
						+ "MAKEMOVE MOVE WORD (StartCoordinate) (WOR)" + "\n" + "MAKEMOVE SWAP (TILES TO SWAP)");
		while (socket.isConnected()) {
			try {
				if (gameStarted == true && gameRunning == false) {
					int score1 = game.players.get(0).getScore();
					int score2 = game.players.get(1).getScore();
					if (score1 > score2) {
						System.out.println("Game over , winner is: " + game.players.get(0).getName());

					} else if (score2 > score1) {
						System.out.println("Game over , winner is: " + game.players.get(1).getName());
					} else
						System.out.println("Game over , there is no winner");
					gameThread.join();
					socket.close();
				}
				
				messageFromClient = bufferedReader.readLine();
				messageFromClient = messageFromClient.toUpperCase();
				splitMessage = messageFromClient.split(" ");

				String command = splitMessage[0];

				switch (command) {
				case ANNOUNCE:
					if (splitMessage.length == 2) {
						if(Server.playerHandlers.get(0).getUserName() == null ) {
							server.namePlayer1 = splitMessage[1];
							Server.playerHandlers.get(0).setUserName(splitMessage[1]);
							broadCastResponse("WELCOME " + UNIT_SEPARATOR + splitMessage[1]);
						}else if(Server.playerHandlers.get(0).getUserName() != null) {
							
							Server.playerHandlers.get(1).setUserName(splitMessage[1]);
						}
					} else {
						broadCastResponse("Invalid input,  please stick to the aforementioned command layout");
					}
					break;
				case REQUESTGAME:
					if (splitMessage.length == 1) {
						server.queue.add(Server.playerHandlers.get(0).getUserName());
						server.queue.add(Server.playerHandlers.get(1).getUserName());

					}
					broadCast("Player " + clientUserName + " added to queue");
					if (server.queue.size() <= 2) {
						broadCast("Current queue : " + server.queue);
					} 
					if (server.queue.size() == 2) {
						server.game = new Game(server.queue);
						gameThread = new Thread((Runnable) game);
						gameThread.start();

						broadCast("Game started with players " + server.queue.get(0) + " and " + server.queue.get(1));

						Random r = new Random();
						server.currentPlayerIndex = r.nextInt(server.game.players.size());
						gameRunning = true;

						broadCast(server.game.board.printBoard());
						broadCastPlayer1(server.game.players.get(server.getCurrentPlayerIndex()).getDeck().toString());
						broadCastPlayer2(server.game.players.get(server.getOtherPlayerIndex()).getDeck().toString());

						broadCast("Player " + server.queue.get(server.currentPlayerIndex) + " goes first");
					}

					else
						broadCastResponse("Request game error");

					break;
				case MAKEMOVE:
					if (splitMessage[1].equals("MOVE") && server.game.checkCommandLinePlace(splitMessage) == true
							&& server.game.checkWord(splitMessage) == true) {

						server.game.place(splitMessage, server.game.players.get(server.currentPlayerIndex));
						broadCast(server.game.board.printBoard());
						broadCastPlayer1(server.game.players.get(server.getCurrentPlayerIndex()).getDeck().toString());
						broadCastPlayer1("Your current score is" + server.game.players.get(currentPlayerIndex).getCurrentScore());
						broadCastPlayer2(server.game.players.get(server.getOtherPlayerIndex()).getDeck().toString());
						broadCastPlayer1("Your current score is" + server.game.players.get(otherPlayerIndex).getCurrentScore());

						broadCast("INFORMMOVE " + splitMessage[1] + " " + splitMessage[2] + " " + splitMessage[3] + " "
								+ splitMessage[4]);

					} else if (splitMessage[1].equals("SWAP") && server.game.checkCommandLineSkip(splitMessage) == true)

						server.game.skip(splitMessage, server.game.players.get(server.currentPlayerIndex));
					broadCast(server.game.board.printBoard());
					broadCastPlayer1(server.game.players.get(server.getCurrentPlayerIndex()).getDeck().toString());
					broadCastPlayer2(server.game.players.get(server.getOtherPlayerIndex()).getDeck().toString());

					broadCast("INFORMMOVE " + splitMessage[1] + " " + splitMessage[2]);

					if (server.game.players.get(0).skipped && server.game.players.get(1).skipped) {
						broadCast("GAMEOVER");
						gameRunning = false;

					}

					if (server.currentPlayerIndex == 0) {
						server.currentPlayerIndex = 1;
					} else if (server.currentPlayerIndex == 1) {
						server.currentPlayerIndex = 0;
					}

					break;
				case SENDCHAT:
					broadCastChat(NOTIFYCHAT + UNIT_SEPARATOR + clientUserName + UNIT_SEPARATOR + splitMessage[2]);
					break;
				default:
					System.out.println("ERROR: Invalid command");
				}

				// broadCastMessage(messageFromClient);

			} catch (IOException e) {
				
				closeEverything(socket,bufferedReader,bufferedWriter);
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			}

		}

	}

	public String getUserName() {
		return this.clientUserName;
	}

	public void setUserName(String userName) {
		this.clientUserName = userName;
	}

	public void listenForPlayerMessage() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String message;
				while (socket.isConnected()) {
					try {
						message = bufferedReader.readLine();
						System.out.println(message);

						// String [] splitMessage = message.split(message);
						// String command = splitMessage[0];

					} catch (IOException e) {
						
						closeEverything(socket,bufferedReader, bufferedWriter);
					}
				}
			}
		}).start();
	}

	public void broadCastChat(String messageToSend) {
		for (PlayerHandler playerHandler : Server.playerHandlers) {
			try {
				if (!playerHandler.clientUserName.equals(clientUserName)) {
					playerHandler.bufferedWriter.write(messageToSend);
					playerHandler.bufferedWriter.newLine();
					playerHandler.bufferedWriter.flush();
				}
			} catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
			}
		}
	}
	
	public void broadCastPlayer1(String messageToSend) {
		for (PlayerHandler playerHandler : Server.playerHandlers) {
			try {
				if (playerHandler.clientUserName.equals(Server.playerHandlers.get(server.getCurrentPlayerIndex()).getUserName())) {
					playerHandler.bufferedWriter.write(messageToSend);
					playerHandler.bufferedWriter.newLine();
					playerHandler.bufferedWriter.flush();
				}
			} catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
			}
		}
	}
	
	public void broadCastPlayer2(String messageToSend) {
		for (PlayerHandler playerHandler : Server.playerHandlers) {
			try {
				if (playerHandler.clientUserName.equals(Server.playerHandlers.get(server.getOtherPlayerIndex()).getUserName())) {
					playerHandler.bufferedWriter.write(messageToSend);
					playerHandler.bufferedWriter.newLine();
					playerHandler.bufferedWriter.flush();
				}
			} catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
			}
		}
	}

	public void broadCastError(String messageToSend) {
		for (PlayerHandler playerHandler : Server.playerHandlers) {
			try {
				if (playerHandler.clientUserName.equals(clientUserName)) {
					playerHandler.bufferedWriter.write(messageToSend);
					playerHandler.bufferedWriter.newLine();
					playerHandler.bufferedWriter.flush();
				}
			} catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
			}
		}
	}

	public void broadCast(String messageToSend) {

		for (PlayerHandler playerHandler : Server.playerHandlers) {
			try {

				playerHandler.bufferedWriter.write(messageToSend);
				playerHandler.bufferedWriter.newLine();
				playerHandler.bufferedWriter.flush();

			} catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
			}
		}

	}

	public void broadCastResponse(String messageToSend) {
		for (PlayerHandler playerHandler : Server.playerHandlers) {
			try {
				if (playerHandler.socket.equals(socket)) {
					playerHandler.bufferedWriter.write(messageToSend);
					playerHandler.bufferedWriter.newLine();
					playerHandler.bufferedWriter.flush();
				}
			} catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
			}
		}
	}

	public void removeClientHandler() {
		Server.playerHandlers.remove(this);
		broadCastResponse("Server" + clientUserName + " has left");
	}

	public boolean messageChecker(String[] splitMessage) {
		if (splitMessage.length > 2) {
			return false;
		}
		return true;

	}

	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		removeClientHandler();
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (bufferedWriter != null) {
				bufferedReader.close();
			}
			if (socket != null) {
				bufferedReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			;
		}
	}

}
