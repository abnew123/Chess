package backend.piece;

import backend.Square;

public class Queen extends Piece {
    public Queen(boolean color) {
        super(color);
    }

    public String algebraicName() {
        return "Q";
    }

    public String fenName() {
        return getColor() ? "Q" : "q";
    }

    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[64];
        int counter = 0;
        int rank = square.getRank();
        int file = square.getFile();

        for (int i = 1; i <= 8; i++) {
            char[] ranks = new char[]{(char) (rank + i), (char) (rank - i)};
            char[] files = new char[]{(char) (file + i), (char) (file - i)};
            if (squareOnBoard(files[0], ranks[0])) {
                sq[counter++] = new Square(files[0], ranks[0]);
            }
            if (squareOnBoard(files[1], ranks[0])) {
                sq[counter++] = new Square(files[1], ranks[0]);
            }
            if (squareOnBoard(files[0], ranks[1])) {
                sq[counter++] = new Square(files[0], ranks[1]);
            }
            if (squareOnBoard(files[1], ranks[1])) {
                sq[counter++] = new Square(files[1], ranks[1]);
            }
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