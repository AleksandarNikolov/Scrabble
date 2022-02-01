package com.Client.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class Board implements ANSI {

	public static final int SIZE = 16;
	private static HashSet<String> dict;
	private static HashMap<String, String> boardScores;
	Square[][] squares = new Square[SIZE][SIZE];

	public Board() {
		generateBoard();
	}


	public Square getSquare(int x, int y) throws Exception {
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
			throw new Exception("Square out of bounds!");
		}
		return this.squares[x][y];
	}
	
	public boolean isField(int x, int y) {
		return x >= 1 && x < SIZE && y >= 1 && y < SIZE;
	}

	public Board clone(Board board) {
		Square[][] newSquars = new Square[SIZE][SIZE];

		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				newSquars[x][y] = board.squares[x][y];
			}
		}
		return new Board();
	}

	public static void main(String[] args) throws Exception {

		Board bard = new Board();

		bard.generateBoard();
		bard.printBoard();//////////Question - out of bounds?
		
	}

	public void generateBoard() {
		for (int i = 1; i < SIZE ; i++) {	
			for (int j = 1; j < SIZE; j++) {
				if (i == 1 & j == 1 || i == 1 && j == 8 || i == 1 && j == 15 || i == 8 && j == 1 || i == 8 && j == 15
						|| i == 15 && j == 1 || i == 15 && j == 8 || i == 15 && j == 15) {

					squares[i][j] = new Square(i, j, SquareType.TRIPLE_WORD);
				

				} else if (i == 1 && j == 4 || i == 1 && j == 12 || i == 3 && j == 7 || i == 3 && j == 9
						|| i == 4 && j == 1 || i == 4 && j == 8 || i == 4 && j == 15 || i == 7 && j == 3
						|| i == 7 && j == 7 || i == 7 && j == 9 || i == 7 && j == 13 || i == 8 && j == 4
						|| i == 8 && j == 12 || i == 9 && j == 3 || i == 9 && j == 7 || i == 9 && j == 9
						|| i == 9 && j == 13 || i == 12 && j == 1 || i == 12 && j == 8 || i == 12 && j == 15
						|| i == 13 && j == 7 || i == 13 && j == 9 || i == 15 && j == 4 || i == 15 && j == 12) {

					squares[i][j] = new Square(i, j, SquareType.DOUBLE_LETTER);
					

				} else if (i == 2 && j == 6 || i == 2 && j == 10 || i == 6 && j == 2 || i == 6 && j == 6
						|| i == 6 && j == 10 || i == 6 && j == 14 || i == 10 && j == 2 || i == 10 && j == 6
						|| i == 10 && j == 10 || i == 10 && j == 14 || i == 14 && j == 6 || i == 14 && j == 10) {

					squares[i][j] = new Square(i, j, SquareType.TRIPLE_LETTER);
			

				} else if (i == 2 && j == 2 || i == 2 && j == 14 || i == 3 && j == 3 || i == 3 && j == 13
						|| i == 4 && j == 4 || i == 4 && j == 12 || i == 5 && j == 5 || i == 5 && j == 11
						|| i == 11 && j == 5 || i == 11 && j == 11 || i == 12 && j == 4 || i == 12 && j == 12
						|| i == 13 && j == 3 || i == 13 && j == 13 || i == 14 && j == 2 || i == 14 && j == 14) {
					
					squares[i][j] = new Square(i, j, SquareType.DOUBLE_WORD);
					

				} else if (i == 8 && j == 8) {
					
					squares[i][j] = new Square(i, j, SquareType.CENTRAL_SQUARE);
					

				} else {
					
					squares[i][j] = new Square(i, j, SquareType.PLAIN);
				
				}

			}

		}

	}

	public Square getNextSquare(Square currentSquare, Direction dir) {

		if (dir.equals(Direction.HORIZONTAL)) {
			return squares[currentSquare.getPosX() + 1][currentSquare.getPosY()];
		} else
			return squares[currentSquare.getPosX()][currentSquare.getPosY() - 1];
	}

	public enum Direction {
		HORIZONTAL, VERTICAL;
	}

	public void readWord() {

	}

	public String printBoard() throws Exception{
		String s = "      A    B    C    D    E    F    G    H    I    J    K    L    M    N    O   \n";

		for (int i = 1; i < SIZE ; i++) {
			if(i < 10) {
				s +="  " + i + " ";
			}else s += " " + i + " ";
			
			for(int j = 1; j < SIZE ; j++) {
				
				if(this.squares[i][j].getTile() != null) {
					
					char c = this.getSquare(i, j).getTile().getLetter();
					if(this.getSquare(i, j).getType().equals(SquareType.TRIPLE_WORD)) {
						s += ANSI.RED_BACKGROUND;

					}
					
					if(this.getSquare(i, j).getType().equals(SquareType.DOUBLE_LETTER)) {
						s += ANSI.CYAN_BACKGROUND;
						
					}
					
					if(this.getSquare(i, j).getType().equals(SquareType.TRIPLE_LETTER)) {
						s += ANSI.BLUE_BACKGROUND;
						
					}
				
					if(this.getSquare(i, j).getType().equals(SquareType.DOUBLE_WORD)) {
						s += ANSI.GREEN_BACKGROUND;
						
					}
					if(this.getSquare(i, j).getType().equals(SquareType.CENTRAL_SQUARE)) {
						s += ANSI.PURPLE_BACKGROUND;
						
					}
					s += "[ " + c + " ]" + ANSI.RESET;
					
				}else if(this.getSquare(i, j).getTile() == null) {
					
					if(this.getSquare(i, j).getType().equals(SquareType.TRIPLE_WORD)) {
						s += ANSI.RED_BACKGROUND + "[W 3]" + ANSI.RESET;
					}
					
					if(this.getSquare(i, j).getType().equals(SquareType.DOUBLE_LETTER)) {
						s += ANSI.CYAN_BACKGROUND + "[L 2]" + ANSI.RESET;
					}
					
					if(this.getSquare(i, j).getType().equals(SquareType.TRIPLE_LETTER)) {
						s += ANSI.BLUE_BACKGROUND + "[L 3]" + ANSI.RESET;
					}
				
					if(this.getSquare(i, j).getType().equals(SquareType.DOUBLE_WORD)) {
						s += ANSI.GREEN_BACKGROUND + "[W 2]" + ANSI.RESET;
					}
					if(this.getSquare(i, j).getType().equals(SquareType.CENTRAL_SQUARE)) {
						s += ANSI.PURPLE_BACKGROUND + "[W 2]" + ANSI.RESET;
						
					}if(this.getSquare(i, j).getType().equals(SquareType.PLAIN)) {
						s +=  "[   ]" ;
						
					}
				
				}
				
				
				
			}
			s += "\n";
		}
		return s;
	}
	
}
