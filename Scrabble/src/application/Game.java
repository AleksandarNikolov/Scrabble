package application;

import java.awt.Polygon;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
import exceptions.PlacementException;
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
	int turn = 1;

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

	/**
	 * 
	 * @param command
	 * @return true if the word to place is adjacent to any other word or letter.
	 *         Requirement in scrabble rules.
	 * @return false if the word to place is not adjacent to any other word or
	 *         letter.
	 * @throws Exception
	 */
	public boolean checkAdjacent(String[] command) throws Exception {
		int notAdjasentCount = 0;
		int adjasentCount = 0;

		// generate command parameters
		String word = command[1];
		String position = command[2];
		String dir = command[3];

		// create placement variables
		char[] letters = splitWord(word);
		int[] positions = splitPosition(position);
		int x = positions[0];
		int y = positions[1];
		Direction direction;

		// if its the first turn the the first tile must be placed on the center square.
		if (turn == 1 && x == 8 && y == 8) {
			return true;

		} else if (turn == 1 && (x != 8 || y != 8)) {

			System.out.println("On the first turn you must place the first tile in the center ");
		} else if (dir == "H") {
			direction = Direction.HORIZONTAL;
			// check for every tile in the word.
			// ceck that every tile adjacent to this tile, either doenst have a tile or is
			// off the board.
			for (int i = 0; i < word.length(); i++) {

				if (!(board.getSquare((x + 1), y).getTile() == null) || !(board.isField(x, y))) {
					adjasentCount++;
				}
				if (!(board.getSquare((x - 1), y).getTile() == null) || !(board.isField(x, y))) {
					adjasentCount++;
				}
				if (!(board.getSquare(x, y + 1).getTile() == null) || !(board.isField(x, y))) {
					adjasentCount++;
				}
				if (!(board.getSquare(x, (y - 1)).getTile() == null) || !(board.isField(x, y))) {
					adjasentCount++;
				}
				x++;
			}
		} else {
			direction = Direction.VERTICAL;
			for (int i = 0; i < word.length(); i++) {

				if (!(board.getSquare((x + 1), y).getTile() == null) || !(board.isField(x, y))) {
					adjasentCount++;
				}
				if (!(board.getSquare((x - 1), y).getTile() == null) || !(board.isField(x, y))) {
					adjasentCount++;
				}
				if (!(board.getSquare(x, y + 1).getTile() == null) || !(board.isField(x, y))) {
					adjasentCount++;
				}
				if (!(board.getSquare(x, (y - 1)).getTile() == null) || !(board.isField(x, y))) {
					adjasentCount++;
				}
				y++;
			}
		}
		// if there is a single adjacent tile
		// you can place here
		if (adjasentCount > 0) {
			return true;
		} else
			System.out.println("Word should be placed next to an existing word");
		;
		return false;
	}

	// checks the word with an inMemoryScrabbleWordCheker
	public boolean checkWord(String[] command) {
		ScrabbleWordChecker checker = new InMemoryScrabbleWordChecker();
		String word = command[1];

		ScrabbleWordChecker.WordResponse response = checker.isValidWord(word);
		if (response == null) {
			System.out.println("The word \"" + word + "\" is not a valid scrabble word");
			return false;
		} else {
			System.out.println("The word is in the dictionary!");
			return true;
		}

	}

	// check that the command that was inputed is correct
	public boolean checkCommandLinePlace(String[] command) throws CommandLineException {
		// error counters
		int count = 0;
		int wordError = 0;

		// generate command parameters
		String choice = command[0];
		String word = command[1];
		String position = command[2];
		String dir = command[3];

		// check choice
		if (choice.equals("PLACE")) {
			count++;
		}
		if (choice.equals("SKIP")) {
			count++;
		}

		// check if word input is valid

		char[] letters = splitWord(word);
		for (int i = 0; i < letters.length; i++) {
			if (!Character.isUpperCase(letters[i]) || Character.isDigit(letters[i])) {
				// throw new CommandLineException("Problem with word input");
				System.out.println("Problem with word input");
				wordError = wordError + 1;

			}

		}
		if (wordError == 0) {
			count = count + 1;

		}

		// check if position is valid

		String[] part = position.split("(?<=\\D)(?=\\d)");

		// check letter
		char letter = part[0].charAt(0);
		if (!Character.isUpperCase(letter) || Character.isDigit(letter))
			// throw new CommandLineException("Position input is incorrect");
			System.out.println("Position input is incorrect");
		else
			count = count + 1;

		// check number
		String number = part[1];
		int[] numberInt = new int[1];
		numberInt[0] = Integer.parseInt(number);
		if ((numberInt[0] % 1 == 0)) {

			count = count + 1;

		} else
			// throw new CommandLineException("Position input is incorrect");
			System.out.println("Position input is incorrect");

		// check direction

		if (dir.equals("H") || dir.equals("V")) {
			count = count + 1;

		} else
			// throw new CommandLineException("Problem with word input");
			System.out.println("Problem with word input");

		// return true or false
		if (count == 5) {

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
		String StringTilesToSwap = cleanTilesToSwap[0].toString();
		char[] individualTilesToSwap = splitWord(StringTilesToSwap);

		// for each letter in the word. check that they are formated correctly
		for (int i = 0; i < individualTilesToSwap.length; i++) {
			if (!Character.isUpperCase(individualTilesToSwap[i]) || Character.isDigit(individualTilesToSwap[i])) {
				System.out.println(" tile");
				tileError = tileError + 1;
				// throw new CommandLineException("Problem with tiles input");
			}

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

		if (count == 2) {
			return true;
		} else
			return false;

	}

	// changes the position input from a string for a character and a number to an
	// int array of positions
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

	// splits the word into a char array of letters
	public char[] splitWord(String word) {
		char[] letters = new char[word.length()];
		for (int i = 0; i < word.length(); i++) {
			letters[i] = word.charAt(i);
		}

		return letters;
	}

	// place a word at the given position with the given direction
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
		System.out.println(checkWordInDeck(word, player));
		System.out.println(checkSquaresUnocuppied(command, player));
		System.out.println(containsUsefullTile(command, player) != null);
		if (checkWordInDeck(word, player)
				&& (checkSquaresUnocuppied(command, player) || containsUsefullTile(command, player) != null)) {

			int[] usefullTile = containsUsefullTile(command, player);
			// for vertical placement
			if (dir.equals("V")) {
				// place first letter
				board.getSquare(x, y).setTile(player.getDeck().getTile(letters[0]));
				// get the value of the letter and the multiplier of the tile
				multiplierL = multiplierL * board.getSquare(x, y).getLetterMultiplier();
				multiplierW = multiplierL * board.getSquare(x, y).getWordMultiplier();
				// add it up into a score for the tile
				score += (player.getDeck().getTile(letters[0]).getValue()) * multiplierL;

				// if the tile is a useful tile, dont remove it from the deck.
				// this is because the correct letter is already on that specific square
				if (usefullTile[0] != 0) {
					player.getDeck().removeTile(player.getDeck().getTile(letters[0]));
				}

				// place for the remaning letters
				for (int i = 1; i < word.length(); i++) {
					board.getSquare((x - i), y).setTile(player.getDeck().getTile(letters[i]));

					// get the value of the letter and the multiplier of the tile
					multiplierL = multiplierL * board.getSquare(x, y).getLetterMultiplier();
					multiplierW = multiplierL * board.getSquare(x, y).getWordMultiplier();

					// add it all up into a score for the tile
					score += (player.getDeck().getTile(letters[i]).getValue()) * multiplierL;

					// if the tile is a useful tile, dont remove it from the deck.
					// this is because the correct letter is already on that specific square
					if (usefullTile[i] != i) {
						player.getDeck().removeTile(player.getDeck().getTile(letters[i]));
					}
				}
				// for horizontal placement
			} else {
				// place first lettter
				board.getSquare(x, y).setTile(player.getDeck().getTile(letters[0]));
				// get the value of the letter and the multiplier of the tile
				multiplierL = multiplierL * board.getSquare(x, y).getLetterMultiplier();
				multiplierW = multiplierL * board.getSquare(x, y).getWordMultiplier();

				// add it all up into a score for the tile
				score += (player.getDeck().getTile(letters[0]).getValue()) * multiplierL;

				// if the tile is a useful tile, dont remove it from the deck.
				// this is because the correct letter is already on that specific square

				if (usefullTile[0] != 0) {
					player.getDeck().removeTile(player.getDeck().getTile(letters[0]));
				}

				for (int i = 1; i < word.length(); i++) {
					board.getSquare(x, (y + i)).setTile(player.getDeck().getTile(letters[i]));
					// get the value of the letter and the multiplier of the tile
					multiplierL = multiplierL * board.getSquare(x, y).getLetterMultiplier();
					multiplierW = multiplierL * board.getSquare(x, y).getWordMultiplier();

					// add it all up into a score for the tile
					score += (player.getDeck().getTile(letters[i]).getValue()) * multiplierL;

					// if the tile is a useful tile, dont remove it from the deck.
					// this is because the correct letter is already on that specific square

					if (usefullTile[i] != i) {
						player.getDeck().removeTile(player.getDeck().getTile(letters[i]));
					}
				}
			}
			//add up the final score of this number
			//the letter multipliers are multiplied by the letter values
			//the word multipliers are multiplied together
			//at the end we just multiply the added up score from all the letters with the word multiplier
			//if there was no special tile for example, the world multipler will just be 1
			score = score * multiplierW;
			player.setCurrentScore(score);

			//refil the deck of the player with new tiles from the bag
			player.getDeck().refillDeck(bag.getTiles());

		}
	}

	/**
	 * check the word can be made with words from the deck.
	 * specifically this checks if the tile that is going to be placed on the board is currently in the deck.
	 * @param word
	 * @param player
	 * @return
	 */
	public boolean checkWordInDeck(String word, Player player) {
		//check for every letter in the word
		char[] letters = splitWord(word);
		int count = 0;
		for (int i = 0; i < letters.length; i++) {
			for (int j = 0; j < 7; j++) {
				//if the tile is in the deck add 1 to the counter
				if (player.getDeck().getTileIndex(j).getLetter() == letters[i]) {
					count++;
				}
			}
		}
		// the counter is equal or greater than the letters.length that means all the tiles needed to make the word exist and there could even be extra.
		if (count >= letters.length) {
			return true;
		} else
			return false;
	}

	//check that the square where we wlll place a tile is not occupied by a tile that is not a useful tile
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

	//check if any square in the position of the word contain usefultiles.
	//if a tile is a usefull tile it means that it is already part of the word at the correct position
	//so when placing the tiles, the specific tile does not need to be placed again.
	// meaning it can stay in the deck of the player
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

		if (board.getSquare(x, y).getTile() != null) {
			if (board.getSquare(x, y).getTile().equals(new Tile(letters[0]))) {
				usefullTileIndex[0] = 0;
			}

			for (int i = 1; i < word.length(); i++) {

				if (board.getNextSquare(board.getSquare(x, y), direction).getTile().equals(new Tile(letters[i]))) {
					usefullTileIndex[i] = i;

				}
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

				player.getDeck().removeTile(player.getDeck().removeTile(letters[i]));

			}
			player.getDeck().refillDeck(bag.getTiles());

			for (int i = 0; i < individualTilesToSwap.length; i++) {
				bag.tiles.add(new Tile(letters[i]));

			}
			Collections.shuffle(bag.getTiles());

		}

	}
	//similar to word in deck but is used for the skip command
	//checks that the tiles reuested to replace are in the deck
	//return true if all tiles requested are in deck 
	//return false if one of the is not
	public boolean checkTilesInDeck(String[] command, Player player) throws TilesNotInDeckException {

		// generate command parameters
		String tilesToSwap = command[1];
		String[] individualTilesToSwap = tilesToSwap.split(",");
		String word = "";
		for (int i = 0; i < individualTilesToSwap.length; i++) {
			word = word + individualTilesToSwap[i];
		}
		char[] letters = splitWord(word);
		int count = 0;
		for (int i = 0; i < letters.length; i++) {
			for (int j = 0; j < 7; j++) {
				if (player.getDeck().getTileIndex(j).getLetter() == letters[i]) {

					count++;
				}

			}
		}
		if (count >= letters.length) {
			return true;
		} else
			return false;
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
	public boolean mustHaveAdjacent(String[] command) {

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
