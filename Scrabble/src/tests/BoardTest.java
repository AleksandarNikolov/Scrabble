package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.Server.Model.Board;
import com.Server.Model.Board.Direction;
import com.Server.Model.Square;
import com.Server.Model.SquareType;

class BoardTest {
	
	private Board board;
	
	
	@BeforeEach
	void setUp()  {
		 board = new Board();
		
	}
	
	@Test
	void testClone() throws Exception{
		board.generateBoard();
		Board clone = board.clone(board);
		for(int x = 1; x < Board.SIZE; x++) {
			for(int y = 1; y < Board.SIZE; y++) {
				assertEquals(board.getSquare(x, y).getTile(), clone.getSquare(x, y).getTile());
			}
		}
		
	}
	@Test
	void isField() {
		assertTrue(board.isField(2, 3));
	}
	
	@Test
	void generateBoard() throws Exception{
		board.generateBoard();
		assertTrue(board.getSquare(0, 0)==null);
		assertEquals(board.getSquare(8, 8).getType(), SquareType.CENTRAL_SQUARE);
		assertEquals(board.getSquare(1, 1).getType(), SquareType.TRIPLE_WORD);
		assertEquals(board.getSquare(2, 2).getType(), SquareType.DOUBLE_WORD);
		assertEquals(board.getSquare(2, 6).getType(), SquareType.TRIPLE_LETTER);
		assertEquals(board.getSquare(4, 1).getType(), SquareType.DOUBLE_LETTER);
	}
	@Test
	void getNextSquare() throws Exception{
		board.generateBoard();
		Square currentSquare = board.getSquare(1, 1);
		assertTrue(board.getNextSquare(currentSquare,	Direction.HORIZONTAL) == board.getSquare(2, 1) );
		assertTrue(board.getNextSquare(currentSquare, Direction.VERTICAL) == board.getSquare(1, 2));
	}
	

}
