package backend;

import backend.piece.Piece;

public class Move {
	private Piece piece;
	private Square source;
	private Square destination;
	private boolean capture;
	private boolean promotion;
}
