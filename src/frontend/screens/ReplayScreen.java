package frontend.screens;

import backend.game.FinishedGame;
import frontend.displays.CapturedPiecesDisplay;
import frontend.displays.UserDisplay;
import frontend.views.MoveListView;
import frontend.views.ReplayView;

public class ReplayScreen implements Screen {
	private CapturedPiecesDisplay piecesCapturedByWhite;
	private CapturedPiecesDisplay piecesCapturedByBlack;
	private ReplayView gameBoard;
	private MoveListView moveList;
	private UserDisplay whiteDisplay;
	private UserDisplay blackDisplay;
	private FinishedGame game;
}
