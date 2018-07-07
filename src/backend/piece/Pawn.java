package backend.piece;

import backend.Square;

public class Pawn extends Piece {
    public Pawn(boolean color) {
        super(color);
    }

    public String algebraicName() {
        return "";
    }

    public String fenName() {
        return getColor()? "P" : "p";
    }

    public Square[] movesFrom(Square square) {
        int rank = square.getRank();
        int file = square.getFile();
        if (getColor()) {
            if (rank == '8') {
                return new Square[0];
            } else if (rank == '2') {
                return new Square[]{new Square(file, '4'), new Square(file, '3')};
            } else {
                return new Square[]{new Square(file,  (rank + 1))};
            }
        } else {
            if (rank == '1') {
                return new Square[0];
            } else if (rank == '7') {
                return new Square[]{new Square(file, '5'), new Square(file, '6')};
            } else {
                return new Square[]{new Square(file,  (rank - 1))};
            }
        }
    }
}