package application;

import java.util.Arrays;

public class Board {
	
	public static final int DIM = 15;
    private static final String[] NUMBERING = { "    | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  1 | 3 |   |   | 2 |   |   |   | 3 |   |   |   | 2 |   |   | 3 |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  2 |   | 2 |   |   |   | 3 |   |   |   | 3 |   |   |   | 2 |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  3 |   |   | 2 |   |   |   | 2 |   | 2 |   |   |   | 2 |   |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  4 | 2 |   |   | 2 |   |   |   | 2 |   |   |   | 2 |   |   | 2 |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  5 |   |   |   |   | 2 |   |   |   |   |   | 2 |   |   |   |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  6 |   | 3 |   |   |   | 3 |   |   |   | 3 |   |   |   | 3 |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  7 |   |   | 2 |   |   |   | 2 |   | 2 |   |   |   | 2 |   |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  8 | 3 |   |   | 2 |   |   |   | X |   |   |   | 2 |   |   | 3 |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"  9 |   |   | 2 |   |   |   | 2 |   | 2 |   |   |   | 2 |   |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											" 10 |   | 3 |   |   |   | 3 |   |   |   | 3 |   |   |   | 3 |   |    ", 
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											" 11 |   |   |   |   | 2 |   |   |   |   |   | 2 |   |   |   |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											" 12 | 2 |   |   | 2 |   |   |   | 2 |   |   |   | 2 |   |   | 2 |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											" 13 |   |   | 2 |   |   |   | 2 |   | 2 |   |   |   | 2 |   |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											" 14 |   | 2 |   |   |   | 3 |   |   |   | 3 |   |   |   | 2 |   |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											" 15 | 3 |   |   |   |   |   |   | 3 |   |   |   |   |   |   | 3 |    ",
    											"----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|----",
    											"    |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    ",};
    private static final String LINE = NUMBERING[1];
    private static final String DELIM = "     ";
    
    private Square[][] squares;
  
	
    public Board() {
    
    
    
    /**
     
    int[] col = new int[DIM];
    int[] row = new int[DIM];
    
    	for ( int i = 0 ; i < col.length ; i++ ) {
    		for( int j = 0 ; j < row.length ; j++) {
    			Square square = new Square(i,j);
    		}
    	}
     
     */
   
    	
    }
    
    public Board boardCopy() {
        
    	Board boardCopy = new Board();	
    	boardCopy.squares = this.squares.clone();
    	return boardCopy;
    	
        }
    
    
    /**
     * Calculates the index in the linear array of fields from a (row, col)
     * pair.
     * @requires row to be between 0 and DIM
     * @requires col to be between 0 and DIM
     * @return the index belonging to the (row,col)-field
     */
    public int index(int col, int row) {
    	return (col * DIM) + row;
    }
    
    /**
     * Returns true if index is a valid index of a field on the board.
     * @ensures a positive result when the index is between 0 and DIM*DIM
     * @return true if 0 <= index < DIM*DIM
     */
    public boolean isField(int index) {
        return index >= 0 && index < DIM * DIM;
    }
    
    /**
     * Returns true of the (row,col) pair refers to a valid field on the board.
     * @ensures true when both row and col are within the board's bounds
     * @return true if 0 <= row < DIM && 0 <= col < DIM
     */
    public boolean isField(int row, int col) {
    	return row >= 0  && row < DIM && col >= 0 && col < DIM;
    }
    
    /**
     * Returns the content of the field i.
     * @requires i to be a valid field
     * @ensures the result to be either EMPTY, XX or OO
     * @param i the number of the field (see NUMBERING)
     * @return the mark on the field
     */
    public Square getField(int i) {
    	return this.squares[i][i];
    }
    
    /**
     * Returns the content of the field ref erred to by the (row,col) pair.
     * @requires (row, col) to be a valid field
     * @ensures the result to be either EMPTY, XX or OO
     * @param row the row of the field
     * @param col the column of the field
     * @return the mark on the field
     */
    public Square getField(int row, int col) {
    	return getField(index(row, col));
    }
    
    /**
     * Returns true if the field i is empty.
     * @requires i to be a valid field index
     * @ensures true when the Mark at index i is EMPTY
     * @param i the index of the field (see NUMBERING)
     * @return true if the field is empty
     */
    public boolean isEmpty(int i) {
    	return getField(i).isEmpty();
    }
    
    /**
     * Returns true if the field referred to by the (row,col) pair it empty.
     * @requires (row, col) to be a valid field
     * @ensures true when the Mark at (row, col) is EMPTY
     * @param row the row of the field
     * @param col the column of the field
     * @return true if the field is empty
     */
    public boolean isEmpty(int row, int col) {
    	return getField(row, col).isEmpty();
    }
    
    /**
     * Tests if the whole board is full.
     * @ensures true if all fields are occupied
     * @return true if all fields are occupied
     */
    public boolean isFull() {
    	for (int i = 0; i < squares.length; i++) {
    		if (isEmpty(i)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Returns true if the game is over. The game is over when there is a winner
     * or the whole board is full.
     * @ensures true if the board is full or when there is a winner
     * @return true if the game is over
     */
    public boolean gameOver() {
    	// Optional add timer
        return hasWinner();
    }
    
    
    
    //TODO change this so its oriented towards Scrabble not TicTacToe
    
    
    
    /**
     * Checks whether there is a row which is full and only contains the mark
     * m.
     * @param m the Mark of interest
     * @return true if there is a row controlled by m
     */
    public boolean hasRow(Square m) {
    	boolean hasRow = false;
    	for (int row = 0; row < DIM && !hasRow; row++) {
    		hasRow = true;
    		for (int col = 0; col < DIM && hasRow; col++) {
    			hasRow = getField(row, col) == m;
    		}
    	}
    	return hasRow;
    }
    
    /**
     * Checks whether there is a column which is full and only contains the mark
     * m.
     * @param m the Mark of interest
     * @return true if there is a column controlled by m
     */
    public boolean hasColumn(Square m) {
    	boolean hasCol = false;
    	for (int col = 0; col < DIM && !hasCol; col++) {
    		hasCol = true;
    		for (int row = 0; row < DIM && hasCol; row++) {
    			hasCol = getField(row, col) == m;
    		}
    	}
    	return hasCol;
    }
    
    /**
     * Checks if the mark m has won. A mark wins if it controls at
     * least one row, column.
     * @requires m to be either XX or OO
     * @ensures true when m has a row, column or diagonal 
     * @param m the mark of interest
     * @return true if the mark has won
     */
    public boolean isWinner(Square m) {
    	return true;
    }

    /**
     * Returns true if the game has a winner. This is the case when one of the
     * marks controls at least one row, column or diagonal.
     * @ensures true when either XX or OO has won
     * @return true if the student has a winner.
     */
    public boolean hasWinner() {
        return isWinner(null);
    }
    
    
    /**
     * Returns a String representation of this board. In addition to the current
     * situation, the String also shows the numbering of the fields.
     *
     * @return the game situation as String
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < DIM; i++) {
            String row = "";
            for (int j = 0; j < DIM; j++) {
                row = row + " " + getField(i, j).toString() + " ";
                if (j < DIM - 1) {
                    row = row + "|";
                }
            }
            s = s + row + DELIM + NUMBERING[i * 2];
            if (i < DIM - 1) {
                s = s + "\n" + LINE + DELIM + NUMBERING[i * 2 + 1] + "\n";
            }
        }
        return s;
    }
    
    /**
     * Empties all fields of this board (i.e., let them refer to the value
     * Mark.EMPTY).
     * @ensures all fields are EMPTY
     *TODO FIX THIS
     */
    public void reset() {
    	Arrays.fill(this.squares, 0);
    }
    
    /**
     * Sets the content of field i to the mark m.
     * @requires i to be a valid field
     * @ensures field i to be set to Mark m
     * @param i the field number (see NUMBERING)
     * @param m the mark to be placed
     */
    public void setField(int i, Square[] m) {
    	squares[i] = m;
    }

    /**
     * Sets the content of the field represented by the (row,col) pair to the
     * mark m.
     * @requires (row, col) to be a valid field
     * @ensures field (row, col) to be set to Mark m
     * @param row the field's row
     * @param col the field's column
     * @param m the mark to be placed
     */
    public void setField(int row, int col, Tile tile) {
    	//squares[index(row, col)] = tile;
    }
    
    
    public static void main(String[] args) {
    	
    	Board bard = new Board();
    	
    	bard.toString();
    	
    	
    	//for (int i = 0 ; i < NUMBERING.length ; i++) {
		//System.out.println(NUMBERING[i]);
    	//}
	}


	public void setField(int choice, int value) {
		// TODO Auto-generated method stub
		
	}
    
}
