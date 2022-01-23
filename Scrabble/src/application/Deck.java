package application;

import java.util.ArrayList;

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
    private static final Tile[] deck = new Tile[7];  //Players rack that holds all the tiles
   
    public Deck () {
    	
    }
    
    //Get the tile at a specific index
    public Tile getTileIndex(int index) {
       return deck[index];	//Return tile at index
    }
    //return the deck of the player
    public Tile[] getDeck() {
    	return deck;
    }
    //Take out a tile of a certain letter
    public Tile getTile(String letter) {
       for(int i = 0; i < 7; i++) {
          if(deck[i] != null  && letter.equals(deck[i].getLetter())) {   //Tile is not null and equal to letter value
             Tile temp = deck[i]; //Set temp equal to the tile value
             deck[i] = null;   //Set the deck at index equal to null
             return temp;      //Return the temp value
          }                 
       }
       return null;      
    }
    
    //Check to see if the deck contains a certain letter
    public boolean hasLetter(String letter) {
       for(int i = 0; i < 7; i++) { 
          if(deck[i] != null  && letter.equals(deck[i].getLetter())) {   //Deck value is not null and letter is equal to tile letter
             return true;   //Return true
          }
       }
       return false;  //Return false
    }
    
    //Set deck at position i to tile value
    public void setTile(int i, Tile tile) {
       this.deck[i] = tile;	//Set the tile at index to our input tile
    }
    
    //Check to see if we have no Tiles in our array
    public boolean rackIsEmpty() {
       for(int i = 0; i < 7; i++) {
          if(deck[i] != null)  //Deck at index is not equal to null
             return false;  //Return false
       }
       return true;   //Return true
    }
    
    //Generate a random deck for the player to start the game
    public Tile[] generateRack(ArrayList<Tile> bag) {
       for(int i = 0; i < 7; i++) {
          int rand = (int)(Math.random() * bag.size());   //Get a random value
          deck[i] = bag.remove(rand);   //Set deck value to piece we are removing
       }
       return deck;   //Return the newly created deck
    }
    
    //Refill the deck after we create a word or switch out pieces
    public Tile[] refillRack(ArrayList<Tile> bag) {
       for(int i = 0; i < 7; i++) {
          if(deck[i] == null) {   //If the deck value is equal to null
             int rand = (int)(Math.random() * bag.size());   //Get a random number
             deck[i] = bag.remove(rand);   //Set deck equal to the removed piece from the bag
          }
       }
       return deck;   //Return our updated deck
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
