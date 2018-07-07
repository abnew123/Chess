package backend.piece;

import backend.Square;

public class Rook extends Piece {
    public Rook(boolean color) {
        super(color);
    }

    public String algebraicName() {
        return "R";
    }

    public String fenName() {
        return getColor()? "R" : "r";
    }

    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[27];
        int counter = 0;
        int rank = square.getRank();
        int file = square.getFile();
        for (int i = 1; i <= 8; i++) {
            int[] ranks = new int[]{ (rank + i),  (rank - i)};
            int[] files = new int[]{ (file + i),  (file - i)};
            if (squareOnBoard(files[0], rank)) {
                sq[counter++] = new Square(files[0], rank);
            }
            if (squareOnBoard(files[1], rank)) {
                sq[counter++] = new Square(files[1], rank);
            }
            if (squareOnBoard(file, ranks[0])) {
                sq[counter++] = new Square(file, ranks[0]);
            }
            if (squareOnBoard(file, ranks[1])) {
                sq[counter++] = new Square(file, ranks[1]);
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}