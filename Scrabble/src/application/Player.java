package application;

import java.util.ArrayList;
import application.Board.Direction;
import wordchecker.ScrabbleWordChecker;

/**
 * Class for Player in Scrabble
 * 
 * @author Aleksandar Nikolov and Andrei Cohan
 */
public class Player {

	public String name;
	private int id;
	public Score score;
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
	public Score getScore() {
		return score;
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
     */
    public void makeMove(String word, String position, Direction dir) {
    	
    	char [] letters = splitWord(word)	;
    	String [] positions = splitPosition(position);
    
    	//check if valid word
    	if(ScrabbleWordChecker.isValidWord(word).equals(word)) {
    	//check if word is part of players deck	
    	for(int i = 0 ; i < letter.length; i++) {
    		if(currentPlayerIndex.getDeck.hasLetter(letters[i])) {
    			//check if move is made horizontally
    			if(dir == Direction.HORIZONTAL) {
    		    	for(int i = 0 ; i < word.length();i++) {
    		    		//add tile with letter in position i of letters[]
    		    		squares.getSquare(position[i],position[1]).setTile[letters[i]];
    		    	}
    		    	//check if move is made vertically
    		    	}else if(dir == Direction.VERTICAL) {
    		    		for(int i = 0 ; i < word.length();i++) {
    		    			//add tile with letter in position i of letters[]
    		        		squares.getSquare(position[0],position[i]).setTile[letters[i]];
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

	public String[] splitPosition(String position) {
		String str = position;
		String[] part = str.split("(?<=\\D)(?=\\d)");
		System.out.println(part[0]);
		System.out.println(part[1]);
		return part;
	}

	public void skipTurn(ArrayList<String> tilesToReplace) {
		// TODO Auto-generated method stub

	}
	
	public Score calculateScore() {
		
		
		
		
		
		
		
		return score;
		
		
		
		
		
		
	}

}
