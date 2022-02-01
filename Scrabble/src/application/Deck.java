package application;

import java.util.ArrayList;

/**
 * Class for Deck in Scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */

/**
 * The deck is represented by an array of tiles
 * 
 * - constructor - boolean on(double x, double y) : is the point x,y on the rack
 * - int index(double x, double y) : returns the index of the cell at the point
 * x,y on the rack - void fill(Bag bag) : fill all the space on the rack from
 * the bag - void reset() : reset the rack to initial empty state.
 */

public class Deck {

	private static final int DeckSize = 7;
	private ArrayList<Tile> deck = new ArrayList<>();

	public Deck() {

	}

	/*
	 * get tile at specific index of deck
	 */
	public Tile getTileIndex(int index) {
		return deck.get(index); // Return tile at index
	}

	/*
	 * returns a tile with the specified character from the deck
	 */
	public Tile getTile(char letter) {
		Tile temp = null;
		for (int i = 0; i < deck.size(); i++) {
			if (deck.get(i).getLetter() == letter) {
				temp = deck.get(i);
			}
		}
		return temp;
	}
	
	/*
	 * returns a tile with the specified character from the deck
	 */
	public Tile removeTile(char letter) {
		Tile temp = null;
		for (int i = 0; i < deck.size(); i++) {
			if (deck.get(i).getLetter() == letter) {
				temp = deck.get(i);
				deck.remove(i);
			}
		}
		return temp;
	}
	
	/*
	 * discard the tile containing the specified letter
	 * letter is removed from deck and placed back into the bag
	 */
	public void discardTile(char letter, ArrayList<Tile> bag) {

		try {
			for (Tile tile : deck) {

				if (tile.equals(letter)) {
					deck.remove(tile);
					bag.add(letter, tile);
				}
			}

		} catch (Exception e) {
			System.out.println("Tile is not in deck");
		}
	}


	/*
	 * set a specific index of the deck to a specific tile
	 */
	public void setTile(int i, Tile tile) {
		this.deck.add(i, tile);
	}

	/*
	 * check if array is empty
	 */
	public boolean deckIsEmpty() {
		for (int i = 0; i < 7; i++) {
			if (deck.get(i) != null)
				return false;
		}
		return true;
	}

	/*
	 * generate a deck of random tiles for the player
	 * 
	 * @param bag containing tiles takes the available tiles from the bag ArrayList
	 * of tiles
	 */
	public ArrayList<Tile> generateDeck(ArrayList<Tile> bag) {
		for (int i = 0; i < 7; i++) {
			int rand = (int) (Math.random() * bag.size());
			deck.add(i, bag.remove(rand));
		}
		return deck;
	}

	/*
	 * refill the deck with available tiles from the bag
	 * 
	 * @param bag containing tiles
	 */
	public ArrayList<Tile> refillDeck(ArrayList<Tile> bag) {
		if(deck.size() < 7) {
			int ammountToAdd = (7 - deck.size());
		for (int i = 1; i <= ammountToAdd; i++) { 
			int rand = (int) (Math.random() * bag.size());
			deck.add(i,bag.remove(rand));
			}
		}
		
		
		return deck;
	}

	/*
	 * print deck to string
	 */
	public String toString() {
		String deckString = "";

		for (int i = 0; i < deck.size(); i++) {
			deckString += "[" + deck.get(i) + "]";
		}
		return deckString;
	}

	public void removeTile(Tile tile) {
		deck.remove(tile);
	}
}
