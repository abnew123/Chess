package frontend.screens;

import backend.user.User;
import frontend.buttons.ButtonFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * screen to choose various factors of a game prior to starting the game
 * @author shichengrao
 *
 */
public class GameStartScreen implements Screen {
	private Stage myStage;
	private Pane myPane;
	private Scene myScene;
	public GameStartScreen(Stage stage, User user) {
		myStage = stage;
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
		box.getChildren().addAll(
				ButtonFactory.makeButton("delete this later", e -> new GameScreen(myStage, user),  "image_button")
				);
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0, 0, 0, 30));
		box.setSpacing(SPACING_SMALL);
		myPane.getChildren().add(box);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle("Game Start Screen");
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.setResizable(false);
		myStage.show();
	}
}
