package application;

import java.util.ArrayList;
import application.Board.Direction;
import wordchecker.InMemoryScrabbleWordChecker;
import wordchecker.ScrabbleWordChecker;

/**
 * Class for Player in Scrabble
 * 
 * @author Aleksandar Nikolov and Andrei Cohan
 */
public class Player {

	public String name;
	private int id;
	public int score;
	private Deck deck;
	private int currentScore;
	private Player[] players;

	/**
	 * Creates a new Player object.
	 * 
	 * @requires name is not null
	 * @requires score >= 0;
	 * @ensures the Name of this player will be name
	 * @ensures the Score of this player will be score;
	 */
	public Player(int id, String name) {
		this.setName(name);
		this.setId(id);
		this.currentScore = 0;
		this.score = score;
		this.deck = deck;
	}

	/**
	 * Returns the name of the player.
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the deck of the player.
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * Returns the score of the player.
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Determines the field for the next move.
	 * 
	 * @requires board is not null and not full
	 * @ensures the returned in is a valid field index and that field is empty
	 * @param board the current game board
	 * @return the player's choice
	 */
	public Board determineMove(Board board) {
		return board;// to do
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	/**
     * Makes a move on the board. <br>
     * @requires board is not null and not full
     * @param board the current board
	 * @throws Exception 
     */
    public void makeMove(String word, String position, Direction dir,Board board,Player player,ScrabbleWordChecker scrabbleWordChecker) throws Exception {
    	
    	char [] letters = splitWord(word)	;
    	int [] positions = splitPosition(position);
    
    	//check if valid word
    	if(scrabbleWordChecker.isValidWord(word).equals(word)) {
    	//check if word is part of players deck	
    	for(int i = 0 ; i < letters.length; i++) {
    		if(player.getDeck().hasLetter(letters[i])) {
    			//check if move is made horizontally
    			if(dir == Direction.HORIZONTAL) {
    		    	for(int j = 0 ; j < word.length();j++) {
    		    		//add tile with letter in position i of letters[}
    		    		board.getSquare(positions[i],positions[1]).setTile(deck.getTile(letters[i]));
    		    	}
    		    	//check if move is made vertically
    		    	}else if(dir == Direction.VERTICAL) {
    		    		for(int k = 0 ; k < word.length();k++) {
    		    			//add tile with letter in position i of letters[]
    		    			board.getSquare(positions[1],positions[k]).setTile(player.getDeck().getTile(letters[i]));
    		        	}
    		    	}else throw new Exception("Move invalid");
    		    }else throw new Exception("Letters not in deck");
    		}
    	}else throw new Exception("Word is not in the dictionary");
    }

	public char[] splitWord(String word) {
		char[] letters = null;
		for (int i = 0; i < word.length(); i++) {
			letters[i] = word.charAt(i);
		}

		return letters;
	}

	public int[] splitPosition(String position) {
		String str = position;
		int [] positions = null;
		String[] part = str.split("(?<=\\D)(?=\\d)");
		
		try {
			if(part[0].equals("A")){
				positions[0] = 1;
			}if(part[0].equals("B")){
				positions[0] = 2;
			}if(part[0].equals("C")){
				positions[0] = 3;
			}if(part[0].equals("D")){
				positions[0] = 4;
			}if(part[0].equals("E")){
				positions[0] = 5;
			}if(part[0].equals("F")){
				positions[0] = 6;
			}if(part[0].equals("G")){
				positions[0] = 7;
			}if(part[0].equals("H")){
				positions[0] = 8;
			}if(part[0].equals("I")){
				positions[0] = 9;
			}if(part[0].equals("J")){
				positions[0] = 10;
			}if(part[0].equals("K")){
				positions[0] = 11;
			}if(part[0].equals("L")){
				positions[0] = 12;
			}if(part[0].equals("M")){
				positions[0] = 13;
			}if(part[0].equals("N")){
				positions[0] = 14;
			}if(part[0].equals("O")){
				positions[0] = 15;
			}
		}catch (Exception e) {
			System.out.println("invalid input");
		}
		positions[1] = Integer.parseInt(part[1]);
		
		return positions;
	}

	public void skipTurn(ArrayList<String> tilesToReplace) {
		// TODO Auto-generated method stub

	}
	
	public int calculateScore() {
		
		
		
		
		
		
		
		return score;
		
		
		
		
		
		
	}

}
