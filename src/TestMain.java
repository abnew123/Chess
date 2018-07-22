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
		
		FinishedGame test = new FinishedGame("[Event \"?\"]\n" + 
				"[Site \"?\"]\n" + 
				"[Date \"1904.??.??\"]\n" + 
				"[Round \"?\"]\n" + 
				"[White \"Giese\"]\n" + 
				"[Black \"Alekhine, Alexander A\"]\n" + 
				"[Result \"0-1\"]\n" + 
				"[ECO \"C33\"]\n" + 
				"\n" + 
				"1.e4 e5 2.f4 exf4 3.Bc4 d5 4.Bxd5 Qh4+ 5.Kf1 g5 6.Nc3 Ne7 7.d4 Bg7 8.Nf3 \n" + 
				"Qh5 9.h4 h6 10.e5 Nbc6 11.Kg1 g4 12.Ne1 Bf5 13.Bxc6+ Nxc6 14.Ne2 Be4 15.\n" + 
				"Bxf4 Qf5 16.Qd2 O-O-O 17.Ng3 Qh7 18.Qe2 Nxd4 19.Qc4 Bc6 20.c3 Ne6 21.Qf1 \n" + 
				"h5 22.Bg5 Bxe5 23.Bxd8 Bxg3 24.Bf6 Qe4 25.Nd3 Nf4 26.Rh3 Qe3+ 27.Nf2 Nxh3+\n" + 
				"28.gxh3 Bh2+ 29.Kxh2 Qf4+ 30.Kg1 Qg3+ 31.Qg2 Qxg2# 0-1");
	}
}
