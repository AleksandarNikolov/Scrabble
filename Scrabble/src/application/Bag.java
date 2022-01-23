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
	private HashMap<Character, Integer> tileBag;
    private Random random = new Random ();

    public Bag () {
        reset ();
    }

    /**
     * Tile bag initialization and distribution
     * //TODO: Find a way to read these values directly from the file (if time permits)
     */
    private void reset() {
        this.tileBag =  new HashMap<Character, Integer>();
        if (this.tileBag.isEmpty()) {
            this.tileBag.put('A', 9);
            this.tileBag.put('B', 2);
            this.tileBag.put('C', 2);
            this.tileBag.put('D', 4);
            this.tileBag.put('E', 12);
            this.tileBag.put('F', 2);
            this.tileBag.put('G', 3);
            this.tileBag.put('H', 2);
            this.tileBag.put('I', 9);
            this.tileBag.put('J', 1);
            this.tileBag.put('K', 1);
            this.tileBag.put('L', 4);
            this.tileBag.put('M', 2);
            this.tileBag.put('N', 6);
            this.tileBag.put('O', 8);
            this.tileBag.put('P', 2);
            this.tileBag.put('Q', 1);
            this.tileBag.put('R', 6);
            this.tileBag.put('S', 4);
            this.tileBag.put('T', 6);
            this.tileBag.put('U', 4);
            this.tileBag.put('V', 2);
            this.tileBag.put('W', 2);
            this.tileBag.put('X', 1);
            this.tileBag.put('Y', 2);
            this.tileBag.put('Z', 1);
        }
    }
    /**
     * Used when players give up their turns
     * @param C     Tile added back to bag
     * @return      added successfully
     */
    public void addTileToBag(char C) {
        if (this.tileBag.containsKey(C)) {
            int newValue = this.tileBag.get(C) + 1;
            this.tileBag.put(C, newValue);
        }
    }
    
    public int totalNumberOfTilesInBag() {
        int total = 0;

        for (int i=65; i<=90; i++) {
            char C = (char) i;
            total += tileBag.get(C);
        }

        return total;

    }

    public boolean isEmpty () {
        return tileBag.isEmpty();
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
        Object[] letters = this.tileBag.keySet().toArray();
        Object randomLetter = letters[random.nextInt(letters.length)];

        if (this.tileBag.get(randomLetter) == 0) {
            // need to go recursive on the getRandomLetterFromBag function
        }

        else {
            int newValue = this.tileBag.get(randomLetter) -1;
            this.tileBag.put((Character) randomLetter, newValue);
        }
        return randomLetter;
    }

}
