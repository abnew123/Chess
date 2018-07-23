package backend.game;

import java.util.ArrayList;

import backend.HalfTurn;
import backend.Position;

public class UnfinishedGame extends Game {
	
	public UnfinishedGame() {
		moves = new ArrayList<>();
		positions = new ArrayList<>();
		positions.add(new Position());
	}
	
	public boolean addPly(HalfTurn ply) {
		if(ply.playable() && ((moves.size() % 2 == 0) != ply.pieceColor())) {
			moves.add(ply);
			Position currentPosition = new Position(positions.get(positions.size() - 1));
			currentPosition.update(ply);
			positions.add(currentPosition);
			return true;
		}
		return false;
		
	}
	
	public String currentFEN() {
		String result = positions.get(positions.size() - 1).toSimplifiedFEN();
		//TODO add rest of FEN (like pawn moves, castling, etc...)
		
		return result;
	}
	
	
}
