package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.Server.Model.Bag;
import com.Server.Model.Deck;
import com.Server.Model.Tile;

public class DeckTest {
	private Bag bag;
	private Deck deck;
	
	
	@BeforeEach
	void setUp() throws Exception {
		bag = new Bag();
		deck = new Deck();
		bag.generateBag();
		
	}

	@Test
	void generateDeck() {
		deck.generateDeck(bag.getTiles());
		assertFalse(deck.getTileIndex(0)==null);
		assertFalse(deck.deckIsEmpty());
		assertEquals(7, deck.getDeck().size());
		
	}
	@Test
	void refillDeck() throws Exception {
		deck.generateDeck(bag.getTiles());
		assertFalse(deck.deckIsEmpty());
		
	} 
	@Test
	void removeTile() throws Exception{
		deck.generateDeck(bag.getTiles());
		deck.removeTile(deck.getTileIndex(0).getLetter());
		assertTrue(deck.getTileIndex(0) == null);
	}
	@Test
	void deckIsEmpty() throws Exception{
		deck.generateDeck(bag.getTiles());
		assertTrue(deck.getDeck().size() == 7);
		ArrayList<Tile> tiles = new ArrayList<>();
		for(int i = 0; i < deck.getDeck().size(); i++) {
			deck.removeTile(deck.getTileIndex(i).getLetter());
		
		}
		assertFalse(deck.deckIsEmpty());
	}
	
	
}