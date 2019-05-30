package backend.piece;
import java.util.ArrayList;
import java.util.List;

import backend.HalfTurn;
import backend.Position;
import backend.Square;
/**
 * models the king chess piece
 * @author shichengrao
 *
 */
public class King extends Piece {
    public King(boolean color) {
        super(color);
    }

    public String algebraicName() {
        return "K";
    }

    public String fenName() {
        return getColor() ? "K" : "k";
    }
    //Overridden due to castling being a special move
    @Override 
    public List<Square> possibleMovesFull(Position position, Square square){
		List<Square> squaresPossible = possibleMoves(position, square);
		
		List<Square> squares = new ArrayList<>();
		for(Square candidate: squaresPossible) {
			Position copyOfGamePosition = new Position(position);
			HalfTurn attemptedMove = new HalfTurn(copyOfGamePosition.getPieceOnSquare(square), null, square, candidate, copyOfGamePosition);
			//pre-checks if castling is even possible (since can't check legality of castling without rook
			if(square.getRank() == candidate.getRank() && square.distanceToOther(candidate) == 2) {
				if(position.pieceOnSquare(attemptedMove.castledRookDestination()[0]) && position.getPieceOnSquare((attemptedMove.castledRookDestination()[0])).algebraicName().equals("K") ) {
					
				}
				continue;
			}
			//override in Pawn class
			copyOfGamePosition.update(attemptedMove);
			if(!copyOfGamePosition.kingInCheck(color)) {
				squares.add(candidate);
			}
		}
		return squares;
}
	@Override
	public List<Square> possibleMoves(Position position, Square square) {
		List<Square> squares = new ArrayList<>();
		int[] verticalOptions = new int[] {-1, 0, 1};
		int[] horizontalOptions = new int[] {-1, 0, 1};
		for(int vertOffset: verticalOptions) {
			for(int horizOffset: horizontalOptions) {
				if(squareOnBoard(square.getFile() + horizOffset, square.getRank() + vertOffset)) {
					Square test = new Square(square.getFile() + horizOffset, square.getRank() + vertOffset);
					if(!test.equals(square)) {
						if(!position.pieceOnSquare(test) || (position.pieceOnSquare(test) && position.getPieceOnSquare(test).getColor() != getColor())){
							squares.add(test);
						}
					}
				}
			}
		}
		if(square.getRank() == (getColor()?1:8) && square.getFile() == 5) {
			squares.add(new Square(3, square.getRank()));
			squares.add(new Square(7, square.getRank()));
		}
		return squares;
	}

	

    
}