package backend.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frontend.buttons.ButtonFactory;
import frontend.screens.UserScreen;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserManager {
	private Map<String, User> users;
	public UserManager() {
		users = new HashMap<>();
	}
	public boolean createNewUser(String name) {
		if(!users.keySet().contains(name)) {
			users.put(name, new User());
			return true;
		}
		return false;
	}
	public User getUser(String name) {
		return users.get(name);
	}
	
	public List<Button> makeButtons(Stage stage){
		List<Button> buttonList = new ArrayList<>();
		for(String name: users.keySet()) {
			buttonList.add(ButtonFactory.makeButton(name, e -> new UserScreen(stage, name), "image_button"));
		}
		return buttonList;
	}
}
