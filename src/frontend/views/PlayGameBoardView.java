package frontend.views;

import backend.Position;
import backend.Square;
import backend.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Jennifer Chin
 * 
 * Implements the Viewer interface. Class created to viewer a square grid. Creates a Rectangle object to represent 
 * each cell of the grid and colors the object accordingly.
 */

public class PlayGameBoardView extends Group implements View {
	
	private Position position;
	public static String pieceSet = "classic";
	private boolean flag = false;
	private Piece selectedPiece = null;
	public static final int SQUARE_SIZE = 60;
	/**
	 * Constructor for a SquareViewer. Takes in a group, that the Polygons will be added to, and a grid, which will be 
	 * used to determine the color of the Polygons.
	 * @param group
	 * @param grid
	 */

	public PlayGameBoardView(){
		position = new Position();
		initGrid();
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
		for (int r = 1; r < 9; r++){
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
							Square destination = new Square((int)yPos/SQUARE_SIZE, (int)xPos/SQUARE_SIZE);
							
						}
						else {
							Square source = new Square((int)yPos/SQUARE_SIZE, (int)xPos/SQUARE_SIZE);
							if(position.getPieceOnSquare(source) != null) {
								selectedPiece = position.getPieceOnSquare(source);
								flag = true;
							}
							System.out.println(source);
						}
						
							
					}
				});
			}
			
		}
	}
}
