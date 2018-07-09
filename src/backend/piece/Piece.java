package backend.piece;

import backend.Position;
import backend.Square;

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

    public abstract Square[] movesFrom(Position position, Square square);

    public boolean squareOnBoard(int file, int rank) {
    		return (file > 0 && file < 9) && (rank > 0 && rank < 9);
    }

}
