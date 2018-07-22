package frontend.displays;

import backend.user.User;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class StatsDisplay extends Group {
	private Text mainText;
	public StatsDisplay(User user) {
		mainText = new Text(user.getStats());
		mainText.setId("default_authoring_label");
		getChildren().add(mainText);
	}
	
}
