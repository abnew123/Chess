package frontend.views;

import java.util.ArrayList;
import java.util.List;

import backend.AlertMaker;
import backend.HalfTurn;
import backend.Position;
import backend.game.FinishedGame;
import backend.user.User;
import frontend.displays.CapturedPiecesDisplay;
import javafx.scene.Group;
/**
 * models the replay game view
 * @author shichengrao
 *
 */
public class ReplayView extends Group implements View {
	
	private List<Position> positions;
	public static String pieceSet = "classic";
	private int halfTurnCount = 0;
	public static final int SQUARE_SIZE = 60;
	private User user;
	
	public ReplayView(FinishedGame game, User user){
		positions = new ArrayList<>();
		positions.add(new Position());
		for(HalfTurn ply: game.getTurns()) {
			positions.add((new Position(positions.get(positions.size() - 1)) {{update(ply);}}));
		}
		this.user = user;
		displayPosition(halfTurnCount);

	}

	private void initGrid() {
		getChildren().clear();
		getChildren().add(user.makeBoard(SQUARE_SIZE));
	}
	
	private void displayPosition(int halfMoveNumber) {
		getChildren().clear();
		initGrid();
		getChildren().add(user.makePieces(SQUARE_SIZE, positions.get(halfMoveNumber)));
	}
	
	public boolean moveForward() {
		halfTurnCount++;
		if(halfTurnCount >= positions.size()) {
			halfTurnCount--;
			AlertMaker.makeAlert("Error", "Invalid move");
			return false;
		}
		else {
			displayPosition(halfTurnCount);
			return true;
		}
		
	}
	
	public boolean moveBackward() {
		halfTurnCount--;
		if(halfTurnCount < 0) {
			halfTurnCount++;
			AlertMaker.makeAlert("Error", "Invalid move");
			return false;
		}
		else {
			displayPosition(halfTurnCount);
			return true;
		}
	}
	
	public void goToEnd(MoveListView moveList, CapturedPiecesDisplay white, CapturedPiecesDisplay black) {
		while(halfTurnCount < positions.size() - 1) {
			moveForward();
			moveList.incrementMove();
			white.displayNext();
			black.displayNext();
		}
	}
}
