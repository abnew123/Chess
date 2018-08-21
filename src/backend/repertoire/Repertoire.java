package backend.repertoire;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import backend.user.User;
import frontend.buttons.ButtonFactory;
import frontend.screens.RepertoireScreen;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Repertoire {
	private List<Repertoire> children;
	private String move;
	
	public Repertoire(String move) {
		children = new ArrayList<>();
		this.move = move;
	}
	public String toString() {
		String result = move;
		for(Repertoire child: children) {
			if(!child.equals(children.get(0))) {
				result += shift(child.toString())+ "\n";
			}
			else {
				for(int i = 0; i < (4-move.length());i++) {
					result+= " ";
				}
				result += shift(child.toString()).substring(4) + "\n";
			}
		}
		return result;
	}
	
	private String shift(String string) {
		String result = "";
		String[] byRow = string.split("\\n");
		for(String row: byRow) {
			result+="    " + row + "\n";
		}
		return result.substring(0, result.length() - 1);
	}

	public void addChild(Repertoire repertoire) {
		children.add(repertoire);
	}
	public boolean isEmpty() {
		return children.size() == 0;
	}
	public VBox nextMoveOptions(Stage stage, User user, List<String> moves) {
		VBox box = new VBox();
		for(Repertoire nextMove: children) {
			String text = nextMove.toString().split(" ")[0];
			List<String> updatedMoves = moves;
			updatedMoves.add(text);
			box.getChildren().add(ButtonFactory.makeButton(text, e-> new RepertoireScreen(stage, user, nextMove, updatedMoves), "image_button"));
		}
		TextField newRepertoireEntry = new TextField();
		newRepertoireEntry.setMaxWidth(500);
		box.getChildren().add(newRepertoireEntry);
		box.getChildren().add(ButtonFactory.makeButton("Add new move",e -> addNewRepertoire(box, stage, user, moves),"image_button"));
		box.setAlignment(Pos.CENTER);
		return box;
	}
	private void addNewRepertoire(VBox box, Stage stage, User user, List<String> moves) {
		String name = box.getChildren().stream().filter(a -> a.getClass().equals((new TextField()).getClass())).map(a -> ((TextField) a).getText()).collect(Collectors.toList()).get(0);
		addChild(new Repertoire(name));
		new RepertoireScreen(stage, user, this, moves);
	}
}
