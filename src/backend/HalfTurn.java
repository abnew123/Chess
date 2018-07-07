package backend;

import backend.piece.Piece;

public class HalfTurn {
	private Piece piece;
	private Square source;
	private Square destination;
	private Position prePosition;
	@Override
	public String toString() {
		//handle exceptional cases first (e.g. castling)
		if(piece.algebraicName().equals("K") && source.distanceToOther(destination) == 3){
			return "0-0-0";
		}
		if(piece.algebraicName().equals("K") && source.distanceToOther(destination) == 2){
			return "0-0";
		}
		//TODO solve other special cases (promotion?)
		
		String pgnCode = "";
		pgnCode+= piece.algebraicName();
		if(!needDisambiguation()){
			if(capture()) {
				pgnCode+="x";
			}
			pgnCode+=destination.toString();
		}
		return pgnCode;
	}
	
	private boolean needDisambiguation() {
		return false;
	}
	private boolean capture() {
		return false;
	}
}
