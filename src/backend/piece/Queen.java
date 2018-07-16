package backend.piece;

import java.util.ArrayList;
import java.util.List;

import backend.Position;
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

    public List<Square> possibleMoves(Position position, Square square) {
		List<Square> squares = new ArrayList<>();
		for(int i = -1; i <=1; i++) {
			for(int j = -1; j <= 1; j++) {
				squares.addAll(iterate(i,j,position,square));
			}
		}
		return squares;
    }
    private List<Square> iterate(int vertical, int horizontal, Position position, Square square){
		List<Square> squares = new ArrayList<>();
		int rank = square.getRank();
	 	int file = square.getFile();
	 	for (int i = 1; i <= 8; i++) {
	 		if(squareOnBoard(file + (i * horizontal), rank + (i * vertical))) {
	 			Square test = new Square(file + (i * horizontal), rank + (i * vertical));
	 			if(position.pieceOnSquare(test)) {
	 				if(position.getPieceOnSquare(test).getColor() != getColor()) {
	 					squares.add(test);
	 				}
	 				break;
	 			}
	 			else {
	 				squares.add(test);
	 			}
	 		}
	 		else {
	 			break; 
	 		}
	 	}
	 	return squares;
    }

}