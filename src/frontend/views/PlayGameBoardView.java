package frontend.views;

import java.util.List;

import backend.HalfTurn;
import backend.Position;
import backend.Square;
import backend.game.UnfinishedGame;
import backend.piece.Piece;
import backend.user.User;
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
	private boolean flag = false;
	private Piece selectedPiece = null;
	public static final int SQUARE_SIZE = 60;
	private Square source;
	private User user;
	/**
	 * Constructor for a SquareViewer. Takes in a group, that the Polygons will be added to, and a grid, which will be 
	 * used to determine the color of the Polygons.
	 * @param group
	 * @param grid
	 */

	public PlayGameBoardView(User user){
		position = new Position();
		game = new UnfinishedGame();
		onMouseClick();
		this.user = user;
		initGrid();
		updatePosition();
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
		getChildren().clear();
		getChildren().add(user.makeBoard(SQUARE_SIZE));
	}
	
	public void updatePosition() {
		getChildren().clear();
		initGrid();
		getChildren().add(user.makePieces(SQUARE_SIZE, position));
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
						}
						
							
					}
				});
			}
			
		}
	}
	private void addHighlights(Piece piece, Square source) {
		List<Square> possibleMoves = piece.possibleMovesFull(game.getPosition(), source);
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
