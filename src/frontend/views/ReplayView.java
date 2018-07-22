package frontend.views;

import java.util.ArrayList;
import java.util.List;

import backend.AlertMaker;
import backend.HalfTurn;
import backend.Position;
import backend.game.FinishedGame;
import backend.user.User;
import javafx.scene.Group;

/**
 * @author Jennifer Chin
 * 
 * Implements the Viewer interface. Class created to viewer a square grid. Creates a Rectangle object to represent 
 * each cell of the grid and colors the object accordingly.
 */

public class ReplayView extends Group implements View {
	
	private List<Position> positions;
	public static String pieceSet = "classic";
	private int halfTurnCount = 0;
	public static final int SQUARE_SIZE = 60;
	private User user;
	/**
	 * Constructor for a SquareViewer. Takes in a group, that the Polygons will be added to, and a grid, which will be 
	 * used to determine the color of the Polygons.
	 * @param group
	 * @param grid
	 */

	public ReplayView(FinishedGame game, User user){
		positions = new ArrayList<>();
		positions.add(new Position());
		for(HalfTurn ply: game.getTurns()) {
			positions.add((new Position(positions.get(positions.size() - 1)) {{update(ply);}}));
		}
		this.user = user;
		displayPosition(halfTurnCount);

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
	
	private void displayPosition(int halfMoveNumber) {
		getChildren().clear();
		initGrid();
		getChildren().add(user.makePieces(SQUARE_SIZE, positions.get(halfMoveNumber)));
	}
	public void moveForward() {
		halfTurnCount++;
		if(halfTurnCount >= positions.size()) {
			halfTurnCount--;
			AlertMaker.makeAlert("Error", "Invalid move");
		}
		else {
			displayPosition(halfTurnCount);
		}
		
	}
	
	public void moveBackward() {
		halfTurnCount--;
		if(halfTurnCount < 0) {
			halfTurnCount++;
			AlertMaker.makeAlert("Error", "Invalid move");
		}
		else {
			displayPosition(halfTurnCount);
		}
	}
}
