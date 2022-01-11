package application;

public class Tile {
	
	private String letter;
	private int posX;
	private int posY;
	private int value;
	private boolean isPlaced;
	private boolean isOwned;
	private Square square;
	
	public Tile(String letter,int posX, int posY, int value, boolean isPlaced, boolean isOwned) {
		this.setLetter(letter);
		this.setPosX(posX);
		this.setPosY(posY);
		this.setValue(value);
		this.isPlaced = true;
		this.isOwned = isOwned;
		
	}
	public Tile(String letter, int value, boolean isPlaced, boolean isOwned) {
		this.setLetter(letter);
		this.setValue(value);
		this.isPlaced = false;
		this.isOwned = isOwned;
		
	}
	

	


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the isPlaced
	 */
	public boolean isPlaced() {
		return isPlaced;
	}
	
	public void place( String posX, int posY) {
		square.setValue(this.value);
		square.setHasPiece(true);
		this.isPlaced = true;
	}



	/**
	 * @param isPlaced the isPlaced to set
	 */
	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}




	/**
	 * @return the isOwned
	 */
	public boolean isOwned() {
		return isOwned;
	}




	/**
	 * @param isOwned the isOwned to set
	 */
	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}




	/**
	 * @return the posX
	 */
	public String getLetter() {
		return letter;
	}




	/**
	 * @param posX the posX to set
	 */
	public void setLetter(String letter) {
		this.letter = letter;
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
	
}
