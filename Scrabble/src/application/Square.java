package application;

import java.awt.Color;

/**
 * Class for Square in Scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */

public class Square {
	
	private int posX;
	private int posY;
	private int multiplier;
	private boolean forWord;
	private boolean empty;
	public Color color;
	private Tile tile;
	
	/**
	 * Constructor for the tiles.
	 * @requires posX,PosY,multiplier,forWord.
	 * @param posX
	 * @param posY
	 * @param multiplier
	 * @param forWord
	 */
	
	public Square(int posX,int posY,int multiplier , boolean forWord) {
		this.posX = posX;
		this.posY = posY;
		this.multiplier = multiplier;
		this.forWord = forWord;
		this.isEmpty();
	}

	public Square getSquare(int posX,int posY) {
		return this;
	}
	
	
	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the multiplier
	 */
	public int getMultiplier() {
		return multiplier;
	}

	/**
	 * @param b 
	 * @return the forWord
	 */
	public boolean isForWord(boolean b) {
		return forWord;
	}

	/**
	 * @param forWord the forWord to set
	 */
	public void setForWord(boolean forWord) {
		this.forWord = forWord;
	}

	/**
	 * @return the hasPiece
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * @param hasPiece the hasPiece to set
	 */
	public void setEmpty() {
		this.empty = true;
		//todo add check to see if empty or not
	}

	public void setHasPiece(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public void setMultiplier(int i) {
		this.multiplier = i;
		
	}

}
