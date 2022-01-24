package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Board {

	public static final int SIZE = 15;
	private static HashSet<String> dict;
	private static HashMap<String, String> boardScores;
	

	public Board() {
	}

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
			"    |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    ", };
	private static final String LINE = NUMBERING[1];
	private static final String DELIM = "     ";

/*	public Square getSquare(int x, int y) throws Exception {
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
			throw new Exception("Square out of bounds!");
		}
		return this.square[x][y];
	}

	public Board clone() {
		Square[][] newSquars = new Square[SIZE][SIZE];

		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				newSquars[x][y] = (Square) squares[x][y];
			}
		}
		return new Board();
	}

	public void addSquares() {
	for(int i = 1 ; i < squares.length ; i++) {
		for(int j = 1 ; j < squares.length ; j++) {
			Square square = new Square(0, 0, 0, false);
		}
	}

	}
*/	
	
	
	
	
	/**
	 * Returns true if index is a valid index of a field on the board.
	 * 
	 * @ensures a positive result when the index is between 0 and DIM*DIM
	 * @return true if 0 <= index < DIM*DIM
	 */
	public boolean isField(int index) {
		return index >= 0 && index < SIZE * SIZE;
	}

	/**
	 * Returns true of the (row,col) pair refers to a valid field on the board.
	 * 
	 * @ensures true when both row and col are within the board's bounds
	 * @return true if 0 <= row < DIM && 0 <= col < DIM
	 */
	public boolean isField(int row, int col) {
		return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
	}

	/**
	 * Empties all fields of this board (i.e., let them refer to the value
	 * Mark.EMPTY).
	 * 
	 * @ensures all fields are EMPTY TODO FIX THIS
	 */
	public void reset() {
		generateBoard();
	}

	public static void main(String[] args) {

		Board bard = new Board();

		bard.generateBoard();
	}

	/**
	 * Initializes the dictionary by reading from a file
	 * 
	 * @throws FileNotFoundException
	 */
	private void initDict() throws FileNotFoundException {
		Board.dict = new HashSet<String>();
		BufferedReader dictReader = new BufferedReader(new FileReader("WordList.txt"));
		try {
			String line = dictReader.readLine();

			// add all words from words.txt to hashSet
			while (line != null) {
				dict.add(line.toLowerCase());
				line = dictReader.readLine();
			}
		} catch (IOException e) {
			System.err.println("IOException: " + e);
			e.printStackTrace();
		}
	}

	public void generateBoard() {
		for (int i = 1; i <= SIZE; i++) {
			System.out.println("");
			for (int j = 1; j <= SIZE; j++) {
				Square square = new Square(i, j, 0, false);

				Text multiplierT = new Text("");

				if (i == 1 & j == 1 || i == 1 && j == 8 || i == 1 && j == 15 || i == 8 && j == 1 || i == 8 && j == 15
						|| i == 15 && j == 1 || i == 15 && j == 8 || i == 15 && j == 15) {
					
					// red
					square.setMultiplier(3);
					square.isForWord(true);
					System.out.print("[W 3]");

				} else if (i == 1 && j == 4 || i == 1 && j == 12 || i == 3 && j == 7 || i == 3 && j == 9
						|| i == 4 && j == 1 || i == 4 && j == 8 || i == 4 && j == 15 || i == 7 && j == 3
						|| i == 7 && j == 7 || i == 7 && j == 9 || i == 7 && j == 13 || i == 8 && j == 4
						|| i == 8 && j == 12 || i == 9 && j == 3 || i == 9 && j == 7 || i == 9 && j == 9
						|| i == 9 && j == 13 || i == 12 && j == 1 || i == 12 && j == 8 || i == 12 && j == 15
						|| i == 13 && j == 7 || i == 13 && j == 9 || i == 15 && j == 4 || i == 15 && j == 12) {
					// lightblue
					square.setMultiplier(2);
					square.isForWord(false);
					System.out.print("[L 2]");
				} else if (i == 2 && j == 6 || i == 2 && j == 10 || i == 6 && j == 2 || i == 6 && j == 6
						|| i == 6 && j == 10 || i == 6 && j == 14 || i == 10 && j == 2 || i == 10 && j == 6
						|| i == 10 && j == 10 || i == 10 && j == 14 || i == 14 && j == 6 || i == 14 && j == 10) {
					// blue
					square.setMultiplier(3);
					square.isForWord(false);
					System.out.print("[L 3]");
				} else if (i == 2 && j == 2 || i == 2 && j == 14 || i == 3 && j == 3 || i == 3 && j == 13
						|| i == 4 && j == 4 || i == 4 && j == 12 || i == 5 && j == 5 || i == 5 && j == 11
						|| i == 11 && j == 5 || i == 11 && j == 11 || i == 12 && j == 4 || i == 12 && j == 12
						|| i == 13 && j == 3 || i == 13 && j == 13 || i == 14 && j == 2 || i == 14 && j == 14) {
					// lightpink
					square.setMultiplier(2);
					square.isForWord(true);
					System.out.print("[W 2]");
				} else if (i == 8 && j == 8) {
					// lightpink
					square.setMultiplier(2);
					square.isForWord(true);
					System.out.print("[W 2]");
				}else {
					System.out.print("[   ]");
				}

			}

		}

	}
}
