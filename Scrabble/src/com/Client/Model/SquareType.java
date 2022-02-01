package com.Client.Model;

/**
 * Class for SquareType in scrabble
 * 
 * @author 	Aleksandar Nikolov and Andrei Cohan
 */

/*
 * An enum is used to denote each square type
 */
public enum SquareType {
	
	PLAIN (1),
	DOUBLE_LETTER(2),
	TRIPLE_LETTER(3),
	DOUBLE_WORD(2),
	TRIPLE_WORD(3),
	CENTRAL_SQUARE(2);
	
	private int multiplier;
	
	SquareType(int multiplier) {
		this.multiplier = multiplier;
	}
	
	public int multiplier() {
		return multiplier;
	}
	
	/*
	 * this method values() converts different types of value into strings
	 */
	public static void main(String[] args) {
		for(SquareType x : SquareType.values()) { 
			System.out.printf("A %s cell has a multiplier of %d.%n", x, x.multiplier());
		}
	}
	
}