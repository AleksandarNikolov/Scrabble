package application;

import java.util.ArrayList;

public class Score {

	private ArrayList<Tile> placements = new ArrayList<>();
	private int score;
	private int multiplier;
	private int addFactor;
	
	public Score() {
		score = 0;
		multiplier = 1;
		addFactor = 0;
	}
	//gets the current's turn score
	public int getScore() {
		return score;
	}
	//sets the score
	public void setScore(int newScore) {
		this.score = newScore;
	}
	//gets the score computed with the use of special tiles
	public int getComputedScore() {
		return (score + addFactor) * multiplier;
	}
	public void add(Tile letter) {
		placements.add(letter);
	}

}
