package application;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import exceptions.NoTilesInBagException;

/**
 * Class for Bag in Scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */

/**
 * The bag is represented by an Arraylist of tiles. 
 * The bag should be initialized to have the standard distribution of tiles:
 *
 * 2 blank tiles : 0 points
 * Ex12, Ax9, Ix9, Ox8, Nx6, Rx6, Tx6, Lx4, Sx4, Ux4 : 1 point 
 * Dx4, Gx3 : 2 points
 * Bx2, Cx2, Mx2, Px2 3 points Fx2, Hx2, Vx2, Wx2, Yx2 : 4 points
 * Kx1 : 5 points
 * Jx1, Xx1 : 8 points 
 * Qx1, Zx1 : 10 points
 */

public class Bag {
	ArrayList<Tile> tiles;

	public Bag() {
		
	}

	/*
	 * add tiles to the tiles ArrayList
	 */
	public void generateBag() {

		tiles = new ArrayList<Tile>();

		for (int i = 0; i <= 9; i++) {
			tiles.add(new Tile('A'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('B'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('C'));
		}
		for (int i = 0; i <= 4; i++) {
			tiles.add(new Tile('D'));
		}
		for (int i = 0; i <= 12; i++) {
			tiles.add(new Tile('E'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('F'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('G'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('H'));
		}
		for (int i = 0; i <= 8; i++) {
			tiles.add(new Tile('I'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('J'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('K'));
		}
		for (int i = 0; i <= 4; i++) {
			tiles.add(new Tile('L'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('M'));
		}
		for (int i = 0; i <= 6; i++) {
			tiles.add(new Tile('N'));
		}
		for (int i = 0; i <= 8; i++) {
			tiles.add(new Tile('O'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('P'));
		}
		for (int i = 0; i <= 1; i++) {
			tiles.add(new Tile('Q'));
		}
		for (int i = 0; i <= 6; i++) {
			tiles.add(new Tile('R'));
		}
		for (int i = 0; i <= 4; i++) {
			tiles.add(new Tile('S'));
		}
		for (int i = 0; i <= 6; i++) {
			tiles.add(new Tile('T'));
		}
		for (int i = 0; i <= 4; i++) {
			tiles.add(new Tile('U'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('V'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('W'));
		}
		for (int i = 0; i <= 1; i++) {
			tiles.add(new Tile('X'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile('Y'));
		}
		for (int i = 0; i <= 1; i++) {
			tiles.add(new Tile('Z'));
		}
		for (int i = 0; i <= 2; i++) {
			tiles.add(new Tile(' '));
		}

	}
	
	/*
	 * Used to check if bag is empty
	 */
	public boolean isEmpty() {
		return tiles.size() == 0;
	}
	
	/*
	 * returns the ArrayList of tiles
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	/*
	 * get the about of tiles remaining
	 * used for testing
	 */
	public int getRemaining() {
		return tiles.size();
	}
	
	/*
	 * randomly reorder the ArrayList of tiles
	 */
	public void mixBag() {
		Collections.shuffle(tiles, new Random());
	}

	/*
	 * pickup a random tile from the bag
	 * bag is shuffled so tile at index 0 is random
	 */
	public Tile pickUp() throws NoTilesInBagException {
		if (tiles.size() == 0) {
			throw new NoTilesInBagException("Thera are no other tiles in the bag!");
		}
		Tile tile = tiles.get(0);
		tiles.remove(0);
		return tile;
	}
}