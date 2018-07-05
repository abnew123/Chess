package backend;

public abstract class Piece {
	public abstract boolean getColor();

    public abstract String algebraicName();

    public abstract String fenName();

    public abstract Square[] movesFrom(Square square);

    public abstract String toString();
   
}
