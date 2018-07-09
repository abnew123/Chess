import backend.repertoire.Repertoire;
import backend.repertoire.StringRepertoire;

public class TestMain {
	public static void main(String[] args) {
//		Position position = new Position();
//		System.out.println(position.toFEN());
		
		
//		UnfinishedGame game = new UnfinishedGame();
//		HalfTurn t1 = new HalfTurn(new Pawn(true), null, new Square("d2"), new Square("d4"), new Position(game.getPosition()));
//		game.addPly(t1);
//		HalfTurn t2 = new HalfTurn(new Pawn(true), null, new Square("d7"), new Square("d6"), new Position(game.getPosition()));
//		game.addPly(t2);
//		HalfTurn t3 = new HalfTurn(new Pawn(true), null, new Square("d4"), new Square("d5"), new Position(game.getPosition()));
//		game.addPly(t3);
//		HalfTurn t4 = new HalfTurn(new Pawn(true), null, new Square("e7"), new Square("e5"), new Position(game.getPosition()));
//		game.addPly(t4);
//		HalfTurn t5 = new HalfTurn(new Pawn(true), null, new Square("d5"), new Square("e6"), new Position(game.getPosition()));
//		game.addPly(t5);
//		System.out.println(game.getPGN());
//		
		Repertoire rep = new StringRepertoire("d4");
		Repertoire rep1 = new StringRepertoire("d5");
		Repertoire rep2 = new StringRepertoire("Nf6");
		rep1.addChild(new StringRepertoire("c4"));
		rep1.addChild(new StringRepertoire("Nf3"));
		rep2.addChild(new StringRepertoire("c4"));
		rep2.addChild(new StringRepertoire("Nf3"));
		rep.addChild(rep1);
		rep.addChild(rep2);
		System.out.println(rep);
	}
}
