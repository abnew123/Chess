package backend;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public final class AlertMaker {
	
	public static void  makeAlert(String head, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(head);
		alert.setContentText(content);
		alert.show();
	}
	
}