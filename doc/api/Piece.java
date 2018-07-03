package images;

public interface Piece {
	public Color getColor() {
        return color;
    }

    public boolean isInBoard(char file, char rank) ;

    public abstract String algebraicName();

    public abstract String fenName();

    public abstract Square[] movesFrom(Square square);

    public String toString();
}
