import backend.game.FinishedGame;

public class TestMain {
	public static void main(String[] args) {
//		Position position = new Position();
//		System.out.println(position.toVisibleBoard());
		
		
//		UnfinishedGame game = new UnfinishedGame();
//		HalfTurn t1 = new HalfTurn(new Pawn(true), null, new Square("d2"), new Square("d4"), new Position(game.getPosition()));
//		game.addPly(t1);
//		HalfTurn t2 = new HalfTurn(new Pawn(false), null, new Square("d7"), new Square("d5"), new Position(game.getPosition()));
//		game.addPly(t2);
//		HalfTurn t3 = new HalfTurn(new Bishop(true), null, new Square("g1"), new Square("f3"), new Position(game.getPosition()));
//		game.addPly(t3);
//		System.out.println(game.getPGN());
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
		
//		String name = "Samson";
//		UserManager manager = new UserManager();
//		manager.createNewUser(name);
//		Writer.write(Resources.getString("USERS_LIST"), manager);
		
		FinishedGame test = new FinishedGame("[Event \"Random chess.com Game\"]\n" + 
				"[Date \"2011\"]\n" + 
				"[White \"panandh\"]\n" + 
				"[Black \"amirpb007\"]\n" + 
				"\n" + 
				"1. e4 d5 2. exd5 Nf6 3. Bb5+ c6 4. dxc6 Qb6 5. cxb7+ Qxb5 6. bxc8=Q#");
	}
}
