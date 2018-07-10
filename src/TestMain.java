import backend.HalfTurn;
import backend.Position;
import backend.Square;
import backend.game.UnfinishedGame;
import backend.piece.Pawn;

public class TestMain {
	public static void main(String[] args) {
//		Position position = new Position();
//		System.out.println(position.toVisibleBoard());
		
		
		UnfinishedGame game = new UnfinishedGame();
		System.out.println(game.getPosition().getPieceOnSquare(new Square("c1")).possibleMoves(game.getPosition(), new Square("c1")));
		HalfTurn t1 = new HalfTurn(new Pawn(true), null, new Square("d2"), new Square("d4"), new Position(game.getPosition()));
		game.addPly(t1);
		System.out.println(game.getPosition().getPieceOnSquare(new Square("a1")).possibleMoves(game.getPosition(), new Square("a1")));
		HalfTurn t2 = new HalfTurn(new Pawn(true), null, new Square("d7"), new Square("d6"), new Position(game.getPosition()));
		game.addPly(t2);
		HalfTurn t3 = new HalfTurn(new Pawn(true), null, new Square("a2"), new Square("a3"), new Position(game.getPosition()));
		game.addPly(t3);
		System.out.println(game.getPosition().getPieceOnSquare(new Square("a1")).possibleMoves(game.getPosition(), new Square("a1")));
		System.out.println(game.getPGN());
//		
//		Repertoire rep = new StringRepertoire("d4");
//		Repertoire rep1 = new StringRepertoire("d5");
//		Repertoire rep2 = new StringRepertoire("Nf6");
//		rep1.addChild(new StringRepertoire("c4"));
//		rep1.addChild(new StringRepertoire("Nf3"));
//		rep2.addChild(new StringRepertoire("c4"));
//		rep2.addChild(new StringRepertoire("Nf3"));
//		rep.addChild(rep1);
//		rep.addChild(rep2);
//		System.out.println(rep);
	}
}
