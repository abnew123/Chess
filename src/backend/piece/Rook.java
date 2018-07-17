package backend.piece;

import java.util.ArrayList;
import java.util.List;

import backend.Position;
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

    public List<Square> possibleMoves(Position position, Square square) {
		List<Square> squares = new ArrayList<>();
		squares.addAll(iterate(1,0, position, square));
		squares.addAll(iterate(-1,0, position, square));
		squares.addAll(iterate(0,1, position, square));
		squares.addAll(iterate(0,-1, position, square));
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