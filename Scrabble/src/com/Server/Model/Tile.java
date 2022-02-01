package com.Server.Model;

import exceptions.AlreadyPlacedException;

/**
 * Class for Tile in scrabble
 * 
 * @author Aleksandar Nikolov and Andrei Cohan
 */

/*
 * Tiles are what are placed on squares to create words A tile has a letter, a
 * value and a boolean to know if it is placed or not The value of the tile is based on its letter
 */
public class Tile {
	private char letter;
	private int value;
	boolean isPlaced;

	public Tile(char letter) {
		this.letter = letter;
		if (letter == 'A') {
			value = 1;
		}
		if (letter == 'B') {
			value = 3;
		}
		if (letter == 'C') {
			value = 3;
		}
		if (letter == 'D') {
			value = 4;
		}
		if (letter == 'E') {
			value = -1;
		}
		if (letter == 'F') {
			value = 4;
		}
		if (letter == 'G') {
			value = 2;
		}
		if (letter == 'H') {
			value = 4;
		}
		if (letter == 'I') {
			value = 1;
		}
		if (letter == 'J') {
			value = 8;
		}
		if (letter == 'K') {
			value = 5;
		}
		if (letter == 'L') {
			value = 1;
		}
		if (letter == 'M') {
			value = 3;
		}
		if (letter == 'N') {
			value = 1;
		}
		if (letter == 'O') {
			value = 1;
		}
		if (letter == 'P') {
			value = 3;
		}
		if (letter == 'Q') {
			value = 10;
		}
		if (letter == 'R') {
			value = 1;
		}
		if (letter == 'S') {
			value = 1;
		}
		if (letter == 'T') {
			value = 1;
		}
		if (letter == 'U') {
			value = 1;
		}
		if (letter == 'V') {
			value = 4;
		}
		if (letter == 'W') {
			value = 4;
		}
		if (letter == 'X') {
			value = 8;
		}
		if (letter == 'Y') {
			value = 4;
		}
		if (letter == 'Z') {
			value = 10;
		}
		if (letter == ' ') {
			value = 0;
		}

	}

	/*
	 * returns the tiles letter
	 */
	public char getLetter() {
		return letter;
	}

	/*
	 * returns the tiles value
	 */
	public int getValue() {
		return this.value;
	}

	/*
	 * Print out the tile as a string
	 */
	public String toString() {
		return "" + letter + "(" + value + ")";
	}

	/*
	 * public boolean equals(Object obj) { if(!obj instanceof Tile) { return false;
	 * } else { other = (Tile)obj; } if(this.letter == other.letter) { return true;
	 * } else { return false; } }
	 * 
	 * /** returns if the tile is placed
	 */
	public boolean isPlaced() {
		return true;
	}

	/**
	 * @param isPlaced the isPlaced to set
	 */
	public void setPlaced(boolean isPlaced) throws AlreadyPlacedException {
		if (isPlaced()) {
			throw new AlreadyPlacedException("It is already placed!");
		}
		isPlaced = true;
	}

}
