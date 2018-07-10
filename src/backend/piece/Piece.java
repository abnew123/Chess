package backend.piece;

import java.util.ArrayList;
import java.util.List;

import backend.HalfTurn;
import backend.Position;
import backend.Square;
import backend.game.Game;
import backend.game.UnfinishedGame;

public abstract class Piece {
	private boolean color;
	public Piece(boolean c) {
		color = c;
	}
	public boolean getColor() {
		return color;
	}

    public abstract String algebraicName();

    public abstract String fenName();

    public abstract List<Square> possibleMoves(Position position, Square square);
    
    public List<Square> possibleMovesFull(Game game, Square square){
    		List<Square> squaresPossible = possibleMoves(game.getPosition(), square);
    		List<Square> squares = new ArrayList<>();
    		for(Square candidate: squaresPossible) {
    			Position copyOfGamePosition = new Position(game.getPosition());
    			//override in Pawn class
    			copyOfGamePosition.update(new HalfTurn(copyOfGamePosition.getPieceOnSquare(square), null, square, candidate, copyOfGamePosition));
    			if(!copyOfGamePosition.kingInCheck(color)) {
    				squares.add(candidate);
    			}
    		}
    		return squares;
    }

    public boolean squareOnBoard(int file, int rank) {
    		return (file > 0 && file < 9) && (rank > 0 && rank < 9);
    }

}
