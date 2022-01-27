package application;
import java.util.*;
import java.util.Map.Entry;

import exceptions.NoTilesInBagException;


/**
 * Class for Bag in Scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */

/**
 *The bag is represented by a list of tiles.
 *The bag should be initialized to have the standard distribution of tiles:
 *
 *2 blank tiles                                      0 points
 *Ex12, Ax9, Ix9, Ox8, Nx6, Rx6, Tx6, Lx4, Sx4, Ux4  1 point 
 *Dx4, Gx3                                            2 points
 *Bx2, Cx2, Mx2, Px2                                    3 points
 *Fx2, Hx2, Vx2, Wx2, Yx2                            4 points
 *Kx1                                                    5 points
 *Jx1, Xx1                                            8 points
 *Qx1, Zx1                                            10 points
*/

public class Bag{
	ArrayList<Tile> tiles;
    private HashMap<Character, Integer[]> letters;

    public Bag () {
        reset ();
    }

    public void reset () {
    	tiles = new ArrayList<>();
        letters = new HashMap<Character, Integer[]>();
        letters.put('A', new Integer[]{9, 1});
        letters.put('B', new Integer[]{2, 3});
        letters.put('C', new Integer[]{2, 3});
        letters.put('D', new Integer[]{4, 2});
        letters.put('E', new Integer[]{12, 1});
        letters.put('F', new Integer[]{2, 4});
        letters.put('G', new Integer[]{3, 2});
        letters.put('H', new Integer[]{2, 4});
        letters.put('I', new Integer[]{9, 1});
        letters.put('J', new Integer[]{1, 8});
        letters.put('K', new Integer[]{1, 5});
        letters.put('L', new Integer[]{4, 1});
        letters.put('M', new Integer[]{2, 3});
        letters.put('N', new Integer[]{6, 1});
        letters.put('O', new Integer[]{8, 1});
        letters.put('P', new Integer[]{2, 3});
        letters.put('Q', new Integer[]{1, 10});
        letters.put('R', new Integer[]{6, 1});
        letters.put('S', new Integer[]{4, 1});
        letters.put('T', new Integer[]{6, 1});
        letters.put('U', new Integer[]{4, 1});
        letters.put('V', new Integer[]{2, 4});
        letters.put('W', new Integer[]{2, 4});
        letters.put('X', new Integer[]{2, 8});
        letters.put('Y', new Integer[]{1, 4});
        letters.put('Z', new Integer[]{1, 10});
        letters.put('_', new Integer[]{2, 0});
    
        Iterator<?> i = letters.entrySet().iterator(); // make an iterator to manipulate the letters 
        	while(i.hasNext()) {					//while it has letters in the arraylist
        		Map.Entry entry = (Entry<?, ?>) i.next();// for every next letter, which will be in the variable entry (Map.Entry returns the whole map)
        		for (int j = 0; j < ((Integer[])((Entry<?, ?>) i).getValue())[0]; j++) {
        			 ((List<Tile>) letters).add(new Tile((char) ((Entry<?, ?>) i).getKey(), ((Integer[])((Entry<?, ?>) i).getValue())[1]));
        		}
        	}
        	randomMix();
        }
    public void randomMix() {
    	Collections.shuffle(tiles, new Random()); //randomly reorders the list elements using specified randomness
    }
    public boolean isEmpty () {
        return tiles.size() == 0;
    } 
    public int getRemaining() { //this method is useful for the testing part
        return tiles.size();
    }
    public ArrayList <Tile> getTiles() {
    	return tiles;
    }
    public Tile pickup() throws NoTilesInBagException{
    	if (tiles.size() <= 0) {
    		throw new NoTilesInBagException("Thera are no other tiles in the bad!");
    	}
	    Tile t = tiles.get(0);
	    tiles.remove(0);
	    return t;
    }
}