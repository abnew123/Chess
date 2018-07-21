package backend.user;

import java.util.ArrayList;
import java.util.List;

import backend.Position;
import backend.Square;
import backend.game.FinishedGame;
import backend.piece.Piece;
import frontend.buttons.ButtonFactory;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class User {
	private List<FinishedGame> history;
	private String name;
	//non-cosmetic 
	private boolean showPossibleMoves;
	private boolean autoQueen;
	
	//cosmetic
	//TODO change next options to string at some point
	private boolean pieceSet;
	private boolean boardScheme;
	public User(String name) {
		history = new ArrayList<>();
		this.name = name;
		showPossibleMoves = true;
		autoQueen = true;
		pieceSet = true;
		boardScheme = true;
	}
	
	public void addGame(FinishedGame game) {
		history.add(game);
	}
	
	public String getName() {
		return name;
	}
	
	public String getStats() {
		String stats = "";
		stats += "Won: " + history.stream().filter(g->g.getWinner().equals("WHITE")).count() + "\n";
		stats += "Drawn: " + history.stream().filter(g->g.getWinner().equals("DRAW")).count() + "\n";
		stats += "Lost: " + history.stream().filter(g->g.getWinner().equals("BLACK")).count();
		return stats;
	}
	
	public HBox makeCosmeticSelector(){
		HBox cosmetic = new HBox();
		cosmetic.getChildren().addAll(
				ButtonFactory.makeButton("Switch to " + (pieceSet?"fancy":"classic") + " piece set", e->updatePieces(), "image_button"),
				ButtonFactory.makeButton("Switch to " + (boardScheme?"fancy":"classic") + " board scheme", e->updateBoard(), "image_button")
				);
		return cosmetic;
	}
	
	public HBox makeNonCosmeticSelector(){
		HBox noncosmetic = new HBox();
		noncosmetic.getChildren().addAll(
				ButtonFactory.makeButton((showPossibleMoves?"Do not s":"S") + "how possible moves", e->updateShowPossibleMoves(), "image_button"),
				ButtonFactory.makeButton("Turn " + (autoQueen?"off":"on") + " auto queening", e->updateAutoQueen(), "image_button")
				);
		return noncosmetic;
	}
	
	public void updateShowPossibleMoves() {
		showPossibleMoves = !showPossibleMoves;
	}
	
	public void updateAutoQueen() {
		autoQueen = !autoQueen;
	}
	//TODO make this more varied
	public void updatePieces() {
		pieceSet = !pieceSet;
	}
	
	public void updateBoard() {
		boardScheme = !boardScheme;
	}
	
	public Group makeBoard(int size) {
		Group group = new Group();
		double xStep = size;
		double yStep = size;
		double xPos = 0;
		double yPos = 0;
		boolean flip = true;
		for (int r = 1; r < 9; r++){
			for (int c = 1; c < 9; c++){
				Rectangle cellVisual = new Rectangle(xPos, yPos, xStep, yStep);
				cellVisual.setFill(flip?boardScheme?Color.GREEN:Color.GRAY:Color.WHITE);
				flip = !flip;
				group.getChildren().add(cellVisual);
				xPos += xStep;
			}
			flip = !flip;
			yPos += yStep;
			xPos = 0;
		}
		return group;
	}
	public Group makePieces(int size, Position position) {
		Group group = new Group();
		double xStep = size;
		double yStep = size;
		double xPos = 0;
		double yPos = 0;
		for (int r = 8; r >= 1; r--){
			for (int c = 1; c < 9; c++){
				Piece piece = position.getPieceOnSquare(new Square(c,r));
				if(piece != null) {
					ImageView image = new ImageView();
					image.setFitHeight(yStep);
					image.setFitWidth(xStep);
					image.setX(xPos);
					image.setY(yPos);
					image.setImage(new Image("images/" + (pieceSet?"classic":"fancy") + (piece.getColor()?"White":"Black") + piece.getClass().getSimpleName() + ".png"));
					group.getChildren().add(image);
				}
				xPos += xStep;
			}
			yPos += yStep;
			xPos = 0;
		}
		return group;
	}
	
	public boolean getBoard() {
		return boardScheme;
	}
}
