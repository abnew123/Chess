package frontend.screens;

import backend.game.FinishedGame;
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

public class ReplayScreen implements Screen {
	
	private CapturedPiecesDisplay piecesCapturedByWhite;
	private CapturedPiecesDisplay piecesCapturedByBlack;
	private ReplayView gameBoard;
	private MoveListView moveList;
	private UserDisplay whiteDisplay;
	private UserDisplay blackDisplay;
	private Stage myStage;
	private Pane myPane;
	private Scene myScene;
	public ReplayScreen(Stage stage, User user, FinishedGame game) {
		myStage = stage;
		gameBoard = new ReplayView(game, user);
		setupScreen();
		setupContent(user);
		setupStage();
	}
	
	private void setupScreen() {
		myPane = new StackPane();
		myPane.setBackground(new Background(new BackgroundFill(DEFAULT_BACKGROUND, null, null)));
		myPane.setId("make_game_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
	}
	
	private void setupContent(User user) {
		VBox box = new VBox();
		HBox box2 = new HBox();
		box2.getChildren().addAll(
				ButtonFactory.makeButton("Backward One", e -> gameBoard.moveBackward(), "image_button"),
				ButtonFactory.makeButton("Forward One", e -> gameBoard.moveForward(), "image_button")
				);
		box.getChildren().addAll(
				gameBoard,
				box2
				);
		myPane.getChildren().add(box);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle("Replay Screen");
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.setResizable(false);
		myStage.show();
	}
	
}
