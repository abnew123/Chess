package backend.game;

import java.util.List;

import backend.HalfTurn;
import backend.Position;

public class UnfinishedGame {
	private List<HalfTurn> moves;
	private Position currentPosition;
	public String getWinner() {
		return "UNKNOWN";
	}

	public List<HalfTurn> getTurns() {
		return moves;
	}
	public String currentFEN() {
		String result = currentPosition.toFEN();
		
		
		return result;
	}
}
