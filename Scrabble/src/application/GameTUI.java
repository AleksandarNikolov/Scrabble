package application;

import java.util.Random;
import java.util.Scanner;

import exceptions.AlreadyPlacedException;
import exceptions.TilesNotInDeckException;

public class GameTUI {

	@SuppressWarnings("null")
	public static void main(String[] args) throws TilesNotInDeckException, AlreadyPlacedException, Exception {

		boolean running = true;

		int MAXPLAYERS = 2;
		// Add all players to players list
		String[] playersInfo = new String[MAXPLAYERS];

		// Assign players to game
		System.out.println("Please type player 1 name");
		// Add player 1
		Scanner sc = new Scanner(System.in);
		String playerName = sc.nextLine();
		playersInfo[0] = playerName;
		// Add player 2
		System.out.println("Please type player 2 name");
		playerName = sc.nextLine();
		playersInfo[1] = playerName;

		// generate a game with the players
		Game game = new Game(playersInfo);

		// Start playing
		// Randomly select starting player
		Random r = new Random();
		int currentPlayerIndex = r.nextInt(game.players.size());

		while (running) {

			// split input for move and skip
			// print board
			System.out.println(game.board.printBoard());
			System.out.println(game.players.get(currentPlayerIndex).getDeck().toString());
			System.out.println("Player " + game.players.get(currentPlayerIndex).getName() + " make your move:");
			String input = sc.nextLine();
			input = input.toUpperCase();
			
			// command arrayList
			String[] command;
			command = input.split(" ");
			game.checkWord(command);
			// generate command parameters
			String choice = command[0];

			if (game.checkWord(command) && game.checkCommandLinePlace(command) && game.checkCommandLineSkip(command)) {

				// if place
				if (choice.equals("PLACE")) {
					game.checkCommandLinePlace(command);
					game.place(command, game.players.get(currentPlayerIndex));
				}

				// if skip
				if (choice.equals("SKIP")) {
					game.checkCommandLineSkip(command);
					game.skip(command, game.players.get(currentPlayerIndex));
				}
				
			}

			

		}

		int score1 = game.players.get(0).getScore();
		int score2 = game.players.get(1).getScore();
		if (score1 > score2) {
			System.out.println("Game over , winner is: " + game.players.get(0).getName());

		} else if (score2 > score1) {
			System.out.println("Game over , winner is: " + game.players.get(1).getName());
		} else
			System.out.println("Game over , there is no winner");
		sc.close();
	}

}
