package backend;

import java.util.Map;
import java.util.TreeMap;

import backend.piece.Bishop;
import backend.piece.King;
import backend.piece.Knight;
import backend.piece.Pawn;
import backend.piece.Piece;
import backend.piece.Queen;
import backend.piece.Rook;

public class Position {
	private Map<Square, Piece> pieces;
	public Position() {
		pieces = new TreeMap<>();
		addPieces();
	}
	public String toFEN() {
		String result = "";
		for(int i = 8; i > 0; i--) {
			result += constructRow(i);
		}
		
		return result;
	}
	
	private void addPieces() {
		pieces.put(new Square("a1"), new Rook(true));
		pieces.put(new Square("b1"), new Knight(true));
		pieces.put(new Square("c1"), new Bishop(true));
		pieces.put(new Square("d1"), new Queen(true));
		pieces.put(new Square("e1"), new King(true));
		pieces.put(new Square("f1"), new Bishop(true));
		pieces.put(new Square("g1"), new Knight(true));
		pieces.put(new Square("h1"), new Rook(true));
		
		pieces.put(new Square("a2"), new Pawn(true));
		pieces.put(new Square("b2"), new Pawn(true));
		pieces.put(new Square("c2"), new Pawn(true));
		pieces.put(new Square("d2"), new Pawn(true));
		pieces.put(new Square("e2"), new Pawn(true));
		pieces.put(new Square("f2"), new Pawn(true));
		pieces.put(new Square("g2"), new Pawn(true));
		pieces.put(new Square("h2"), new Pawn(true));
		
		pieces.put(new Square("a7"), new Pawn(false));
		pieces.put(new Square("b7"), new Pawn(false));
		pieces.put(new Square("c7"), new Pawn(false));
		pieces.put(new Square("d7"), new Pawn(false));
		pieces.put(new Square("e7"), new Pawn(false));
		pieces.put(new Square("f7"), new Pawn(false));
		pieces.put(new Square("g7"), new Pawn(false));
		pieces.put(new Square("h7"), new Pawn(false));
		
		pieces.put(new Square("a8"), new Rook(false));
		pieces.put(new Square("b8"), new Knight(false));
		pieces.put(new Square("c8"), new Bishop(false));
		pieces.put(new Square("d8"), new Queen(false));
		pieces.put(new Square("e8"), new King(false));
		pieces.put(new Square("f8"), new Bishop(false));
		pieces.put(new Square("g8"), new Knight(false));
		pieces.put(new Square("h8"), new Rook(false));
		
	}
	
	private String constructRow(int row) {
		String result = "";
		int emptycounter = 0;
		for(int i = 1; i < 9; i++) {
			if(pieces.keySet().contains(new Square(i,row))) {
				if(emptycounter != 0) {
					result += emptycounter;
					emptycounter = 0;
				}
				result+= pieces.get(new Square(i,row)).fenName();
			}
			else {
				emptycounter++;
			}
		}
		if(emptycounter!=0) {
			result += emptycounter;
		}
		if(row != 1) {
			result+= "/";
		}
		return result;
	}
	
	public boolean pieceOnSquare(Square square) {
		return pieces.keySet().contains(square);
	}
}
