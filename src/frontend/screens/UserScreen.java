package frontend.screens;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import backend.HalfTurn;
import backend.Position;
import backend.Square;
import backend.game.FinishedGame;
import backend.piece.Knight;
import backend.user.User;
import frontend.buttons.ButtonFactory;
import frontend.displays.StatsDisplay;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserScreen implements Screen {
	private StatsDisplay statsDisplay;
	private Stage myStage;
	private StackPane myPane;
	private Scene myScene; 
	public UserScreen(Stage stage, User user) {
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
		XStream xstream = new XStream(new DomDriver());
		VBox box = new VBox();
		box.getChildren().addAll(
				new Text("Welcome " + user.getName()) {{ setId("default_authoring_title"); }},
				new StatsDisplay(user),
				ButtonFactory.makeButton("New Game", e-> new GameStartScreen(myStage, user), "image_button"),
				ButtonFactory.makeButton("Load Game", e -> loadGame(user), "image_button"),
				ButtonFactory.makeButton("Go To Settings", e -> new SettingsScreen(myStage, user), "image_button")
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
	
	private void loadGame(User user) {
		FinishedGame test = new FinishedGame("[Event \"?\"]\n" + 
				"[Site \"?\"]\n" + 
				"[Date \"1904.??.??\"]\n" + 
				"[Round \"?\"]\n" + 
				"[White \"Giese\"]\n" + 
				"[Black \"Alekhine, Alexander A\"]\n" + 
				"[Result \"0-1\"]\n" + 
				"[ECO \"C33\"]\n" + 
				"\n" + 
				"1.e4 e5 2.f4 exf4 3.Bc4 d5 4.Bxd5 Qh4+ 5.Kf1 g5 6.Nc3 Ne7 7.d4 Bg7 8.Nf3 \n" + 
				"Qh5 9.h4 h6 10.e5 Nbc6 11.Kg1 g4 12.Ne1 Bf5 13.Bxc6+ Nxc6 14.Ne2 Be4 15.\n" + 
				"Bxf4 Qf5 16.Qd2 O-O-O 17.Ng3 Qh7 18.Qe2 Nxd4 19.Qc4 Bc6 20.c3 Ne6 21.Qf1 \n" + 
				"h5 22.Bg5 Bxe5 23.Bxd8 Bxg3 24.Bf6 Qe4 25.Nd3 Nf4 26.Rh3 Qe3+ 27.Nf2 Nxh3+\n" + 
				"28.gxh3 Bh2+ 29.Kxh2 Qf4+ 30.Kg1 Qg3+ 31.Qg2 Qxg2# 0-1");
		new ReplayScreen(myStage, user, test);
	}
}
