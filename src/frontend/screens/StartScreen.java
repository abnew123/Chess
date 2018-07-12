package frontend.screens;

import frontend.buttons.NewUserButton;
import frontend.menus.UserSelectMenu;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartScreen implements Screen {
	private NewUserButton newUserButton;
	private UserSelectMenu userSelectMenu;
	private Stage myStage;
	private StackPane myPane;
	private Scene myScene; 
	
	public StartScreen(Stage stage) {
		myStage = stage;
	}
}
