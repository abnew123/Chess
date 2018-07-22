package frontend.displays;

import java.util.ArrayList;
import java.util.List;

import backend.HalfTurn;
import backend.Position;
import backend.game.FinishedGame;
import backend.piece.Piece;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class CapturedPiecesDisplay extends FlowPane{
	private List<List<Node>> displays;
	private int moveNumber;
	public static final int SIZE = 40;
	public CapturedPiecesDisplay(boolean color, FinishedGame game) {
		setMinHeight(SIZE);
		setMaxWidth(SIZE * 12);
		moveNumber = 0;
		displays = new ArrayList<>();
		Position position = new Position();
		displays.add(new ArrayList<>());
		int counter = 0;
		for(HalfTurn ply: game.getTurns()) {
			List<Node> display = new ArrayList<>();
			for(Node node: displays.get(counter)) {
				display.add(node);
			}
			if(ply.squarePieceWasCapturedOn() != null) {
				Piece capturedPiece = position.getPieceOnSquare(ply.squarePieceWasCapturedOn());
				if(capturedPiece.getColor() == color) {
					ImageView image = new ImageView("images/classic" + (color?"White":"Black") + capturedPiece.getClass().getSimpleName() + ".png");
					image.setFitHeight(SIZE);
					image.setFitWidth(SIZE);
					display.add(image);
				}
			}
			position.update(ply);
			displays.add(display);
			counter++;
		}
	}
	
	public void displayPrevious() {
		jumpDelta(-1);
	}
	public void displayNext() {
		jumpDelta(1);
	}
	
	private void jumpDelta(int delta) {
		getChildren().clear();
		moveNumber += delta;
		getChildren().addAll(displays.get(moveNumber));
	}
}
