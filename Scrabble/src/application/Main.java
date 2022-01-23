package application;
	
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	
	private List<Tile> availableTiles;
	private Board board;
	private List<Player> players;
	
	private Map<Player, List<Tile>> decks = new HashMap<>();
	private Map<Player, Integer> scores = new HashMap<>();
	
	private int currentPlayerIndex;
	
	private ScrabbleWordChecker scrabbleWordChecker = new InMemoryScrabbleWordChecker();
	
	public Main(List<Player> players) {
		//Generate and shuffle tiles
		Bag bag = new Bag();
		this.availableTiles = bag.tiles;
		Collections.shuffle(this.availableTiles);
	
		
		//Generate a board
		Board board = new Board();
		this.board = board;
		
		//Assign players to the game
		this.players = players;
		Random r = new Random();
		currentlyPlayerIndex = r.nextInt(players.size());
		
		//Assign tiles to players, and
		for(Player player : players) {
			List<Tile> handOut = availableTiles.subList(0,  7);
			decks.put(player, new ArrayList<>(handOut));
			availableTiles.removeAll(handOut);
			
		}
	
	}
	
	public Board getBoard() {
		return board;
	}
	public int calculateScore(List<TilePlacement> placements) {}
	
	public int doMove(List<TilePlacement> placements, Player player) {}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	public List<Tile> getTiles(Player player) {
		return Collections.unmodifiableList(decks.get(player));
	}
	public void nextPlayer() {
		this.currentPlayerIndex = (this.currentPlayerIndex + 1) % players.size();
	}
	public List<Tile> pickTiles(int quantity){
		return availableTiles;
	}
	
	public boolean isFinished() {
		return decks.values().stream().anyMatch(deck -> deck.size() == 0);
	}
	
	private int giveScore(Player player, int score) {
		return score;
	}
	
	
	
	
	
	
	private int calculateWordPoints(int direction, Square square, Board board, List<TilePlacements> placements) {}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,880,880,Color.SADDLEBROWN);
			Stage stage = new Stage();
			
			Image icon = new Image("icon.png",50,50, true, false); 
			stage.getIcons().add(icon);
			stage.setTitle("Scrabble");
			
			/**
			Image image = new Image("word.png");
			ImageView imageView = new ImageView(image);
			imageView.setX(400);
			imageView.setY(400);
			*/
			
			/**
			Text text = new Text();
			text.setText("Scrabble");	
			text.setX(50);
			text.setY(50);
			text.setFont(Font.font("Verdana",50));
			text.setFill(Color.WHITESMOKE);
			*/
			
			/**
			Line line = new Line();
			line.setStartX(200);
			line.setStartY(250);
			line.setEndX(300);
			line.setEndY(250);
			line.setStrokeWidth(5);
			line.setStroke(Color.RED);
			line.setOpacity(0.5);
			line.setRotate(45);
			*/
			
			
	
			
			
			int numBands = 15;
			int rectWidth = 50;
			double rectHeight = 50;
			for (int i = 1 ; i <= numBands ; i++) {
				
				for (int j = 1 ; j <= numBands ; j++){
					Font font = Font.font("Verdana", FontWeight.BOLD, 17);
					Rectangle rectangle = new Rectangle((rectWidth +2) * i,(rectHeight +2) * j, rectWidth, rectHeight );
							rectangle.setStroke(Color.WHITE);
							rectangle.setFill(Color.BEIGE);
							Text multiplierT = new Text("");
							multiplierT.setX(((rectWidth + 2) * i + 11));
							multiplierT.setY(((rectHeight + 2) * j + 31));
							multiplierT.setFont(font);
				
							if (i == 1 & j == 1 || i == 1 && j == 8 || i == 1 && j == 15 || i == 8 && j == 1 || i == 8 && j == 15 || i == 15 && j == 1 || i == 15 && j == 8 || i == 15 && j == 15) {
								rectangle.setStroke(Color.WHITE);
								rectangle.setFill(Color.RED);
								multiplierT.setText("W3");
								multiplierT.setFont(font);
								
							}else if (	i == 1 && j == 4 || i == 1 && j == 12 ||
										i == 3 && j == 7 || i == 3 && j == 9 ||
										i == 4 && j == 1|| i == 4 && j == 8 || i == 4 && j == 15 ||
										i == 7 && j == 3 || i == 7 && j == 7 || i == 7 && j == 9 || i == 7 && j == 13 ||
										i == 8 && j == 4 || i == 8 && j == 12||
									  	i == 9 && j == 3 || i == 9 && j == 7|| i == 9 && j == 9 || i == 9 && j == 13 ||
									  	i == 12 && j == 1|| i == 12 && j == 8 || i == 12 && j == 15 ||
									  	i == 13 && j == 7 || i == 13 && j == 9 ||
									  	i == 15 && j == 4 || i == 15 && j == 12 ){
								rectangle.setStroke(Color.WHITE);
								rectangle.setFill(Color.LIGHTBLUE);
								multiplierT.setText("L2");
								multiplierT.setFont(font);
							}else if (	i == 2 && j == 6 || i == 2 && j == 10 ||
									  	i == 6 && j == 2 || i == 6 && j == 6 || i == 6 && j == 10 || i == 6 && j == 14||
									  	i == 10 && j == 2 || i == 10 && j == 6 || i == 10 && j == 10 || i == 10 && j == 14||
									  	i == 14 && j == 6 || i == 14 && j == 10){
								rectangle.setStroke(Color.WHITE);
								rectangle.setFill(Color.BLUE);
								multiplierT.setText("L3");
								multiplierT.setFont(font);
							}else if (	i == 2 && j == 2 || i == 2 && j == 14 ||
										i == 3 && j == 3 || i == 3 && j == 13 ||
										i == 4 && j == 4 || i == 4 && j == 12 ||
										i == 5 && j == 5 || i == 5 && j == 11 ||
										i == 11 && j == 5 || i == 11 && j == 11 ||
										i == 12 && j == 4 || i == 12 && j == 12 ||		
										i == 13 && j == 3 || i == 13 && j == 13 ||
										i == 14 && j == 2 || i == 14 && j == 14){
								rectangle.setStroke(Color.WHITE);
								rectangle.setFill(Color.LIGHTPINK);
								multiplierT.setText("W2");
								multiplierT.setFont(font);
							}
							else if (	i == 8 && j == 8){
							rectangle.setStroke(Color.WHITE);
							rectangle.setFill(Color.LIGHTPINK);
							multiplierT.setX(((rectWidth + 2) * i + 18));
							multiplierT.setY(((rectHeight + 2) * j + 31));
							multiplierT.setText("X");
							multiplierT.setFont(font);
						}
							
				    	    root.getChildren().add(rectangle);
				    	    root.getChildren().add(multiplierT);
				    
				
			
				}
			
			}
			
			//root.getChildren().add(text);
			//root.getChildren().add(line);
			//root.getChildren().add(imageView);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			
	
	}
	
	
}
