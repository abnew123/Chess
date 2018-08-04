package frontend.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
/**
 * fast way to create buttons
 * @author shichengrao
 * code originates for voogasalad project from a CS308 project I was part of 
 */
public class ButtonFactory {
	public static Button makeButton(String text) {
		Button b = new Button();
		b.setText(text);
		return b;
	}
	public static Button makeButton(String text, EventHandler<ActionEvent> handler) {
		Button b = makeButton(text);
		b.setOnAction(handler);
		return b;
	}
	public static Button makeButton(String text, EventHandler<ActionEvent> handler, String styleclass) {
		Button b = makeButton(text, handler);
		b.getStyleClass().add(styleclass);
		return b;
	}
}
