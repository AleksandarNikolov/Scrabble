package application;

import application.Board;
import application.Tile;

/**
 * Abstract class for keeping a player in the Tic Tac Toe game. Module 2 lab
 * assignment.
 * 
 * 
 * @author Theo Ruys en Arend Rensink
 * @version $Revision: 1.4 $
 */
public abstract class Player {

    // -- Instance variables -----------------------------------------

    private String name;
    private Score score;
    private Tile tile;

    // -- Constructors -----------------------------------------------

    /**
     * Creates a new Player object.
     * @requires name is not null
     * @requires mark is either XX or OO
     * @ensures the Name of this player will be name
     * @ensures the Mark of this player will be mark
     */
    public Player(String name, Score score) {
        this.name = name;
        this.score = score;
    }

    // -- Queries ----------------------------------------------------

    /**
     * Returns the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the mark of the player.
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
    public abstract int determineMove(Board board);

    // -- Commands ---------------------------------------------------

    /**
     * Makes a move on the board. <br>
     * @requires board is not null and not full
     * @param board the current board
     */
    public void makeMove(Board board) {
        int choice = determineMove(board);
        board.setField(choice, tile.getValue());
    }

}
