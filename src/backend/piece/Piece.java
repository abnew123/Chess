package backend.piece;

import java.util.List;

import backend.Position;
import backend.Square;
import backend.game.Game;

public abstract class Piece {
	private boolean color;
	public Piece(boolean c) {
		color = c;
	}
	public boolean getColor() {
		return color;
	}

    public abstract String algebraicName();

    public abstract String fenName();

    public abstract List<Square> possibleMoves(Position position, Square square);
    
    public abstract List<Square> possibleMovesFull(Game game, Square square);

    public boolean squareOnBoard(int file, int rank) {
    		return (file > 0 && file < 9) && (rank > 0 && rank < 9);
    }

}
