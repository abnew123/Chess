package main;

import frontend.screens.StartScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StartScreen ss = new StartScreen(primaryStage);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
