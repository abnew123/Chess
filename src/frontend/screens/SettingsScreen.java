package frontend.screens;

import backend.data.Reader;
import backend.data.Writer;
import backend.user.User;
import backend.user.UserManager;
import frontend.buttons.ButtonFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.Resources;

public class SettingsScreen implements Screen {
	private Stage myStage;
	private Pane myPane;
	private Scene myScene;
	public SettingsScreen(Stage stage, User user) {
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
		myPane.getChildren().clear();
		VBox box = new VBox();
		HBox topRow = new HBox();
		topRow.getChildren().addAll(
				ButtonFactory.makeButton("Back To User Screen", e -> new UserScreen(myStage, user), "image_button"),
				ButtonFactory.makeButton("Back To Game Screen", e -> new GameScreen(myStage, user), "image_button")
				);
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0, 0, 0, 30));
		box.setSpacing(SPACING_SMALL);
		HBox cosmeticSelector = user.makeCosmeticSelector();
		for(Node node: cosmeticSelector.getChildren()) {
			node.setOnMouseClicked(e -> updateSettings(user));
		}
		HBox functionalitySelector = user.makeNonCosmeticSelector();
		for(Node node: functionalitySelector.getChildren()) {
			node.setOnMouseClicked(e -> updateSettings(user));
		}
		box.getChildren().addAll(
				topRow,
				cosmeticSelector,
				functionalitySelector
				);
		myPane.getChildren().add(box);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle("Settings Screen");
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.setResizable(false);
		myStage.show();
	}
	
	private void updateSettings(User user) {
		UserManager manager = (UserManager) Reader.read(Resources.getString("USERS_LIST")).get(0);
		manager.updateUser(user);
		Writer.write(Resources.getString("USERS_LIST"), manager);
		setupContent(user);
	}
}
