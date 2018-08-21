package frontend.screens;

import java.util.List;

import backend.game.FinishedGame;
import backend.repertoire.Repertoire;
import backend.user.User;
import frontend.buttons.ButtonFactory;
import frontend.displays.CapturedPiecesDisplay;
import frontend.displays.UserDisplay;
import frontend.views.MoveListView;
import frontend.views.ReplayView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RepertoireScreen implements Screen {
	private CapturedPiecesDisplay piecesCapturedByWhite;
	private CapturedPiecesDisplay piecesCapturedByBlack;
	private ReplayView gameBoard;
	private MoveListView moveList;
	private UserDisplay whiteDisplay;
	private UserDisplay blackDisplay;
	private Stage myStage;
	private Pane myPane;
	private Scene myScene;
	private String PGN;
	private Repertoire myRepertoire;
	public RepertoireScreen(Stage stage, User user, Repertoire currentrep, List<String> moves) {
		myStage = stage;
		myRepertoire = currentrep;
		getPGN(moves);
		FinishedGame game = new FinishedGame(PGN);
		gameBoard = new ReplayView(game, user);
		moveList = new MoveListView(game.getPGN(), 0);
		piecesCapturedByWhite = new CapturedPiecesDisplay(true, game);
		piecesCapturedByBlack = new CapturedPiecesDisplay(false, game);
		gameBoard.goToEnd(moveList, piecesCapturedByWhite, piecesCapturedByBlack);
		setupScreen();
		setupContent(user, moves);
		setupStage();
	}
	
	private void setupScreen() {
		myPane = new StackPane();
		myPane.setBackground(new Background(new BackgroundFill(DEFAULT_BACKGROUND, null, null)));
		myPane.setId("make_game_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
	}
	//TODO add leftmost vbox thats a bunch of buttons to each possible repertoire
	//TODO rename boxes to something non-retarded
	private void setupContent(User user, List<String> moves) {
		HBox box = new HBox();
		VBox box2 = new VBox();
		HBox box3 = new HBox();
		VBox box4 = new VBox();
		VBox box5 = myRepertoire.nextMoveOptions(myStage, user, moves);
		box3.getChildren().addAll(
				ButtonFactory.makeButton("Backward One", e -> backwards(), "image_button"),
				ButtonFactory.makeButton("Forward One", e -> forwards(), "image_button")
				);
		box2.getChildren().addAll(
				moveList,
				box3
				);
		box2.setAlignment(Pos.CENTER);
		box4.getChildren().addAll(
				piecesCapturedByWhite,
				gameBoard,
				piecesCapturedByBlack
				);
		box4.setAlignment(Pos.CENTER);
		box.getChildren().addAll(
				box4,
				box2,
				box5
				);
		myPane.getChildren().add(box);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle("Repertoire Screen");
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.setResizable(false);
		myStage.show();
	}
	
	private void backwards() {
		if(gameBoard.moveBackward()){
			moveList.decrementMove();
			piecesCapturedByWhite.displayPrevious();
			piecesCapturedByBlack.displayPrevious();
		}
	}
	
	private void forwards() {
		if(gameBoard.moveForward()) {
			moveList.incrementMove();
			piecesCapturedByWhite.displayNext();
			piecesCapturedByBlack.displayNext();
		}
	}
	private void getPGN(List<String> moves) {
		PGN = "";
		for(int i = 0; i < moves.size(); i++) {
			if(i%2 == 0) {
				if(i!=0) {
					PGN +="\n";
				}
				PGN += 1+ (i/2);
				PGN+= ". ";
			}
			else {
				PGN += " ";
			}
			PGN+= moves.get(i);
		}
	}
}
