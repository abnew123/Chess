package frontend.screens;

import java.util.stream.Collectors;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import backend.data.Reader;
import backend.data.Writer;
import backend.user.UserManager;
import frontend.buttons.ButtonFactory;
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

public class StartScreen implements Screen {
	private Stage myStage;
	private StackPane myPane;
	private Scene myScene; 
	public static final String TITLE = "Chess Beta";
	
	public StartScreen(Stage stage) {
		myStage = stage;
		setupScreen();
		setupContent();
		setupStage();
	}
	
	private void setupScreen() {
		myPane = new StackPane();
		myPane.setBackground(new Background(new BackgroundFill(DEFAULT_BACKGROUND, null, null)));
		myPane.setId("start_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
	}
	
	private void setupContent() {
		XStream xstream = new XStream(new DomDriver());
		VBox box = new VBox();
		UserManager manager = (UserManager) Reader.read(Resources.getString("USERS_LIST")).get(0);
		//TODO delete this later
//		if(manager == null) {
//			manager = new UserManager();
//		}
		box.getChildren().addAll(
				new Text(TITLE) {{ setId("main_title"); }},
				new TextField(),
				ButtonFactory.makeButton("Add New User", e -> addNewUser(manager, box), "image_button")
				);
		box.getChildren().addAll(manager.makeButtons(myStage));
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0, 0, 0, 30));
		box.setSpacing(SPACING_SMALL);
		myPane.getChildren().add(box);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle("Start Screen");
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.setResizable(false);
		myStage.show();
	}
	
	private void addNewUser(UserManager manager, VBox box) {
		//change this to get actual name
		String name = box.getChildren().stream().filter(a -> a.getClass().equals((new TextField()).getClass())).map(a -> ((TextField) a).getText()).collect(Collectors.toList()).get(0);
		manager.createNewUser(name);
		Writer.write(Resources.getString("USERS_LIST"), manager);
		new StartScreen(myStage);
	}
	
}
