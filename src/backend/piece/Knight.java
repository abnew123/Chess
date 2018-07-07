package backend.piece;

import backend.Square;

public class Knight extends Piece {
    public Knight(boolean color) {
        super(color);
    }

    public String algebraicName() {
        return "N";
    }

    public String fenName() {
        return getColor() ? "N" : "n";
    }

    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        int rank = square.getRank();
        int file = square.getFile();
        int[] ranks = new int[]{ (rank - 2),  (rank - 1),  (rank + 1),  (rank + 2)};
        int[] files = new int[]{ (file - 2),  (file - 1),  (file + 1),  (file + 2)};

        if (squareOnBoard(files[0], ranks[1])) {
            sq[counter++] = new Square(files[0], ranks[1]);
        }
        if (squareOnBoard(files[0], ranks[2])) {
            sq[counter++] = new Square(files[0], ranks[2]);
        }
        if (squareOnBoard(files[1], ranks[0])) {
            sq[counter++] = new Square(files[1], ranks[0]);
        }
        if (squareOnBoard(files[1], ranks[3])) {
            sq[counter++] = new Square(files[1], ranks[3]);
        }
        if (squareOnBoard(files[2], ranks[0])) {
            sq[counter++] = new Square(files[2], ranks[0]);
        }
        if (squareOnBoard(files[2], ranks[3])) {
            sq[counter++] = new Square(files[2], ranks[3]);
        }
        if (squareOnBoard(files[3], ranks[1])) {
            sq[counter++] = new Square(files[3], ranks[1]);
        }
        if (squareOnBoard(files[3], ranks[2])) {
            sq[counter++] = new Square(files[3], ranks[2]);
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}