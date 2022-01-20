package application;

public class Bag {
	

	public void generateTiles() {
		
		for (int a = 0 ; a <= 9 ; a++) {
			Tile tile = new Tile(null, a, false, false);
		}
		
	}
	

}
