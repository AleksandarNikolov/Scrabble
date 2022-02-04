package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.Server.Model.Game;
import com.Server.Model.Player;
import com.Server.Model.Tile;

class GameTest {
	
	private String[] command;
	private String[] command1;
	private Game game;
	private ArrayList<Tile> helloTiles;
	private ArrayList<Tile> appleTiles;
	private ArrayList<Tile> elephantTiles;
	
	
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<Player> playerslist = new ArrayList<>();
		
		ArrayList<String> players = new ArrayList<>();
		String player1 = "Andrei";
		String player2 = "Aleksandar";
		players.add(player1);
		players.add(player2);
		
		game = new Game(players);
		playerslist.add(new Player(0, players.get(0)));
		playerslist.add(new Player(1, players.get(1)));
		
		String input = "place apple H8 V";
		input = input.toUpperCase();
		String input1 = "place lion H11 H";
		input1 = input1.toUpperCase(); 
		command = input.split(" ");
		command1 = input1.split(" ");
		
		
		
	}
	@Test
	void checkAdjacent() throws Exception{
		assertTrue(game.checkAdjacent(command) == true);
		game.getBoard().getSquare(8, 8).setTile(new Tile('A'));
		game.getBoard().getSquare(8, 9).setTile(new Tile('P'));
		game.getBoard().getSquare(8, 10).setTile(new Tile('P'));
		game.getBoard().getSquare(8, 11).setTile(new Tile('L'));
		game.getBoard().getSquare(8, 12).setTile(new Tile('E'));
		game.incTurn();
		game.getBoard().printBoard();
		assertTrue(game.checkAdjacent(command1) == true);
	}
	@Test
	void checkCommandLinePlace() throws Exception {
		String incorrectInput = "play apple H8 H8";
		incorrectInput = incorrectInput.toUpperCase(); 
		String[] wrongCommand = incorrectInput.split(" "); 
		assertTrue(game.checkCommandLinePlace(command) == true);
		assertTrue(game.checkCommandLinePlace(wrongCommand)== false);
	}
	@Test
	void checkCommandLineSkip() throws Exception{
		String incorrectInput = "skip A1,B2,C3";
		incorrectInput = incorrectInput.toUpperCase();
		String[] wrongCommand = incorrectInput.split(" ");
		String correctInput = "skip A,B,C";
		correctInput = correctInput.toUpperCase();
		String[] correctCommand = correctInput.split(" ");
		assertTrue(game.checkCommandLineSkip(correctCommand) == true);
		assertTrue(game.checkCommandLineSkip(wrongCommand) == false);
	}
	@Test
	void checkWord() throws Exception{
		String correctInput = "place apple H8 H";
		correctInput = correctInput.toUpperCase();
		String[] correctCommand = correctInput.split(" ");
		String wrongInput = "place aple H8 H";
		wrongInput = wrongInput.toUpperCase();
		String[] wrongCommand = wrongInput.split(" ");
		assertTrue(game.checkWord(correctCommand) == true);
		assertTrue(game.checkWord(wrongCommand) == false);
		
	}
	@Test
	void splitPosition() throws Exception{
		assertTrue(game.splitPosition("J10")[0] == 10);
		assertFalse(game.splitPosition("J10")[0] == 9);
	}
	@Test
	void splitWord() throws Exception {
		assertTrue(game.splitWord("apple")[0] == 'a');
		assertFalse(game.splitWord("apple")[0] == 1);
	}
	@Test
	void place() throws Exception{
		String correctInput = "place apple H8 H";
		correctInput = correctInput.toUpperCase();
		String[] correctCommand = correctInput.split(" ");
		String wrongInput = "place apple H8 H";
		wrongInput = wrongInput.toUpperCase();
		String[] wrongCommand = wrongInput.split(" ");
		game.place(correctCommand, game.getCurrentPlayer());
		assertTrue(game.getBoard().getSquare(8, 8).getTile().getLetter() == 'A');
	}
	@Test
	void checkWordInDeck() throws Exception {
		String correctWord = "apple";
		String wrongWord = "aple";
		assertTrue(game.checkWordInDeck(correctWord, game.getCurrentPlayer()) == true);
		assertTrue(game.checkWordInDeck(wrongWord, game.getCurrentPlayer()) == false);
	}
	@Test
	void checkSquaresUnocuppied() throws Exception{
		String input1 = "place apple H8 H";
		String input2 = "place apple H8 V";
		input1 = input1.toUpperCase();
		input2 = input2.toUpperCase();
		String[] command1 = input1.split(" ");
		String[] command2 = input2.split(" ");
		
		assertTrue(game.checkSquaresUnocuppied(command1, game.getCurrentPlayer())==true);
		assertTrue(game.checkSquaresUnocuppied(command2, game.getCurrentPlayer())==true);

	}
	@Test
	void skip() throws Exception{
		
	}
	@Test
	void checkTilesInDeck() throws Exception {
		String correctInput = "place apple H8 H";
		correctInput = correctInput.toUpperCase();
		String[] correctCommand = correctInput.split(" ");
		String wrongInput = "place apple H8 H";
		wrongInput = wrongInput.toUpperCase();
		String[] wrongCommand = wrongInput.split(" ");
		
		assertTrue(game.checkTilesInDeck(correctCommand, game.getCurrentPlayer()) == true);
		assertTrue(game.checkTilesInDeck(wrongCommand, game.getCurrentPlayer()) == false);

	}
	

	

}

