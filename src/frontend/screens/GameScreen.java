package frontend.screens;

import backend.game.UnfinishedGame;
import frontend.buttons.GotoSettingsButton;
import frontend.displays.CapturedPiecesDisplay;
import frontend.displays.UserDisplay;
import frontend.views.MoveListView;
import frontend.views.PlayGameBoardView;

public class GameScreen implements Screen {
	private CapturedPiecesDisplay piecesCapturedByWhite;
	private CapturedPiecesDisplay piecesCapturedByBlack;
	private PlayGameBoardView gameBoard;
	private MoveListView moveList;
	private GotoSettingsButton settingsButton;
	private UserDisplay whiteDisplay;
	private UserDisplay blackDisplay;
	private UnfinishedGame game;
}
