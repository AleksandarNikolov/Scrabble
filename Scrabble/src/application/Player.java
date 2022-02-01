package application;

/**
 * Class for Player in Scrabble
 * 
 * @author Aleksandar Nikolov and Andrei Cohan
 */

/**
 * The user playing the game is defined as a player Each player has a name , an
 * id for the client server connection, a deck containing an arrayList of
 * tiles,a score and a current score;
 */
public class Player {

	public String name;
	private int id;
	public int score;
	private Deck deck;
	private int currentScore;
	public boolean skipped = false;

	/**
	 * Creates a new Player object
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
		this.score = 0;
		this.deck = null;
	}

	/**
	 * Returns the name of the player
	 */
	public String getName() {
		return name;
	}

	/*
	 * set the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * returns the id of the player
	 */
	public int getId() {
		return id;
	}
	
	public void setSkippedTrue() {
		skipped = true;
	}
	
	public void setSkippedFalse() {
		skipped = false;
	}
	
	/*
	 * set the id of the player
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * returns the current score of the player
	 */
	public int getCurrentScore() {
		return currentScore;
	}

	/**
	 * Returns the final score of the player
	 */
	public int getScore() {
		return this.score;
	}

	/*
	 * set the current score of the player
	 */
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	/*
	 * set the final score of the player
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Returns the deck of the player
	 */
	public Deck getDeck() {
		return this.deck;
	}

	/*
	 * set the deck of the player
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

}
