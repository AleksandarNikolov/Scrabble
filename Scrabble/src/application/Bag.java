package application;
import java.util.*;


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
    ArrayList <Tile> tiles = new ArrayList <Tile>();
    private Random random = new Random ();

    public Bag () {
        reset ();
    }

    public void reset () {
        tiles = new ArrayList <Tile>();
        addTiles (2, "blank", 0);
        addTiles (12, "E", 1);
        addTiles (9, "A", 1);
        addTiles (9, "I", 1);
        addTiles (8, "O", 1);
        addTiles (6, "N", 1);
        addTiles (6, "R", 1);
        addTiles (6, "T", 1);
        addTiles (4, "L", 1);
        addTiles (4, "S", 1);
        addTiles (4, "U", 1);
        addTiles (4, "D", 2);
        addTiles (3, "G", 2);
        addTiles (2, "B", 3);
        addTiles (2, "C", 3);
        addTiles (2, "M", 3);
        addTiles (2, "P", 3);
        addTiles (2, "F", 4);
        addTiles (2, "H", 4);
        addTiles (2, "V", 4);
        addTiles (2, "W", 4);
        addTiles (2, "Y", 4);
        addTiles (1, "K", 5);
        addTiles (1, "J", 8);
        addTiles (1, "X", 8);
        addTiles (1, "Q", 10);
        addTiles (1, "Z", 10);
    	}
    
    /**
     * Used when players give up their turns
     * @param C     Tile added back to bag
     * @return      added successfully
     */
    public void addTiles(char C) {
        if (this.tiles.containsKey(C)) {
            int newValue = this.tiles.get(C) + 1;
            this.tiles.put(C, newValue);
        }
    }
    
    public int totalNumberOfTilesInBag() {
        int total = 0;

        for (int i=65; i<=90; i++) {
            char C = (char) i;
            total += tiles.get(C);
        }

        return total;

    }

    public boolean isEmpty () {
        return tiles.isEmpty();
    }
    /**
     * / *****  USE THE getRandomLetterFromBag(char C) instead of this    *****  /
     *
     * Used when game starts and after each move
     * @param C     Tile to be removed
     * @return      removed successfully
     */
    public boolean removeTileFromBag(char C) {
        if (this.tileBag.containsKey(C) && this.tileBag.get(C) > 0) {
            int newValue = this.tileBag.get(C) - 1;
            this.tileBag.put(C, newValue);
            return true;
        }
        return false;
    }
    
    
    public Object getRandomLetterFromBag(char C) {
        Random random = new Random();
        Set<Character> letters = this.tileBag.keySet();
        Object randomLetter = letters.contains(random.nextInt(letters.size()));

        
       if (this.tileBag.get(randomLetter) == 0) {
        	while(this.tileBag.get(randomLetter) == 0) {
        		letters.remove(randomLetter);
        		randomLetter = letters.contains(random.nextInt(letters.size()));
        		
        	}
        }

        else {
            int newValue = this.tileBag.get(randomLetter) -1;
            this.tileBag.put((Character) randomLetter, newValue);
        }
        return randomLetter;
    }
    public ArrayList <Tile> getTiles() {
    	return tiles;
    }
    private void addTiles (int count, String name, int score) {
        for (int i=0; i<count; i++) {
            tiles.add(new Tile(name,score));
        }
    }

    public Tile pick () {
        if (tiles.isEmpty()) {
            return null;
        }
        return tiles.remove(random.nextInt(tiles.size()));

    }
}
