package backend.piece;
import backend.Square;

public class King extends Piece {
    public King(boolean color) {
        super(color);
    }

    public String algebraicName() {
        return "K";
    }

    public String fenName() {
        return getColor() ? "K" : "k";
    }

    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        int rank = square.getRank();
        int file = square.getFile();
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r == 0 && c == 0) {
                    continue;
                }
                if (squareOnBoard((file + c), (rank + r))) {
                    sq[counter++] = new Square((file + c), (rank + r));
                }
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}