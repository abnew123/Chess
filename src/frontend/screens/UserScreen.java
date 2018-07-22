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
		FinishedGame test = new FinishedGame("[Event \"Bled-Zagreb-Belgrade Candidates\"]\n" + 
				"[Site \"Bled, Zagreb & Belgrade YUG\"]\n" + 
				"[Date \"1959.10.11\"]\n" + 
				"[Round \"20\"]\n" + 
				"[White \"Tal, Mikhail\"]\n" + 
				"[Black \"Fischer, Robert James\"]\n" + 
				"[Result \"1-0\"]\n" + 
				"\n" + 
				"1. d4 Nf6 2. c4 g6 3. Nc3 Bg7 4. e4 d6 5. Be2 O-O 6. Nf3 e5\n" + 
				"7. d5 Nbd7 8. Bg5 h6 9. Bh4 a6 10. O-O Qe8 11. Nd2 Nh7 12. b4 Bf6\n" + 
				"13. Bxf6 Nhxf6 14. Nb3 Qe7 15. Qd2 Kh7 16. Qe3 Ng8 17. c5 f5\n" + 
				"18. exf5 gxf5 19. f4 exf4 20. Qxf4 dxc5 21. Bd3 cxb4 22. Rae1 Qf6\n" + 
				"23. Re6 Qxc3 24. Bxf5+ Rxf5 25. Qxf5+ Kh8 26. Rf3 Qb2 27. Re8 Nf6\n" + 
				"28. Qxf6+ Qxf6 29. Rxf6 Kg7 30. Rff8 Ne7 31. Na5 h5 32. h4 Rb8\n" + 
				"33. Nc4 b5 34. Ne5 1-0");
		new ReplayScreen(myStage, user, test);
	}
}
