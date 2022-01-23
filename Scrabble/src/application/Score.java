package application;

import java.util.HashMap;

/**
 * Class for Score in Scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */
public class Score {

	private HashMap<Player, Integer> scoreMap;
    public static HashMap<Character, Integer> tileScore;

    
    public Score(Player player1, Player player2) {
        this.scoreMap = new HashMap<Player, Integer>();
        this.scoreMap.put(player1, 0);
        this.scoreMap.put(player2, 0);
        this.initTileScore();
    }
    
    /**
     * Initializes tile scores in another hashmap
     * //TODO: Find a way to read these directly from the file
     */
    public void initTileScore() {
        Score.tileScore = new HashMap<Character, Integer>();
        if (Score.tileScore.isEmpty()) {
            Score.tileScore.put('A', 1);
            Score.tileScore.put('B', 3);
            Score.tileScore.put('C', 3);
            Score.tileScore.put('D', 2);
            Score.tileScore.put('E', 1);
            Score.tileScore.put('F', 2);
            Score.tileScore.put('G', 2);
            Score.tileScore.put('H', 4);
            Score.tileScore.put('I', 1);
            Score.tileScore.put('J', 8);
            Score.tileScore.put('K', 5);
            Score.tileScore.put('L', 1);
            Score.tileScore.put('M', 3);
            Score.tileScore.put('N', 1);
            Score.tileScore.put('O', 1);
            Score.tileScore.put('P', 3);
            Score.tileScore.put('Q', 10);
            Score.tileScore.put('R', 1);
            Score.tileScore.put('S', 1);
            Score.tileScore.put('T', 1);
            Score.tileScore.put('U', 1);
            Score.tileScore.put('V', 4);
            Score.tileScore.put('W', 4);
            Score.tileScore.put('X', 8);
            Score.tileScore.put('Y', 4);
            Score.tileScore.put('Z', 10);
        }
    }
    
    /**
     * @param C     Tile to be scored
     * @return      Score for a tile
     */
    private int getTileScore(Character C) {
        if (Score.tileScore.containsKey(C)) {
            return Score.tileScore.get(C);
        }
        return -1;
    }
	
	 /**
     * Get player's score
     * @param player    Player in question
     * @return          Player's score
     */
    public int getPlayerScore(Player player) throws IllegalArgumentException {
        if (player == null)    {  
        	throw new IllegalArgumentException("player obj null"); 
        }

        if (this.scoreMap.containsKey(player)) {
            return this.scoreMap.get(player);
        } else return -1;
    }
    
    /**
     * Updates the player's score
     * @param player    Player in question
     * @param score     New Score
     */
    public void updatePlayerScore(Player player, int score) {
        if (score < 0)         {   
        	throw new IllegalArgumentException("negative score!");
        }
        if (player == null)    { 
        	throw new IllegalArgumentException("player obj null"); 
        }

        int newScore = this.scoreMap.get(player) + score;
        this.scoreMap.put(player, newScore);
    }

}
