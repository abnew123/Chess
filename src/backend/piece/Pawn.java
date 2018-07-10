package backend.piece;

import java.util.ArrayList;
import java.util.List;

import backend.Position;
import backend.Square;
import backend.game.Game;

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
    /**
     * NOTE: does not include enpassant 
     */
	@Override
	public List<Square> possibleMoves(Position position, Square square) {
		List<Square> squares = new ArrayList<>();
		int moveDirection = (getColor()?1:-1);
		//moves
		if(!position.pieceOnSquare(new Square(square.getFile(), square.getRank() + moveDirection))) {
			squares.add(new Square(square.getFile(), square.getRank() + moveDirection));
		}
		if((getColor() && square.getRank() == 2) || (!getColor() && square.getRank() == 7)) {
			if(!position.pieceOnSquare(new Square(square.getFile(), square.getRank() + (moveDirection * 2)))) {
				squares.add(new Square(square.getFile(), square.getRank() + (moveDirection * 2)));
			}
		}
		//captures
		if(checkEnemyPieceOnSquare(1, moveDirection, position, square)) {
			squares.add(new Square(square.getFile() + 1, square.getRank() + moveDirection));
		}
		if(checkEnemyPieceOnSquare(-1, moveDirection, position, square)) {
			squares.add(new Square(square.getFile() - 1, square.getRank() + moveDirection));
		}
		return squares;
	}
	private boolean checkEnemyPieceOnSquare(int fileDelta, int rankDelta, Position position, Square square) {
		if(squareOnBoard(square.getFile() + fileDelta, square.getRank() + rankDelta)) {
			Square test = new Square(square.getFile() + fileDelta, square.getRank() + rankDelta);
			if(position.pieceOnSquare(test)) {
				if(position.getPieceOnSquare(test).getColor() != getColor()) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public List<Square> possibleMovesFull(Game game, Square square) {
		// TODO Auto-generated method stub
		return null;
	}
}