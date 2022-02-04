package tests;

import com.Server.Model.Board;
import com.Server.Model.Tile;

public class PrintBoardTest {

	public static void main(String[] args) throws Exception {

		Board bard = new Board();
		
		bard.generateBoard();
		
		bard.getSquare(1, 1).setTile(new Tile('A'));
		bard.getSquare(1, 2).setTile(new Tile('P'));
		bard.getSquare(1, 3).setTile(new Tile('P'));
		bard.getSquare(1, 4).setTile(new Tile('L'));
		bard.getSquare(1, 5).setTile(new Tile('E'));
		
		System.out.println(bard.printBoard());
		
		
	}

}
