package application;

/**
 * Class for Deck in Scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */

/**    
The deck is represented by an array of tiles.
Deck is printed as Player rack;

- constructor
- boolean on(double x, double y) : is the point x,y on the rack
- int index(double x, double y) : returns the index of the cell at the point x,y on the rack
- void fill(Bag bag) :  fill all the space on the rack from the bag
- void reset() : reset the rack to initial empty state.  
 */

public class Deck{
    private static final int DeckSize = 7;
    private Tile [] tiles = new Tile [DeckSize];
    private String deck = "";
    
    
    public Deck () {
    	
    }
    
    //fill the Deck with random tiles from the bag
    //@requires Bag.size > 0
    //@ensures deck.size = DeckSize = 7
    public void fill(Bag bag) {
        for (int pos = 0; pos<DeckSize; pos++) {
            if (tiles[pos]==null) {
                tiles[pos]=bag.pick();
            }
        }
    }
    
    //reset the Deck
    //@ensures deck.size = DeckSize = 7
    public void reset() {
        for (int i=0; i<DeckSize; i++) {
            tiles [i] = null;
        }
    }
    
    //print the deck to a string
    public String toString() {
    	
    	for(int i=0; i<DeckSize; i++) {
    		deck += tiles[i] + ",";
    	}
    	return deck;
    }

}
