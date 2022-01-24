package application;



public class Tile {
	private char letter;
	private int value;
	private boolean isPlaced;

	 public Tile(char letter, int value) {
	      this.letter = letter;  
	      this.value = value;   
	 }
	
	 //Return the letter of the tile
	   public char getLetter() {
	      return letter;	//Return the letter value
	   }  

		public int getValue(){
			return this.value;
		}

	   //Print out the letter value
	   public String toString() {
	      return "" + letter + "(" + value + ")";
	   }

		public boolean equals(Object obj) {
			if(!obj instanceof Tile) {
				return false;
			} else {
				other = (Tile)obj;
			}
			if(this.letter == other.letter) {
				return true;
			} else {
				return false;
			}
		}

	/**
	 * @return the isPlaced
	 */
	
	public boolean isPlaced() {
		return true;
	}
	/**
	 * @param isPlaced the isPlaced to set
	 */
	public void setPlaced(boolean isPlaced) throws Exception {
		if(isPlaced() == true) {
			throw new Exception("It is already placed!");
		}
		isPlaced = true;
	}
	public void place( String posX, int posY) {
		square.setValue(this.points);
		square.setHasPiece(true);
		this.isPlaced = true;
	}
	

}
