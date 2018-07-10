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
		if(enpassant()) {
			pgnCode+= source.toString().substring(0,1);
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
		return prePosition.pieceOnSquare(destination)|| enpassant();
	}
	
	private boolean enpassant() {
		if(!piece.algebraicName().equals("")||!(source.distanceToOther(destination) == 2) || source.getRank() == 2 || source.getRank() == 7) {
			return false;
		}
		return !prePosition.pieceOnSquare(destination);
	}
	private boolean check() {
		Square opposingKingSquare = prePosition.getSquaresFromPiece(prePosition.getKingPiece(!piece.getColor())).get(0);
		for(Square square: prePosition.getPieces().keySet()) {
			if(prePosition.getPieces().get(square).possibleMoves(prePosition, square).contains(opposingKingSquare)) {
				return true;
			}
		}
		return false;
	}
	private boolean checkmate() {
		Position prePositionCopy = new Position(prePosition);
		prePositionCopy.update(this);
		return check() && prePositionCopy.containsNoLegalMoves(!piece.getColor());
	}
	/**
	 * if a piece was captured, the square it was captured on is returned
	 * @return
	 */
	public Square squarePieceWasCapturedOn() {
		if(capture()) {
			if(!enpassant()) {
				return destination;
			}
			else {
				if(piece.getColor()) {
					return new Square(destination.getFile(), destination.getRank() - 1);
				}
				else {
					return new Square(destination.getFile(), destination.getRank() + 1);
				}
			}
		}
		return null;
	}
	
	public Square[] initialAndFinalLocation() {
		return new Square[] {source,destination};
	}
	
	public boolean playable() {
		return prePosition.getSquaresFromPiece(piece).contains(source) && (piece.possibleMoves(prePosition, source)).contains(destination);
	}
}
