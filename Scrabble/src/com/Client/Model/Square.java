package com.Client.Model;

import exceptions.OutOfBoundsException;

/**
 * Class for Square in Scrabble
 * 
 * @author Aleksandar Nikolov and Andrei Cohan
 */

/*
 * The board is made of a 2 dimensional array of Squares All the values of the
 * square are stored here
 */
public class Square {

	private int posX;
	private int posY;
	private SquareType type;
	private Tile tile = null;

	/**
	 * Constructor for the tiles.
	 * 
	 * @requires posX,PosY,multiplier,forWord.
	 * @param posX
	 * @param posY
	 * @param multiplier
	 * @param forWord
	 */

	public Square(int posX, int posY, SquareType type) {
		this.posX = posX;
		this.posY = posY;
		this.type = type;

	}

	public Square getSquare(int x, int y) throws OutOfBoundsException {
		if (x < 0 || x >= 16 || y < 0 || y >= 16) {
			throw new OutOfBoundsException("The requested square is out of the bounds of the board");
		}
		return this;
	}
	
	/**
	 * @return the X position
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param set the X position
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the Y position
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param set the Y position
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public SquareType getType() {
		return type;
	}

	/**
	 * @return true if the square has no tile on it
	 */
	public boolean isEmpty() {
		return tile == null;
	}

	/*
	 * add a tile to the square
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}

	/*
	 * @return the tile of this square
	 */
	public Tile getTile() {
		return this.tile;
	}

	/*
	 * returns the word multiplier of the square
	 */
	public int getWordMultiplier() {
		if (type == SquareType.TRIPLE_WORD) {
			return 3;
		}
		if (type == SquareType.DOUBLE_WORD || type == SquareType.CENTRAL_SQUARE) {
			return 2;
		}
		return 1;
	}

	/*
	 * returns the letter multiplier of the square
	 */
	public int getLetterMultiplier() {
		if (type == SquareType.TRIPLE_LETTER) {
			return 3;
		}
		if (type == SquareType.DOUBLE_LETTER) {
			return 2;
		}
		return 1;
	}
}