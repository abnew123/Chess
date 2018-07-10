package backend.piece;
import java.util.ArrayList;
import java.util.List;

import backend.Position;
import backend.Square;
import backend.game.Game;

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
		return squares;
	}

	@Override
	public List<Square> possibleMovesFull(Game game, Square square) {
		// TODO Auto-generated method stub
		return null;
	}

    
}