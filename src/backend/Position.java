package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import backend.piece.Bishop;
import backend.piece.King;
import backend.piece.Knight;
import backend.piece.Pawn;
import backend.piece.Piece;
import backend.piece.Queen;
import backend.piece.Rook;
/**
 * models the game state of a static position in chess
 * @author shichengrao
 *
 */
public class Position {
	private Map<Square, Piece> pieces;
	/**
	 * initializes a standard initial chess position
	 */
	public Position() {
		pieces = new TreeMap<>();
		addPieces();
	}
	/**
	 * makes a copy of a position
	 * two DIFFERENT OBJECTS, same information
	 * @param position
	 */
	public Position(Position position) {
		pieces = new TreeMap<>();
		for(Square square: position.getPieces().keySet()) {
			pieces.put(square, position.getPieces().get(square));
		}
	}
	/**
	 * returns the part of a position's FEN that can be determined without looking back in time.
	 * does not include whether castling is allowed or indicator for 50 move draw
	 * @return
	 */
	public String toSimplifiedFEN() {
		String result = "";
		for(int i = 8; i > 0; i--) {
			result += constructRowForFEN(i);
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
	
	private String constructRowForFEN(int row) {
		String result = "";
		int emptycounter = 0;
		for(int i = 1; i < 9; i++) {
			if(pieces.keySet().contains(new Square(i,row))) {
				if(emptycounter != 0) {
					result += emptycounter;
					emptycounter = 0;
				}
				if(pieces.get(new Square(i,row)) == null) {
					result+="*";
				}
				else {
					result+= pieces.get(new Square(i,row)).fenName();
				}
				
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
	/**
	 * checks if a piece exists on given square
	 * @param square
	 * @return
	 */
	public boolean pieceOnSquare(Square square) {
		return pieces.keySet().contains(square);
	}
	/**
	 * updates the position with a given ply
	 * @param ply
	 */
	public void update(HalfTurn ply) {
		if(ply.castling()) {
			pieces.put(ply.initialAndFinalLocation()[1], pieces.remove(ply.initialAndFinalLocation()[0]));
			pieces.put(ply.castledRookDestination()[1], pieces.remove(ply.castledRookDestination()[0]));
			return;
		}
		if(ply.promotion()) {
			pieces.remove(ply.initialAndFinalLocation()[0]);
			pieces.put(ply.initialAndFinalLocation()[1], ply.promotedPiece());
		}
		else {
			if(ply.squarePieceWasCapturedOn() != null) {
				pieces.remove(ply.squarePieceWasCapturedOn());
			}
			
			pieces.put(ply.initialAndFinalLocation()[1], pieces.remove(ply.initialAndFinalLocation()[0]));
		}
		
	}
	/**
	 * returns the map of each occupied square to the piece on it
	 * TODO: is it possible to remove this for better encapsulation?
	 * @return
	 */
	public Map<Square, Piece> getPieces(){
		return pieces;
	}
	/**
	 * returns the king for given side
	 * @param color - the color of the king
	 * @return
	 */
	public Piece getKingPiece(boolean color) {
		for(Piece piece:pieces.values()) {
			if(piece.algebraicName().equals("K")&& piece.getColor() == color) {
				return piece;
			}
		}
		//should never be reached
		return null;
		
	}
	/**
	 * given a piece, returns all squares the piece is found on
	 * @param algebraicName - formal PGN name of the piece (e.g. K for king)
	 * @return
	 */
	public List<Square> getSquaresFromPiece(String algebraicName) {
		List<Square> squares = new ArrayList<>();
		for(Square square: pieces.keySet()) {
			if(pieces.get(square).algebraicName().equals(algebraicName)) {
				squares.add(square);
			}
		}
		return squares;
	}
	
	/**
	 * checks that one side (specified by color) has not legal moves left
	 * @param color
	 * @return
	 */
	public boolean containsNoLegalMoves(boolean color) {
		for(Square square: pieces.keySet()) {
			if((pieces.get(square).getColor() != color) || pieces.get(square).possibleMoves(this, square).size() != 0) {
				return false;
			}
		}
		return true;
	}
	/**
	 * returns piece on a given square
	 * @param square
	 * @return
	 */
	public Piece getPieceOnSquare(Square square) {
		return pieces.get(square);
	}
	/**
	 * returns if the king is in check in the current position
	 * @param color - color of the king
	 * @return
	 */
	public boolean kingInCheck(boolean color) {
		List<Square> kingSquares = getSquaresFromPiece(getKingPiece(color).algebraicName());
		Square opposingKingSquare = null;
		for(Square square: kingSquares) {
			if(getPieceOnSquare(square).getColor() == color) {
				opposingKingSquare = square;
			}
		}
		for(Square square: getPieces().keySet()) {
			if(getPieces().get(square).getColor() != color&& getPieces().get(square).possibleMoves(this, square).contains(opposingKingSquare)) {
				return true;
			}
			//TODO set up pawn check situation (can't use possibleMoves since its legal for king to be in front of pawn
			
		}
		return false;
	}
}
