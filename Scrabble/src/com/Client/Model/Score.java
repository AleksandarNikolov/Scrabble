package com.Client.Model;

/**
 * Class for Score in Scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */

/*
 * Used to store the players score throughout the game
 * Used at the end to determine winner
 */
public class Score {

	private int score;
	private int multiplier;
	private int addFactor;
	
	/*
	 * Score constructor 
	 * @ensures starting score is 0
	 */
	public Score() {
		score = 0;
		//multiplier = 1;
		//addFactor = 0;
	}
	
	/*
	 * returns the score for the current turn
	 */
	public int getScore() {
		return score;
	}
	
	
	/*
	 * set score to a specified value
	 * @requires newScore > score
	 * @ensures score = newScore
	 */
	public void setScore(int newScore) {
		this.score = newScore;
	}
	
	/*
	 * computes the score taking into account the square multipliers
	 */
	public int getComputedScore() {
		return (score + addFactor) * multiplier;
	}
}
