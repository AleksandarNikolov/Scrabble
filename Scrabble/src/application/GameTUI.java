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
			System.out.println("Input should resemble either");
			System.out.println("place word position direction Example => place car H8 H  ");
			System.out.println("skip tiles    Example => skip A,B,C ");
			System.out.println("exit");
			System.out.println("Player " + game.players.get(currentPlayerIndex).getName() + " your current score is " + game.players.get(currentPlayerIndex).getCurrentScore());
			System.out.println("Player " + game.players.get(currentPlayerIndex).getName() + " make your move:");
			
			String input = sc.nextLine();
			input = input.toUpperCase();
			
			// command arrayList
			String[] command;
			command = input.split(" ");
			// generate command parameters
			String choice = command[0];

		

				// if place
				if (choice.equals("PLACE") && game.checkCommandLinePlace(command)==true && game.checkWord(command)==true) {
					game.place(command, game.players.get(currentPlayerIndex));
					if (currentPlayerIndex == 0) {
						currentPlayerIndex = 1;
					} else if (currentPlayerIndex == 1) {
						currentPlayerIndex = 0;
					}
					System.out.println("Player " + game.players.get(currentPlayerIndex).getName() + " your current score is " + game.players.get(currentPlayerIndex).getCurrentScore());
				}

				// if skip
				else if (choice.equals("SKIP") && game.checkCommandLineSkip(command)==true) {
					game.skip(command, game.players.get(currentPlayerIndex));
					game.players.get(currentPlayerIndex).setSkippedTrue();
					if(game.players.get(0).skipped && game.players.get(1).skipped) {
						running = false;
					}
					if (currentPlayerIndex == 0) {
						currentPlayerIndex = 1;
					} else if (currentPlayerIndex == 1) {
						currentPlayerIndex = 0;
					}
				}			
				else if(choice.equals("EXIT")) {
				running = false;
				}
				else {
					System.out.print("The first command should be either place, skip or exit");
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
