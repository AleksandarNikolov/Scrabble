package application;
/**
 * Class for Player in Scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */
public class Player {

    // -- Instance variables -----------------------------------------
    public String name;
    public Score score;
    private Deck deck;

    // -- Constructors -----------------------------------------------

    /**
     * Creates a new Player object.
     * @requires name is not null
     * @requires score >= 0;
     * @ensures the Name of this player will be name
     * @ensures the Score of this player will be score;
     */
    public Player(String name, int totalPoints,Score score) {
        this.name = name;
        this.score = score;
        deck = new Deck();
    }

    // -- Queries ----------------------------------------------------

    /**
     * Returns the name of the player.
     */
    public String getName() {
        return name;
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
     * @requires board is not null and not full
     * @ensures the returned in is a valid field index and that field is empty
     * @param board the current game board
     * @return the player's choice
     */
    public Board determineMove(Board board) {
		return board;// to do
	}

    // -- Commands ---------------------------------------------------

    /**
     * Makes a move on the board. <br>
     * @requires board is not null and not full
     * @param board the current board
     */
    public void makeMove(Tile tile, int x, int y) {
    }// to do
  
    
    
}
