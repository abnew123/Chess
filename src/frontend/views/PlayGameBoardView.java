package frontend.views;

import java.util.List;

import backend.HalfTurn;
import backend.Position;
import backend.Square;
import backend.game.UnfinishedGame;
import backend.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Jennifer Chin
 * 
 * Implements the Viewer interface. Class created to viewer a square grid. Creates a Rectangle object to represent 
 * each cell of the grid and colors the object accordingly.
 */

public class PlayGameBoardView extends Group implements View {
	
	private UnfinishedGame game;
	private Position position;
	public static String pieceSet = "classic";
	private boolean flag = false;
	private Piece selectedPiece = null;
	public static final int SQUARE_SIZE = 60;
	private Square source;
	/**
	 * Constructor for a SquareViewer. Takes in a group, that the Polygons will be added to, and a grid, which will be 
	 * used to determine the color of the Polygons.
	 * @param group
	 * @param grid
	 */

	public PlayGameBoardView(){
		position = new Position();
		initGrid();
		game = new UnfinishedGame();
		onMouseClick();
	}
	
	/**
	 * Visualizes a square grid. Takes in the x and y position of the upper left corner of the grid, the grid width, 
	 * grid height, number of grid rows, and number of column rows. Iterates over the Grid to create a Rectangle for 
	 * each cell. Uses the parameters to calculate the x and y position of the top left corner of each Rectangle, 
	 * whose fill is determined by the State of its corresponding Cell in the Grid. All cell visuals are then added 
	 * to the Group.
	 * @param x
	 * @param y
	 * @param gridWidth
	 * @param gridHeight
	 * @param gridRows
	 * @param gridCols
	 * @return Group
	 */

	public void initGrid() {
		double xStep = SQUARE_SIZE;
		double yStep = SQUARE_SIZE;
		double xPos = 0;
		double yPos = 0;
		boolean flip = true;
		for (int r = 8; r >= 1; r--){
			for (int c = 1; c < 9; c++){
				Rectangle cellVisual = new Rectangle(xPos, yPos, xStep, yStep);
				//will be customizable
				cellVisual.setFill(flip?Color.GREEN:Color.WHITE);
				flip = !flip;
				getChildren().add(cellVisual);
				Piece piece = position.getPieceOnSquare(new Square(c,r));
				if(piece != null) {
					ImageView image = new ImageView();
					image.setFitHeight(yStep);
					image.setFitWidth(xStep);
					image.setX(xPos);
					image.setY(yPos);
					image.setImage(new Image("images/" + pieceSet + (piece.getColor()?"White":"Black") + piece.getClass().getSimpleName() + ".png"));
					getChildren().add(image);
				}
				xPos += xStep;
			}
			flip = !flip;
			yPos += yStep;
			xPos = 0;
		}
	}
	public void onMouseClick() {
		for(Node node: getChildren()){
			if(node.getClass().equals((new Rectangle()).getClass())) {
				double xPos = node.getBoundsInLocal().getMinX();
				double yPos = node.getBoundsInLocal().getMinY();
				node.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override public void handle(MouseEvent e){
						if(flag) {
							Square destination = new Square(1 + (int)xPos/SQUARE_SIZE, 8 - (int)yPos/SQUARE_SIZE);
							HalfTurn move = new HalfTurn(selectedPiece, null, source, destination, position);
							if(game.addPly(move)) {
								position.update(move);
								initGrid();
							}
							flag = false;
						}
						else {
							source = new Square(1 + (int)xPos/SQUARE_SIZE, 8 - (int)yPos/SQUARE_SIZE);
							if(position.getPieceOnSquare(source) != null) {
								selectedPiece = position.getPieceOnSquare(source);
								//highlights the squares that the piece can go
								addHighlights(selectedPiece, source);
								flag = true;
							}
							System.out.println(source);
						}
						
							
					}
				});
			}
			
		}
	}
	private void addHighlights(Piece piece, Square source) {
		List<Square> possibleMoves = piece.possibleMovesFull(game, source);
		System.out.println(possibleMoves);
		for(Square possibility: possibleMoves) {
			ImageView image = new ImageView();
			image.setFitHeight(SQUARE_SIZE);
			image.setFitWidth(SQUARE_SIZE);
			image.setX((possibility.getFile() - 1) * SQUARE_SIZE);
			image.setY((8 - possibility.getRank())*SQUARE_SIZE);
			image.setImage(new Image("images/rcd.png"));
			getChildren().add(image);
		}
	}
}
