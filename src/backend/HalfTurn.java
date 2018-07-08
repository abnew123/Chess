package backend;

import backend.piece.Piece;

public class HalfTurn {
	private Piece piece;
	private Piece promotedPiece;
	private Square source;
	private Square destination;
	private Position prePosition;
	
	public HalfTurn(Piece currentPiece, Piece promotedPiece, Square source, Square destination, Position position) {
		piece = currentPiece;
		this.promotedPiece = promotedPiece;
		this.source = source;
		this.destination = destination;
		prePosition = position;
	}
	@Override
	public String toString() {
		if(piece.algebraicName().equals("K") && source.distanceToOther(destination) == 3){
			return "O-O-O";
		}
		if(piece.algebraicName().equals("K") && source.distanceToOther(destination) == 2){
			return "O-O";
		}
		String pgnCode = "";
		pgnCode+= piece.algebraicName();
		if(needDisambiguation()){
			//TODO figure out how to disambiguate
		}
		if(capture()) {
			pgnCode+="x";
		}
		pgnCode+=destination.toString();
		if((piece.fenName().equals("P") && destination.getRank() == 8)||(piece.fenName().equals("p") && destination.getRank() == 1)) {
			pgnCode+="=" + promotedPiece.algebraicName();
		}
		if(check()) {
			pgnCode+=checkmate()?"#":"+";
		}
		return pgnCode;
	}
	
	private boolean needDisambiguation() {
		return false;
	}
	private boolean capture() {
		//TODO figure out en passant
		return prePosition.pieceOnSquare(destination);
	}
	private boolean check() {
		//TODO figure out if any piece of same color is attacking opposing king.
		return false;
	}
	private boolean checkmate() {
		//TODO figure out if opposing king has no available moves
		return false;
	}
}
