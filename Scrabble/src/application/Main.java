package application;

import java.awt.Polygon;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import wordchecker.InMemoryScrabbleWordChecker;
import wordchecker.ScrabbleWordChecker;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 0;

	private ArrayList<Tile> availableTiles;
	private Board board;
	private List<Player> players;

	private Map<Player, List<Tile>> decks = new HashMap<>();
	private Map<Player, Integer> scores = new HashMap<>();

	private ScrabbleWordChecker scrabbleWordChecker = new InMemoryScrabbleWordChecker();
	private int currentPlayerIndex;

	public static void main(String[] args) throws IOException, Exception {
		boolean running = true;

		// Assign players to game
		System.out.println("Please type player 1 name");
		// Add player 1
		Scanner sc = new Scanner(System.in);
		String playerName = sc.nextLine();
		Player player1 = new Player(0, playerName);

		// Add player 2
		System.out.println("Please type player 2 name");
		playerName = sc.nextLine();
		Player player2 = new Player(1, playerName);

		// Add all players to players list
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);

		// Generate and shuffle tiles
		ArrayList<Tile> availableTiles = new ArrayList<Tile>();
		Bag bag = new Bag();
		availableTiles = bag.tiles;
		Collections.shuffle(availableTiles);

		// Assign tiles to players deck
		for (Player player : players) {
			Deck deck = new Deck();
			player.getDeck().generateDeck(availableTiles);
			deck.generateDeck(availableTiles);
			player.setDeck(deck);
		}

		// Generate a board
		Board board = new Board();
		board.reset();
		board.toString(); // to do

		// Start playing
		// Randomly select starting player
		Random r = new Random();
		int currentPlayerIndex = r.nextInt(players.size());

		while (running) {

			// split input for move and skip
			try {
				System.out.println("Player" + currentPlayerIndex + 1 + " make your move:");
				String input = sc.nextLine();
				ArrayList<String> command = new ArrayList<>();
				input.split(" ");

				if (command.size() > 3) {
					throw new Exception("invalid input");
				} else if (command.get(1).contains(Integer))

					// if make move
					if (command.get(1).equals("Place")) {
						String word = command.get(1);
						String position = command.get(2);
						players.get(currentPlayerIndex).makeMove(word, position); // to do makeMove(String word, String
																					// position){

						players.get(currentPlayerIndex).setCurrentScore(currentPlayerIndex); // to do

						players.get(currentPlayerIndex).getScore();

						board.toString(); // to do
					}

				if (currentPlayerIndex == 0) {
					currentPlayerIndex = 1;
				} else if (currentPlayerIndex == 1) {
					currentPlayerIndex = 0;
				}

				// if skip turn
				else if (command[0] == "Skip") {
					ArrayList<String> tilesToReplace = null;
					for (int i = 1; i < command.length; i++) {
						tilesToReplace.add(command[i]);
					}
					players.get(currentPlayerIndex).skipTurn(tilesToReplace); // to do
				}

			} catch (Exception e) {
				System.out.println("invalid input");
			}

		}

	}

	public Board getBoard() {
		return board;
	}

	public int calculateScore(List<TilePlacement> placements) {
	}

	public int doMove(List<TilePlacement> placements, Player player) {
	}

	public Player getCurrentPlayer() {
		return players.get(this.currentPlayerIndex);
	}

	public List<Tile> getTiles(Player player) {
		return Collections.unmodifiableList(decks.get(player));
	}

	public void nextPlayer() {
		this.currentPlayerIndex = (this.currentPlayerIndex + 1) % players.size();
	}

	public List<Tile> pickTiles(int quantity) {
		return availableTiles;
	}

	public boolean isFinished() {
		return decks.values().stream().anyMatch(deck -> deck.size() == 0);
	}

	private int giveScore(Player player, int score) {
		return score;
	}

	private int calculateWordPoints(int direction, Square square, Board board, List<TilePlacements> placements) {
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public void start(Stage primaryStage) throws Exception {
	 * 
	 * BorderPane root = new BorderPane(); Scene scene = new
	 * Scene(root,880,880,Color.SADDLEBROWN); Stage stage = new Stage();
	 * 
	 * Image icon = new Image("icon.png",50,50, true, false);
	 * stage.getIcons().add(icon); stage.setTitle("Scrabble");
	 * 
	 * /** Image image = new Image("word.png"); ImageView imageView = new
	 * ImageView(image); imageView.setX(400); imageView.setY(400);
	 */

	/**
	 * Text text = new Text(); text.setText("Scrabble"); text.setX(50);
	 * text.setY(50); text.setFont(Font.font("Verdana",50));
	 * text.setFill(Color.WHITESMOKE);
	 */

	/**
	 * Line line = new Line(); line.setStartX(200); line.setStartY(250);
	 * line.setEndX(300); line.setEndY(250); line.setStrokeWidth(5);
	 * line.setStroke(Color.RED); line.setOpacity(0.5); line.setRotate(45);
	 */

}
