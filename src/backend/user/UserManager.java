package backend.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import frontend.buttons.ButtonFactory;
import frontend.screens.UserScreen;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserManager {
	private List<User> users;
	public UserManager() {
		users = new ArrayList<>();
	}
	public boolean createNewUser(String name) {
		for(User user: users) {
			if(user.getName().equals(name)) {
				return false;
			}
		}
		users.add(new User(name));
		return true;
	}
	public User getUser(String name) {
		return users.stream().filter(a -> a.getName().equals(name)).collect(Collectors.toList()).get(0);
	}
	
	public List<Button> makeButtons(Stage stage){
		List<Button> buttonList = new ArrayList<>();
		for(User user: users) {
			buttonList.add(ButtonFactory.makeButton(user.getName(), e -> new UserScreen(stage, user), "title_button"));
		}
		return buttonList;
	}
	
	public void updateUser(User updatedUser) {
		for(User user: users) {
			if(user.getName().equals(updatedUser.getName())) {
				users.remove(user);
				users.add(updatedUser);
			}
		}
	}
}
