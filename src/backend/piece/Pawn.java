package backend.piece;

import java.util.ArrayList;
import java.util.List;

import backend.Position;
import backend.Square;
import backend.game.UnfinishedGame;

/**
 * models the pawn chess piece
 * 
 * @author shichengrao
 *
 */
public class Pawn extends Piece {
	public Pawn(boolean color) {
		super(color);
	}

	public String algebraicName() {
		return "";
	}

	public String fenName() {
		return getColor() ? "P" : "p";
	}

	@Override
	public List<Square> possibleMoves(Position position, Square square) {
		List<Square> squares = new ArrayList<>();
		int moveDirection = (getColor() ? 1 : -1);
		// moves
		if (!position.pieceOnSquare(new Square(square.getFile(), square.getRank() + moveDirection))) {
			squares.add(new Square(square.getFile(), square.getRank() + moveDirection));
		}
		if ((getColor() && square.getRank() == 2) || (!getColor() && square.getRank() == 7)) {
			if (!position.pieceOnSquare(new Square(square.getFile(), square.getRank() + (moveDirection * 2)))
					&& !position.pieceOnSquare(new Square(square.getFile(), square.getRank() + (moveDirection)))) {
				squares.add(new Square(square.getFile(), square.getRank() + (moveDirection * 2)));
			}
		}
		// captures
		if (checkEnemyPieceOnSquare(1, moveDirection, position, square)) {
			squares.add(new Square(square.getFile() + 1, square.getRank() + moveDirection));
		}
		if (checkEnemyPieceOnSquare(-1, moveDirection, position, square)) {
			squares.add(new Square(square.getFile() - 1, square.getRank() + moveDirection));
		}
		// enpassant
		if (square.getRank() == (color ? 5 : 4)) {
			// The tertiary layer of nested ifs (i.e. the !position.pieceOnSquare(test)) are
			// technically unnecessary if enpassant is set up correctly
			if (checkEnemyPieceOnSquare(1, 0, position, square)) {
				Square test = new Square(square.getFile() + 1, square.getRank() + moveDirection);
				if (!position.pieceOnSquare(test)) {
					squares.add(new Square(square.getFile() + 1, square.getRank() + moveDirection));
				}
			}
			if (checkEnemyPieceOnSquare(-1, 0, position, square)) {
				Square test = new Square(square.getFile() + 1, square.getRank() + moveDirection);
				if (!position.pieceOnSquare(test)) {
					squares.add(new Square(square.getFile() + 1, square.getRank() + moveDirection));
				}
			}
		}
		return squares;
	}

	private boolean checkEnemyPieceOnSquare(int fileDelta, int rankDelta, Position position, Square square) {
		if (squareOnBoard(square.getFile() + fileDelta, square.getRank() + rankDelta)) {
			Square test = new Square(square.getFile() + fileDelta, square.getRank() + rankDelta);
			if (position.pieceOnSquare(test)) {
				if (position.getPieceOnSquare(test).getColor() != getColor()) {
					return true;
				}
			}
		}
		return false;
	}
}