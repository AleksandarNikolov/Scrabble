package com.Server.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import com.Server.Model.Game;

public class PlayerHandler implements Runnable {

	public static ArrayList<PlayerHandler> playerHandlers = new ArrayList<>();
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String clientUserName;

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
	int currentPlayerIndex;
	ArrayList<String> queue = new ArrayList<String>();

	public PlayerHandler(Socket socket) {
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			playerHandlers.add(this);
		} catch (IOException e) {
			e.printStackTrace();
			// closeEverything(socket,bufferedReader,bufferedWriter);
		}
	}

	@Override
	public void run() {

		String messageFromClient;
		broadCastResponse("WELCOME: Here is a command list " + "\n" + "ANNOUNCE (PlayerName) (CHAT)" + "\n"
				+ "REQUESTGAME" + "\n" + "MAKEMOVE WORD (StartCoordinate) (WORD)" + "\n" + "MAKEMOVE SWAP (WORD)");
		while (socket.isConnected()) {
			try {

				messageFromClient = bufferedReader.readLine();
				splitMessage = messageFromClient.split(" ");

				String command = splitMessage[0];

				switch (command) {
				case ANNOUNCE:
					if (splitMessage.length == 2) {
						if (namePlayer1 == splitMessage[1]) {

							broadCastError("ERROR " + UNIT_SEPARATOR + " NameAlreadyTaken");
						} else if (namePlayer1 == null) {

							namePlayer1 = splitMessage[1];
							playerHandlers.get(socket.getLocalPort()).setUserName(splitMessage[1]);
							broadCastResponse("WELCOME " + UNIT_SEPARATOR + splitMessage[1]);
						} else if (namePlayer1 != null) {
							namePlayer2 = splitMessage[1];
							playerHandlers.get(socket.getLocalPort()).setUserName(splitMessage[1]);
						}
					}
					broadCastResponse("Invalid input,  please stick to the aforementioned command layout");
					break;
				case REQUESTGAME:
					if (splitMessage.length == 1) {
						for (PlayerHandler playerHandler : playerHandlers) {
							if (playerHandler.clientUserName.equals(clientUserName)) {
								queue.add(clientUserName);
							} else
								broadCastResponse("Request game error");

						}
						broadCast("Player " + clientUserName + " added to queue");
						if (queue.size() <= 2) {
							broadCast("Current queue : " + queue);
						}else if(queue.size() == 2) {
							game = new Game(queue);
							gameThread = new Thread((Runnable) game);
							gameThread.start();   
							
							broadCast("Game started with players " + queue.get(0) + " and " + queue.get(1));
							
							Random r = new Random();
							currentPlayerIndex = r.nextInt(game.players.size());

							broadCast("Player " + queue.get(currentPlayerIndex) + " goes first");
						} 
						
						
					} else
						broadCastResponse("Request game error");

					break;
				case MAKEMOVE:
					broadCast(game.getBoard().printBoard());
					
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
					break;
				case PLAYERDISCONNECTED:
					// code block
					break;

				case SENDCHAT:
					broadCastChat(NOTIFYCHAT + UNIT_SEPARATOR + splitMessage[1] + UNIT_SEPARATOR + splitMessage[2]);
					break;
				default:
					System.out.println("ERROR: Invalid command");
				}

				// broadCastMessage(messageFromClient);

			} catch (IOException e) {
				e.printStackTrace();
				// closeEverything(socket,bufferedReader,bufferedWriter);
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
						e.printStackTrace();
						// closeEverything(socket,bufferedReader, bufferedWriter);
					}
				}
			}
		}).start();
	}

	public void broadCastChat(String messageToSend) {
		for (PlayerHandler playerHandler : playerHandlers) {
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

	public void broadCastError(String messageToSend) {
		for (PlayerHandler playerHandler : playerHandlers) {
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

		for (PlayerHandler playerHandler : playerHandlers) {
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
		for (PlayerHandler playerHandler : playerHandlers) {
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
		playerHandlers.remove(this);
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
