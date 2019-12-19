package backend;

import java.util.ArrayList;
import java.util.List;

import backend.piece.Bishop;
import backend.piece.King;
import backend.piece.Knight;
import backend.piece.Pawn;
import backend.piece.Piece;
import backend.piece.Queen;
import backend.piece.Rook;

/**
 * represents a single ply in a chess game
 * 
 * @author shichengrao
 *
 */
public class HalfTurn {
	private Piece piece;
	private Piece promotedPiece;
	private Square source;
	private Square destination;
	private Position prePosition;

	/**
	 * default constructor to fill all private instance variables
	 * 
	 * @param currentPiece
	 *            - piece that is moving
	 * @param promotedPiece
	 *            - if promotion occurs, piece that the pawn promotes to
	 * @param source
	 *            - square from which the piece started
	 * @param destination
	 *            - square where the piece ends up
	 * @param position
	 *            - position BEFORE the ply occurs
	 */
	public HalfTurn(Piece currentPiece, Piece promotedPiece, Square source, Square destination, Position position) {
		piece = currentPiece;
		this.promotedPiece = promotedPiece;
		this.source = source;
		this.destination = destination;
		prePosition = position;
	}

	/**
	 * constructor used for reading in a move from a Portable Game Notation (PGN)
	 * 
	 * @param PGN
	 * @param position
	 * @param color
	 */
	public HalfTurn(String PGN, Position position, boolean color) {
		prePosition = position;
		if (PGN.equals("O-O") || PGN.equals("O-O-O")) {
			piece = new King(color);
			promotedPiece = null;
			source = new Square(color ? "e1" : "e8");
			destination = new Square(color ? PGN.equals("O-O") ? "g1" : "c1" : PGN.equals("O-O") ? "g8" : "c8");
		} else {
			if (PGN.substring(0, 1).matches("[A-Z]")) {
				piece = determinePiece(PGN.substring(0, 1), color);
				promotedPiece = null;
				destination = new Square(clean(PGN));
			} else {
				piece = new Pawn(color);
				destination = new Square(clean(PGN));
				if (destination.getRank() == (color ? 8 : 1)) {
					promotedPiece = determinePiece(PGN.substring(PGN.indexOf("=") + 1, PGN.indexOf("=") + 2), color);
				} else {
					promotedPiece = null;
				}
			}
			List<Square> possibleSources = possibleSources();

			if (possibleSources.size() > 1) {
				source = disambiguate(PGN, possibleSources);
			} else {
				source = possibleSources.get(0);
			}
		}
	}

	/**
	 * returns the ply as it would appear in PGN notation
	 */
	@Override
	public String toString() {
		if (piece.algebraicName().equals("K") && castling()
				&& castledRookDestination()[0].distanceToOther(castledRookDestination()[1]) == 3) {
			return "O-O-O";
		}
		if (piece.algebraicName().equals("K") && castling()
				&& castledRookDestination()[0].distanceToOther(castledRookDestination()[1]) == 2) {
			return "O-O";
		}
		String pgnCode = "";
		pgnCode += piece.algebraicName();
		if (needDisambiguation()) {
			pgnCode += disambiguate();
		}
		if (capture()) {
			if (piece.algebraicName().equals("")) {
				pgnCode += (char) ('a' - 1 + source.getFile());
			}
			pgnCode += "x";
		}
		pgnCode += destination.toString();
		if ((piece.fenName().equals("P") && destination.getRank() == 8)
				|| (piece.fenName().equals("p") && destination.getRank() == 1)) {
			pgnCode += "=" + promotedPiece.algebraicName();
		}
		if (check()) {
			pgnCode += checkmate() ? "#" : "+";
		}
		return pgnCode;
	}

	private boolean needDisambiguation() {
		List<Square> possibleSources = possibleSources();
		return possibleSources.size() > 1;
	}

	private boolean capture() {
		return prePosition.pieceOnSquare(destination) || enpassant();
	}

	/**
	 * returns if the move is an enpassant
	 * 
	 * @return
	 */
	public boolean enpassant() {
		if (!piece.algebraicName().equals("") || !(source.distanceToOther(destination) == 2) || source.getRank() == 2
				|| source.getRank() == 7) {
			return false;
		}
		return !prePosition.pieceOnSquare(destination);
	}

	public boolean check() {
		Position copyOfGamePosition = new Position(prePosition);
		copyOfGamePosition.update(this);
		return copyOfGamePosition.kingInCheck(!piece.getColor());
	}
	//TODO make checkmate work
	private boolean checkmate() {
		Position prePositionCopy = new Position(prePosition);
		prePositionCopy.update(this);
		return check() && prePositionCopy.containsNoLegalMoves(!piece.getColor());
	}

	/**
	 * if a piece was captured, the square it was captured on is returned not always
	 * destination due to enpassant
	 * 
	 * @return
	 */
	public Square squarePieceWasCapturedOn() {
		if (capture()) {
			if (!enpassant()) {
				return destination;
			} else {
				return new Square(destination.getFile(), destination.getRank() + (piece.getColor() ? -1 : 1));
			}
		}
		return null;
	}

	/**
	 * returns the source and destination of the ply
	 * 
	 * @return
	 */
	public Square[] initialAndFinalLocation() {
		return new Square[] { source, destination };
	}

	/**
	 * returns if the given combination of instance variables can form a legal move
	 * TODO: make sure this works (mainly pawn issues)
	 * 
	 * @return
	 */
	public boolean playable() {
		return prePosition.getSquaresFromPiece(piece.algebraicName()).contains(source)
				&& (piece.possibleMoves(prePosition, source)).contains(destination);
	}

	/**
	 * returns the color of the piece being moved
	 * 
	 * @return
	 */
	public boolean pieceColor() {
		return piece.getColor();
	}

	/**
	 * returns the piece being moved
	 * 
	 * @return
	 */

	public Piece getPiece() {
		return piece;
	}

	private Piece determinePiece(String algebraicName, boolean color) {
		if (algebraicName.equals("N")) {
			return new Knight(color);
		}
		if (algebraicName.equals("K")) {
			return new King(color);
		}
		if (algebraicName.equals("Q")) {
			return new Queen(color);
		}
		if (algebraicName.equals("B")) {
			return new Bishop(color);
		}
		if (algebraicName.equals("R")) {
			return new Rook(color);
		} else {
			return null;
		}
	}

	private String clean(String fullPGN) {
		String partialPGN = new String(fullPGN);
		partialPGN = strip(partialPGN);
		if (partialPGN.length() > 3) {
			partialPGN = partialPGN.substring(1);
		}
		if (partialPGN.length() == 3) {
			partialPGN = partialPGN.substring(1);
		}
		return partialPGN;
	}

	/**
	 * returns if the move involves castling
	 * 
	 * @return
	 */
	public boolean castling() {
		if (piece.algebraicName().equals("K")) {
			if (source.getRank() == destination.getRank()) {
				return source.distanceToOther(destination) == 2 || source.distanceToOther(destination) == 3;
			}
		}
		return false;
	}

	/**
	 * returns the square that the rook moves if the move involves castling NOT
	 * destination, since castling is treated as a king move
	 * 
	 * @return
	 */
	public Square[] castledRookDestination() {
		if (castling()) {
			if (destination.equals(new Square("g1"))) {
				return new Square[] { new Square("h1"), new Square("f1") };
			}
			if (destination.equals(new Square("g8"))) {
				return new Square[] { new Square("h8"), new Square("f8") };
			}
			if (destination.equals(new Square("c1"))) {
				return new Square[] { new Square("a1"), new Square("d1") };
			}
			if (destination.equals(new Square("c8"))) {
				return new Square[] { new Square("a8"), new Square("d8") };
			}
		}
		return null;
	}

	/**
	 * returns if the move involves promotion
	 * 
	 * @return
	 */
	public boolean promotion() {
		return promotedPiece != null;
	}

	/**
	 * returns the promoted piece if it exists
	 * 
	 * @return
	 */
	public Piece promotedPiece() {
		return promotedPiece;
	}

	private String strip(String PGN) {
		String partialPGN = new String(PGN);
		if (partialPGN.contains("+") || partialPGN.contains("#")) {
			partialPGN = partialPGN.substring(0, partialPGN.length() - 1);
		}
		if (partialPGN.substring(partialPGN.length() - 1).matches("[A-Z]")) {
			partialPGN = partialPGN.substring(0, partialPGN.length() - 1);
		}
		if (partialPGN.substring(0, 1).matches("[A-Z]")) {
			partialPGN = partialPGN.substring(1);
		}
		if (partialPGN.contains("x")) {
			int location = partialPGN.indexOf("x");
			partialPGN = partialPGN.substring(0, location) + partialPGN.substring(location + 1);
		}
		if (partialPGN.contains("=")) {
			int location = partialPGN.indexOf("=");
			partialPGN = partialPGN.substring(0, location) + partialPGN.substring(location + 1);
		}
		return partialPGN;
	}

	private Square disambiguate(String PGN, List<Square> possibleSources) {
		String disambiguation = strip(PGN);
		disambiguation = disambiguation.substring(0, disambiguation.length() - 2);
		if (disambiguation.length() == 2) {
			System.out.println("mythical double case");
			return new Square(disambiguation);
		}
		if (disambiguation.matches("[a-h]")) {
			for (Square square : possibleSources) {
				if (square.toString().contains(disambiguation)) {
					return square;
				}
			}
			System.out.println("disambiguation failed");
			return null;
		}
		if (disambiguation.matches("/d")) {
			for (Square square : possibleSources) {
				if (square.toString().contains(disambiguation)) {
					return square;
				}
			}
			System.out.println("disambiguation failed");
			return null;
		}
		System.out.println("wtf" + " " + PGN);
		return null;
	}

	private List<Square> possibleSources() {
		List<Square> possibleSources = new ArrayList<>();
		for (Square square : prePosition.getSquaresFromPiece(piece.algebraicName())) {
			if (prePosition.getPieceOnSquare(square).getColor() == piece.getColor()
					&& piece.possibleMovesFull(prePosition, square).contains(destination)) {
				possibleSources.add(square);
			}
		}
		return possibleSources;
	}

	private String disambiguate() {
		List<Square> possibleSources = possibleSources();
		possibleSources.remove(source);
		if (checkFile(possibleSources)) {
			return "" + ((char) ('a' - 1 + source.getFile()));
		}
		if (checkRank(possibleSources)) {
			return "" + source.getRank();
		}
		return "" + ((char) 'a' - 1 + source.getFile()) + source.getRank();
	}

	private boolean checkFile(List<Square> possibleSources) {
		for (Square square : possibleSources) {
			if (square.getFile() == source.getFile()) {
				return false;
			}
		}
		return true;
	}

	private boolean checkRank(List<Square> possibleSources) {
		for (Square square : possibleSources) {
			if (square.getRank() == source.getRank()) {
				return false;
			}
		}
		return true;
	}

}
