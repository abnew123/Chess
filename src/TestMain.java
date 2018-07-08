import backend.HalfTurn;
import backend.Position;
import backend.Square;
import backend.game.UnfinishedGame;
import backend.piece.Knight;
import backend.piece.Pawn;

public class TestMain {
	public static void main(String[] args) {
//		Position position = new Position();
//		System.out.println(position.toFEN());
		UnfinishedGame game = new UnfinishedGame();
		HalfTurn t1 = new HalfTurn(new Knight(true), null, new Square("g1"), new Square("f3"), new Position());
		game.addPly(t1);
		HalfTurn t2 = new HalfTurn(new Knight(false), null, new Square("g8"), new Square("f6"), new Position());
		game.addPly(t2);
		HalfTurn t3 = new HalfTurn(new Pawn(true), null, new Square("d2"), new Square("d4"), new Position());
		game.addPly(t3);
		System.out.println(game.getPGN());
	}
}
