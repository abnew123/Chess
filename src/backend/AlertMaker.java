package backend;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * class for displaying simple pop-ups onto the screen
 * @author shichengrao
 * code originates for voogasalad project from a CS308 project I was part of 
 */
public final class AlertMaker {
	/**
	 * outputs an alert
	 * @param head - title of the alert
	 * @param content - body of the alert
	 */
	public static void  makeAlert(String head, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(head);
		alert.setContentText(content);
		alert.show();
	}
	
}