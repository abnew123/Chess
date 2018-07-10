package backend.piece;

import java.util.ArrayList;
import java.util.List;

import backend.Position;
import backend.Square;
import backend.game.Game;

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

	@Override
	public List<Square> possibleMoves(Position position, Square square) {
		List<Square> squares = new ArrayList<>();
		int[] verticalOptions = new int[] {2, -2, 2, -2, 1, -1, 1, -1};
		int[] horizontalOptions = new int[] {1, -1, -1, 1, 2, -2, -2, 2};
		for(int i = 0; i < verticalOptions.length; i++) {
			if(squareOnBoard(square.getFile() + horizontalOptions[i], square.getRank() + verticalOptions[i])) {
				Square test = new Square(square.getFile() + horizontalOptions[i], square.getRank() + verticalOptions[i]);
				if(!test.equals(square)) {
					if(!position.pieceOnSquare(test) || (position.pieceOnSquare(test) && position.getPieceOnSquare(test).getColor() != getColor())){
						squares.add(test);
					}
				}
			}
			
		}
		return squares;
	}

    
}