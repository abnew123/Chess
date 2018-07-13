package frontend.screens;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import backend.data.Reader;
import backend.user.UserManager;
import frontend.buttons.ButtonFactory;
import frontend.buttons.GotoSettingsButton;
import frontend.buttons.LoadGameButton;
import frontend.buttons.StartGameButton;
import frontend.displays.StatsDisplay;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Resources;

public class UserScreen implements Screen {
	private StatsDisplay statsDisplay;
	private StartGameButton startGameButton;
	private LoadGameButton loadGameButton;
	private GotoSettingsButton gotoSettingsButton;
	private Stage myStage;
	private StackPane myPane;
	private Scene myScene; 
	public UserScreen(Stage stage, String name) {
		myStage = stage;
		setupScreen();
		setupContent(name);
		setupStage();
	}
	
	private void setupScreen() {
		myPane = new StackPane();
		myPane.setBackground(new Background(new BackgroundFill(DEFAULT_BACKGROUND, null, null)));
		myPane.setId("start_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
	}
	
	private void setupContent(String name) {
		XStream xstream = new XStream(new DomDriver());
		VBox box = new VBox();
		//TODO delete this later
//		if(manager == null) {
//			manager = new UserManager();
//		}
		box.getChildren().addAll(
				new Text("Welcome " + name) {{ setId("main_title"); }}
				);
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0, 0, 0, 30));
		box.setSpacing(SPACING_SMALL);
		myPane.getChildren().add(box);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle("User Screen");
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.setResizable(false);
		myStage.show();
	}
}
