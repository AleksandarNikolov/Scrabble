package application;

import java.awt.Polygon;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import application.Board.Direction;
import wordchecker.InMemoryScrabbleWordChecker;
import wordchecker.ScrabbleWordChecker;
public class Scrabble {
//if all players skip game ends.
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 0;
	public static final int MAXTURNS = 4;

	private ArrayList<Tile> availableTiles;
	private Board board;
	private List<Player> players;

	private Map<Player, List<Tile>> decks = new HashMap<>();
	private Map<Player, Integer> scores = new HashMap<>();
	private int currentPlayerIndex;

	public static void main(String[] args)  {
		boolean running = true;

		// Assign players to game
		System.out.println("Please type player 1 name");
		// Add player 1
		Scanner sc = new Scanner(System.in);
		String playerName = sc.nextLine();
		Player player1 = new Player(0, playerName);

		// Add player 2
		System.out.println("Please type player 2 name");
		playerName = sc.nextLine();
		Player player2 = new Player(1, playerName);

		// Add all players to players list
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);

		// Generate and shuffle tiles
		ArrayList<Tile> availableTiles = new ArrayList<Tile>();
		Bag bag = new Bag();
		availableTiles = bag.tiles;
		Collections.shuffle(availableTiles);

		// Assign tiles to players deck
		for (Player player : players) {
			Deck deck = new Deck();
			player.getDeck().generateDeck(availableTiles);
			deck.generateDeck(availableTiles);
			player.setDeck(deck);
		}

		// Start playing
		// Randomly select starting player
		Random r = new Random();
		int currentPlayerIndex = r.nextInt(players.size());

		while (running) {
			int counter = 0;
			counter ++;

			// split input for move and skip
			try {
				// Generate a board
				Board board = new Board();
				board.reset();
				board.toString(); // to do
				players.get(currentPlayerIndex).getDeck().toString();
				System.out.println("Player" + currentPlayerIndex + 1 + " make your move:");
				String input = sc.nextLine();
				ArrayList<String> command = new ArrayList<>();
				input.split(" ");
				command.add(input);

				if (command.size() > 3) {
					throw new Exception("invalid input");
				} else if (equalsNumber(command.get(1))) {
					throw new Exception("must be only letters");
				}

				// if make move
				if (command.get(1).equals("Place")) {
					String word = command.get(1);
					String position = command.get(2);
					Direction dir = null;

					try {
						if (command.get(3) == "H") {
							Direction dir1 = Direction.HORIZONTAL;
							dir = dir1;
						} else if (command.get(3) == "V") {
							Direction dir1 = Direction.VERTICAL;
							dir = dir1;
						}
					} catch (Exception e) {
						System.out.println("Must specify Vertical or Horzizontal with V or H");
					}
					ScrabbleWordChecker scrabbleWordChecker = new InMemoryScrabbleWordChecker();
					players.get(currentPlayerIndex).makeMove(word, position, dir ,board,players.get(currentPlayerIndex),scrabbleWordChecker);

					players.get(currentPlayerIndex).setCurrentScore(currentPlayerIndex); // to do

					players.get(currentPlayerIndex).getScore();
					players.get(currentPlayerIndex).getDeck().toString();
					board.toString(); // to do
					
				}

				if (currentPlayerIndex == 0) {
					currentPlayerIndex = 1;
				} else if (currentPlayerIndex == 1) {
					currentPlayerIndex = 0;
				}

				// if skip turn
				else if (command.get(0) == "Skip") {

					players.get(currentPlayerIndex).skipTurn(command);/////////////Question
					
					
					if (currentPlayerIndex == 0) {
						currentPlayerIndex = 1;
					} else if (currentPlayerIndex == 1) {
						currentPlayerIndex = 0;
					}

				}
			} catch (Exception e) {
				System.out.println("invalid input");
			}
			
			if(counter == MAXTURNS) {
				running = false;
			}
		}
		int score1 = players.get(0).getScore();
		int score2 = players.get(1).getScore();
		if(score1>score2) {
			System.out.println("Game over , winner is: " + players.get(0).getName());
			
		}else if (score2>score1) {
			System.out.println("Game over , winner is: " + players.get(1).getName());
		}else
		System.out.println("Game over , there is no winner");
	}

	public void skipTurn(ArrayList<String> command) {

		ArrayList<Character> tilesToReplace = new ArrayList<Character>();

		for (int i = 1; i < command.size(); i++) {
			tilesToReplace.add(((CharSequence) command).charAt(i));
			for (int j = 0; j < tilesToReplace.size(); j++) {
				players.get(currentPlayerIndex).getDeck().sacrificeTile(tilesToReplace.get(i), availableTiles);
				if (currentPlayerIndex == 0) {
					currentPlayerIndex = 1;
				} else if (currentPlayerIndex == 1) {
					currentPlayerIndex = 0;

				}

			}
		}
	}

	public static boolean equalsNumber(Object obj) {

		if (!(obj instanceof String)) {
			return true;
		}

		return false;

	}

	public Board getBoard() {
		return board;
	}

	//public int calculateScore(List<TilePlacement> placements) {
	//}

//	public int doMove(List<TilePlacement> placements, Player player) {
//	}

	public Player getCurrentPlayer() {
		return players.get(this.currentPlayerIndex);
	}

	public List<Tile> getTiles(Player player) {
		return Collections.unmodifiableList(decks.get(player));
	}

	public void nextPlayer() {
		this.currentPlayerIndex = (this.currentPlayerIndex + 1) % players.size();
	}

	public List<Tile> pickTiles(int quantity) {
		return availableTiles;
	}

	public boolean isFinished() {
		return decks.values().stream().anyMatch(deck -> deck.size() == 0);
	}

	private int giveScore(Player player, int score) {
		return score;
	}


	///private int calculateWordPoints(int direction, Square square, Board board, List<TilePlacements> placements) {
	//}

}
