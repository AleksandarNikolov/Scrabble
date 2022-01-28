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
    private ArrayList<Tile> deck = new ArrayList<>();  //Players rack that holds all the tiles
    private static final int DeckSize = 7;
    private String deckString;
   
    public Deck () {
    	
    }
    
    //Get the tile at a specific index
    public Tile getTileIndex(int index) {
       return deck.get(index);	//Return tile at index
    }
    //return the deck of the player
    public ArrayList<Tile> getDeck() {
    	return deck;
    }
    //Take out a tile of a certain letter
    public Tile getTile(char letter) {
       for(int i = 0; i < 7; i++) {
          if(deck.get(i) != null  && letter == (deck.get(i).getLetter())) {   //Tile is not null and equal to letter value
             Tile temp = deck.get(i); //Set temp equal to the tile value
             deck.remove(i);   //Set the deck at index equal to null
             return temp;      //Return the temp value
          }                 
       }
       return null;      
    }
    
    //Check to see if the deck contains a certain letter
    public boolean hasLetter(char letter) {
       for(int i = 0; i < 7; i++) { 
          if(deck.get(i) != null  && letter == (deck.get(i).getLetter())) {   //Deck value is not null and letter is equal to tile letter
             return true;   //Return true
          }
       }
       return false;  //Return false
    }
    
    //Set deck at position i to tile value
    public void setTile(int i, Tile tile) {
       this.deck.add(i, tile);	//Set the tile at index to our input tile
    }
    
    //Check to see if we have no Tiles in our array
    public boolean deckIsEmpty() {
       for(int i = 0; i < 7; i++) {
          if(deck.get(i) != null)  //Deck at index is not equal to null
             return false;  //Return false
       }
       return true;   //Return true
    }
    
    //Generate a random deck for the player to start the game
    public ArrayList<Tile> generateDeck(ArrayList<Tile> bag) {
       for(int i = 0; i < 7; i++) {
          int rand = (int)(Math.random() * bag.size());   //Get a random value
          deck.add(i, bag.remove(rand));   //Set deck value to piece we are removing
       }
       return deck;   //Return the newly created deck
    }
    
    //Refill the deck after we create a word or switch out pieces
    public ArrayList<Tile> refillDeck(ArrayList<Tile> bag) {
       for(int i = 0; i < 7; i++) {
          if(deck.get(i) == null) {   //If the deck value is equal to null
             int rand = (int)(Math.random() * bag.size());   //Get a random number
             deck.add(i, bag.remove(rand));   //Set deck equal to the removed piece from the bag
          }
       }
       return deck;   //Return our updated deck
    }
    
    //print the deck to a string
    public String toString() {
    	
    	for(int i=0; i<DeckSize; i++) {
    		deckString += deck.get(i) + ",";
    	}
    	return deckString;
    }
 
    //remove tile from deck
    public void sacrificeTile(char letter,ArrayList<Tile> bag) {
    	
    	try { for(Tile tile : deck) {
    		
    		if(tile.equals(letter)) {
    	          deck.remove(tile); //Set deck value to piece we are removing
    	          bag.set(letter, tile); ////////Question
    		}
    	}
    		
    		
    		
    	} catch (Exception e) {
    		System.out.println("Tile is not in deck");
    	}
    }
}
