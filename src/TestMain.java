import backend.Square;
import backend.piece.Bishop;
import backend.piece.Piece;

public class TestMain {
	public static void main(String[] args) {
		Piece piece = new Bishop(true);
		Square[] squares = piece.movesFrom(new Square("h7"));
		for(Square square: squares) {
			System.out.println(square);
		}
	}
}
