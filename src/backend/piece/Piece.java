package backend.piece;

import java.util.ArrayList;
import java.util.List;

import backend.HalfTurn;
import backend.Position;
import backend.Square;
import backend.game.Game;
/**
 * models a piece in chess
 * @author shichengrao
 *
 */

//TODO: add abstract ToString that says something like "piece on square"
public abstract class Piece {
	protected boolean color;
	public Piece(boolean c) {
		color = c;
	}
	/**
	 * returns the color of the piece
	 * @return
	 */
	public boolean getColor() {
		return color;
	}
	/**
	 * returns the PGN name for a piece
	 * @return
	 */
    public abstract String algebraicName();
    /**
     * returns the FEN name for a piece
     * @return
     */
    public abstract String fenName();
    /**
     * returns a list of all possible move that can be determined from a position
     * @param position
     * @param square
     * @return
     */
    public abstract List<Square> possibleMoves(Position position, Square square);
    /**
     * filters moves from possible Moves to distill down to legal moves
     * TODO: make separate method for captures? since pawns capture and move differently
     * @param position
     * @param square
     * @return
     */
    public List<Square> possibleMovesFull(Position position, Square square){
    		List<Square> squaresPossible = possibleMoves(position, square);
    		List<Square> squares = new ArrayList<>();
    		for(Square candidate: squaresPossible) {
    			Position copyOfGamePosition = new Position(position);
    			//override in Pawn class
    			copyOfGamePosition.update(new HalfTurn(copyOfGamePosition.getPieceOnSquare(square), null, square, candidate, copyOfGamePosition));
    			if(!copyOfGamePosition.kingInCheck(color)) {
				squares.add(candidate);
    			}
    		}
    		return squares;
    }
    //to solve castling and en passant stuff
    //TODO: actually do
    public List<Square> possibleMovesGivenGame(Game game, Square square, int moveNumber){
    		return possibleMovesFull(game.getPosition(moveNumber), square);
    }
    /**
     * check if a given square would exist on a normal chess board
     * @param file
     * @param rank
     * @return
     */
    public boolean squareOnBoard(int file, int rank) {
    		return (file > 0 && file < 9) && (rank > 0 && rank < 9);
    }

}
