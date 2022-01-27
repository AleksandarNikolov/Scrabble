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
	private SquareType type;
	private Tile letter = null;
	public Color color;
	
	
	/**
	 * Constructor for the tiles.
	 * @requires posX,PosY,multiplier,forWord.
	 * @param posX
	 * @param posY
	 * @param multiplier
	 * @param forWord
	 */
	
	public Square(int posX,int posY, SquareType type) {
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		
	}

	public Square getSquare(int x, int y) throws Exception {
		if (x < 0 || x >= 15 || y < 0 || y >= 15) {
			throw new Exception("Square out of bounds!");
		}
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

	public SquareType getType() {
        return type;
    }

	/**
	 * @return the hasPiece
	 */
	public boolean isEmpty() {
		return letter == null;
	}
	
	public void setTile(Tile letter) {
		this.letter = letter;
	}
	public Tile getTile() {
		return this.letter;
	}

	public int getWordMultiplier() {
        if (type == SquareType.DOUBLE_WORD || type == SquareType.CENTRAL_SQUARE) {
        	return 2;
        }
        
        if (type == SquareType.TRIPLE_WORD) {
        	return 3;
        }
        return 1;
    }
	public int getLetterMultiplier() {
        if (type == SquareType.DOUBLE_LETTER) {
        	return 2;
        }
        if (type == SquareType.TRIPLE_LETTER) {
        	return 3;
        }
        return 1;
    }

}