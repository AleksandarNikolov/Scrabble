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
import exceptions.AlreadyPlacedException;
import exceptions.CommandLineException;
import exceptions.LowerCaseException;
import exceptions.NoTilesInBagException;
import exceptions.OutOfBoundsException;
import exceptions.TilesNotInDeckException;
import exceptions.WordListException;
import wordchecker.FileStreamScrabbleWordChecker;
import wordchecker.InMemoryScrabbleWordChecker;
import wordchecker.ScrabbleWordChecker;

public class Game {
//if all players skip game ends.
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 0;
	public static final boolean DEBUG = true;

	ArrayList<Player> players = new ArrayList<Player>();
	private int currentPlayerIndex;
	Board board = new Board();
	Bag bag = new Bag();

	public Game(String[] playersInfo) {
		// running variable
		boolean running = true;

		// addPlayers to player arrayList
		for (int i = 0; i < playersInfo.length; i++) {
			@SuppressWarnings("unused")
			Player player;
			players.add(player = new Player(i, playersInfo[i]));
		}

		// Generate a board
		board.generateBoard();

		// Generate and shuffle tiles
		bag.generateBag();
		bag.mixBag();

		// Assign tiles to players deck
		for (int i = 0; i < players.size(); i++) {
			Deck deck = new Deck();
			deck.generateDeck(bag.getTiles());
			players.get(i).setDeck(deck);
		}

	}

	public void skipTurn(ArrayList<String> command, Bag bag) {

		ArrayList<Character> tilesToReplace = new ArrayList<Character>();

		for (int i = 1; i < command.size(); i++) {
			tilesToReplace.add(((CharSequence) command).charAt(i));
			for (int j = 0; j < tilesToReplace.size(); j++) {
				players.get(currentPlayerIndex).getDeck().discardTile(tilesToReplace.get(i), bag.getTiles());
				if (currentPlayerIndex == 0) {
					currentPlayerIndex = 1;
				} else if (currentPlayerIndex == 1) {
					currentPlayerIndex = 0;

				}

			}
		}
	}

	public boolean checkAdjacent(String[] command) {
		int adjasentCount = 0;

		// generate command parameters
		String word = command[1];
		String position = command[2];
		String dir = command[3];
		
		//create placement variables
		char[] letters = splitWord(word);
		int[] positions = splitPosition(position);
		int x = positions[0];
		int y = positions[1];
		
		Direction direction;

		if (dir == "H") {
			direction = Direction.HORIZONTAL;
		} else
			direction = Direction.VERTICAL;

		
		board.getSquare(x, y).getTile();
		if(!(board.getSquare((x + 1) , y).getTile() == null) || !(board.isField(x, y))) {
			
		}
		
		
		
		
		
		
		
		
		if (board.getSquare(x, y).getTile() == null) {
			count++;
		} else
			count--;

		for (int i = 1; i < word.length(); i++) {

			if (board.getNextSquare(board.getSquare(x, y), direction).getTile() == null) {
				count++;
			} else
				count--;
		}
		if (count == letters.length) {
			return true;
		} else
			throw new OutOfBoundsException("The word cannot be placed there");
	
		
		for (int i = 1 ; i < Board.SIZE ; i++) {
			for(int j = 1 ; i < Board.SIZE ; i++) {
				
				
				
			}
		}return false;

	}

	public boolean checkWord(String[] command) {
		ScrabbleWordChecker checker = new InMemoryScrabbleWordChecker();
		String word = command[1];

		ScrabbleWordChecker.WordResponse response = checker.isValidWord(word);
		if (response == null) {
			System.out.println("The word \"" + word + "\" is not known in the dictionary!");
			return false;
		} else {
			System.out.println("The word is in the dictionary!");
			return true;
		}

	}

	public boolean checkCommandLinePlace(String[] command) throws CommandLineException {
		// error counters
		int count = 0;
		int wordError = 0;

		// generate command parameters
		String word = command[1];
		String position = command[2];
		String dir = command[3];

		// check if word input is valid

		char[] letters = splitWord(word);
		for (int i = 0; i < letters.length; i++) {
			if (!Character.isUpperCase(letters[i]) || Character.isDigit(letters[i]))
				throw new CommandLineException("Problem with word input");
			wordError = wordError + 1;

		}
		if (wordError == 0) {
			count = count + 1;
		}

		// check if position is valid

		String[] part = position.split("(?<=\\D)(?=\\d)");

		// check letter
		char letter = part[0].charAt(0);
		if (!Character.isUpperCase(letter) || Character.isDigit(letter))
			throw new CommandLineException("Position input is incorrect");
		else
			count = count + 1;

		// check number
		String number = part[1];
		int[] numberInt = new int[1];
		numberInt[0] = Integer.parseInt(number);
		if (!(numberInt[1] % 1 == 0)) {
			throw new CommandLineException("Position input is incorrect");
		} else
			count = count + 1;

		// check direction

		if (dir.equals("H") || dir.equals("V")) {
			System.out.println("");
			count = count + 1;
		} else
			throw new CommandLineException("Direction input is incorrect");

		// return true or false
		if (count == 4) {
			return true;
		} else
			return false;
	}

	public boolean checkCommandLineSkip(String[] command) throws LowerCaseException, CommandLineException {

		// error counters
		int count = 0;
		int tileError = 0;

		// generate command parameters
		String commandString = command[0];

		// check tiles input

		String tilesToSwap = command[1];
		String[] cleanTilesToSwap = tilesToSwap.split(",");
		String StringTilesToSwap = cleanTilesToSwap.toString();
		char[] individualTilesToSwap = splitWord(StringTilesToSwap);

		for (int i = 0; i < individualTilesToSwap.length; i++) {
			if (!Character.isUpperCase(individualTilesToSwap[i]) || Character.isDigit(individualTilesToSwap[i]))
				throw new CommandLineException("Problem with tiles input");
			tileError = tileError + 1;

		}
		if (tileError == 0) {
			count = count + 1;
		}

		// tilesToSwap must be only UPPERCASE letters divided by a ","
		if (tilesToSwap.toLowerCase() != null && !tilesToSwap.contains(",")) {
			throw new LowerCaseException(
					"The parameter of the skip command must be in uppercase and must be separated by commas");
		} else
			count = count + 1;
		
		if(count == 2) {
			return true;
		}else return false;

	}

	public int[] splitPosition(String position) {
		String str = position;
		int[] positions = new int[15];
		String[] part = str.split("(?<=\\D)(?=\\d)");

		try {
			if (part[0].equals("A")) {
				positions[0] = 1;
			}
			if (part[0].equals("B")) {
				positions[0] = 2;
			}
			if (part[0].equals("C")) {
				positions[0] = 3;
			}
			if (part[0].equals("D")) {
				positions[0] = 4;
			}
			if (part[0].equals("E")) {
				positions[0] = 5;
			}
			if (part[0].equals("F")) {
				positions[0] = 6;
			}
			if (part[0].equals("G")) {
				positions[0] = 7;
			}
			if (part[0].equals("H")) {
				positions[0] = 8;
			}
			if (part[0].equals("I")) {
				positions[0] = 9;
			}
			if (part[0].equals("J")) {
				positions[0] = 10;
			}
			if (part[0].equals("K")) {
				positions[0] = 11;
			}
			if (part[0].equals("L")) {
				positions[0] = 12;
			}
			if (part[0].equals("M")) {
				positions[0] = 13;
			}
			if (part[0].equals("N")) {
				positions[0] = 14;
			}
			if (part[0].equals("O")) {
				positions[0] = 15;
			}
		} catch (Exception e) {
			System.out.println("invalid input");
		}
		positions[1] = Integer.parseInt(part[1]);

		return positions;
	}

	public char[] splitWord(String word) {
		char[] letters = new char[word.length()];
		for (int i = 0; i < word.length(); i++) {
			letters[i] = word.charAt(i);
		}

		return letters;
	}

	public void place(String[] command, Player player)
			throws TilesNotInDeckException, AlreadyPlacedException, Exception {

		// score counter
		int score = player.getCurrentScore();
		int multiplierL = 1;
		int multiplierW = 1;

		// generate command parameters
		String word = command[1];
		String position = command[2];
		String dir = command[3];

		char[] letters = splitWord(word);
		int[] positions = splitPosition(position);
		int x = positions[0];
		int y = positions[1];

		/*
		 * if splitWord position on board must be free and the length of the word must
		 * not go out of bound all positions that the word will occupy must be free
		 */
		if (checkWordInDeck(word, player)
				&& (checkSquaresUnocuppied(command, player) || containsUsefullTile(command, player) != null)) {
			int[] usefullTile = containsUsefullTile(command, player);

			if (dir == "H") {
				board.getSquare(x, y).setTile(player.getDeck().getTile(letters[0]));

				multiplierL = multiplierL * board.getSquare(x, y).getLetterMultiplier();
				multiplierW = multiplierL * board.getSquare(x, y).getWordMultiplier();
				score += (player.getDeck().getTile(letters[0]).getValue()) * multiplierL;

				if (usefullTile[0] != 0) {
					player.getDeck().removeTile(player.getDeck().getTile(letters[0]));
				}

				for (int i = 1; i <= word.length(); i++) {
					board.getSquare(x + 1, y).setTile(player.getDeck().getTile(letters[i]));

					multiplierL = multiplierL * board.getSquare(x, y).getLetterMultiplier();
					multiplierW = multiplierL * board.getSquare(x, y).getWordMultiplier();
					score += (player.getDeck().getTile(letters[i]).getValue()) * multiplierL;

					if (usefullTile[i] != i) {
						player.getDeck().removeTile(player.getDeck().getTile(letters[i]));
					}
				}
			} else
				board.getSquare(x, y).setTile(player.getDeck().getTile(letters[0]));

			multiplierL = multiplierL * board.getSquare(x, y).getLetterMultiplier();
			multiplierW = multiplierL * board.getSquare(x, y).getWordMultiplier();
			score += (player.getDeck().getTile(letters[0]).getValue()) * multiplierL;

			if (usefullTile[0] != 0) {
				player.getDeck().removeTile(player.getDeck().getTile(letters[0]));
			}
			for (int i = 1; i <= word.length(); i++) {
				board.getSquare(x, y - 1).setTile(player.getDeck().getTile(letters[i]));

				multiplierL = multiplierL * board.getSquare(x, y).getLetterMultiplier();
				multiplierW = multiplierL * board.getSquare(x, y).getWordMultiplier();
				score += (player.getDeck().getTile(letters[i]).getValue()) * multiplierL;

				if (usefullTile[i] != i) {
					player.getDeck().removeTile(player.getDeck().getTile(letters[i]));
				}
			}

			score = score * multiplierW;
			player.setCurrentScore(score);

			player.getDeck().refillDeck(bag.getTiles());

			nextTurn();
		}

		/*
		 * place the tiles that make up the word, into the corresponding squares delete
		 * the used tiles from the player's deck
		 * 
		 * refill the player's deck check the score for the word add the score to the
		 * player's score
		 */

	}

	public void nextTurn() {
		if (currentPlayerIndex == 0) {
			currentPlayerIndex = 1;
		} else if (currentPlayerIndex == 1) {
			currentPlayerIndex = 0;
		}
	}

	public boolean checkWordInDeck(String word, Player player) throws TilesNotInDeckException {

		char[] letters = splitWord(word);
		int count = 0;
		for (char letter : letters) {
			for (int i = 0; i <= 7; i++) {
				if (player.getDeck().getTileIndex(i).getLetter() == letter) {

					count++;
				} else
					count--;
			}
		}
		if (count == letters.length) {
			return true;
		} else
			throw new TilesNotInDeckException("The word cannot be made with the tiles in the deck");
	}

	public boolean checkSquaresUnocuppied(String[] command, Player player) throws OutOfBoundsException, Exception {
		// generate command parameters
		String word = command[1];
		String position = command[2];
		String dir = command[3];

		int count = 0;
		char[] letters = splitWord(word);
		int[] positions = splitPosition(position);
		int x = positions[0];
		int y = positions[1];
		Direction direction;

		if (dir == "H") {
			direction = Direction.HORIZONTAL;
		} else
			direction = Direction.VERTICAL;

		if (board.getSquare(x, y).getTile() == null) {
			count++;
		} else
			count--;

		for (int i = 1; i < word.length(); i++) {

			if (board.getNextSquare(board.getSquare(x, y), direction).getTile() == null) {
				count++;
			} else
				count--;
		}
		if (count == letters.length) {
			return true;
		} else
			throw new OutOfBoundsException("The word cannot be placed there");

	}

	public int[] containsUsefullTile(String[] command, Player player) throws Exception {

		// generate command parameters
		String word = command[1];
		String position = command[2];
		String dir = command[3];

		int[] usefullTileIndex = new int[word.length()];

		char[] letters = splitWord(word);
		int[] positions = splitPosition(position);
		int x = positions[0];
		int y = positions[1];
		Direction direction;

		if (dir == "H") {
			direction = Direction.HORIZONTAL;
		} else
			direction = Direction.VERTICAL;

		if (board.getSquare(x, y).getTile().equals(new Tile(letters[0]))) {
			usefullTileIndex[0] = 0;
		}

		for (int i = 1; i < word.length(); i++) {

			if (board.getNextSquare(board.getSquare(x, y), direction).getTile().equals(new Tile(letters[i]))) {
				usefullTileIndex[i] = i;

			}
		}

		if (usefullTileIndex != null) {
			return usefullTileIndex;
		} else
			throw new OutOfBoundsException("Cannot place word there");

	}

	public void skip(String[] command, Player player) throws TilesNotInDeckException {

		// individualTilesToSwap must be in the deck
		// must select at least 1 tile to swap

		/*
		 * remove individualTilesToSwap from the deck of the player and store the as
		 * temp refill the deck of the player add the tiles from temp back into the bag
		 * shuffle the bag
		 */

		String tilesToSwap = command[1];
		String[] individualTilesToSwap = tilesToSwap.split(",");
		String word = String.join("", individualTilesToSwap);
		char[] letters = splitWord(word);

		if (checkTilesInDeck(command, player)) {

			for (int i = 0; i < individualTilesToSwap.length; i++) {

				player.getDeck().removeTile(player.getDeck().getTile(letters[i]));

			}
			player.getDeck().refillDeck(bag.getTiles());

			for (int i = 0; i < individualTilesToSwap.length; i++) {
				bag.tiles.add(new Tile(letters[i]));

			}
			Collections.shuffle(bag.getTiles());

			nextTurn();

		}

	}

	public boolean checkTilesInDeck(String[] command, Player player) throws TilesNotInDeckException {

		// generate command parameters
		String tilesToSwap = command[1];
		String[] individualTilesToSwap = tilesToSwap.split(",");
		String word = String.join("", individualTilesToSwap);
		char[] letters = splitWord(word);
		int count = 0;
		for (char letter : letters) {
			for (int i = 0; i <= 7; i++) {
				if (player.getDeck().getTileIndex(i).getLetter() == letter) {

					count++;
				} else
					count--;
			}
		}
		if (count == letters.length) {
			return true;
		} else
			throw new TilesNotInDeckException("The word is too long for the board");
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

	// in scrabble tiles words must be placed while toutching atleast one tile
	public boolean mustHaveAdjacent(String [] command) {
		
		
		
		
		
		
		return false;

	}

	public Player getCurrentPlayer() {
		return players.get(this.currentPlayerIndex);
	}

	public void nextPlayer() {
		this.currentPlayerIndex = (this.currentPlayerIndex + 1) % players.size();
	}

	public List<Tile> pickTiles(int quantity) throws NoTilesInBagException {
		List<Tile> tiles = null;
		for (int i = 0; i < quantity; i++) {
			tiles.add(bag.pickUp());
		}
		return bag.getTiles();
	}

	private int giveScore(Player player, int score) {
		return score;
	}

	/// private int calculateWordPoints(int direction, Square square, Board board,
	/// List<TilePlacements> placements) {
	// }

}
