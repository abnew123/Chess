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
		
		FinishedGame test = new FinishedGame("[Event \"Bled-Zagreb-Belgrade Candidates\"]\n" + 
				"[Site \"Bled, Zagreb & Belgrade YUG\"]\n" + 
				"[Date \"1959.10.11\"]\n" + 
				"[Round \"20\"]\n" + 
				"[White \"Tal, Mikhail\"]\n" + 
				"[Black \"Fischer, Robert James\"]\n" + 
				"[Result \"1-0\"]\n" + 
				"\n" + 
				"1. d4 Nf6 2. c4 g6 3. Nc3 Bg7 4. e4 d6 5. Be2 O-O 6. Nf3 e5\n" + 
				"7. d5 Nbd7 8. Bg5 h6 9. Bh4 a6 10. O-O Qe8 11. Nd2 Nh7 12. b4 Bf6\n" + 
				"13. Bxf6 Nhxf6 14. Nb3 Qe7 15. Qd2 Kh7 16. Qe3 Ng8 17. c5 f5\n" + 
				"18. exf5 gxf5 19. f4 exf4 20. Qxf4 dxc5 21. Bd3 cxb4 22. Rae1 Qf6\n" + 
				"23. Re6 Qxc3 24. Bxf5+ Rxf5 25. Qxf5+ Kh8 26. Rf3 Qb2 27. Re8 Nf6\n" + 
				"28. Qxf6+ Qxf6 29. Rxf6 Kg7 30. Rff8 Ne7 31. Na5 h5 32. h4 Rb8\n" + 
				"33. Nc4 b5 34. Ne5 1-0");
	}
}
