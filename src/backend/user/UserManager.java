package backend.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import frontend.buttons.ButtonFactory;
import frontend.screens.UserScreen;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * models a database for all users
 * @author shichengrao
 *
 */
public class UserManager {
	private List<User> users;
	public UserManager() {
		users = new ArrayList<>();
	}
	/**
	 * makes a new user if old user with same name does not exist
	 * @param name
	 * @return success state of making the new user
	 */
	public boolean createNewUser(String name) {
		for(User user: users) {
			if(user.getName().equals(name)) {
				return false;
			}
		}
		users.add(new User(name));
		return true;
	}
	/**
	 * returns a user based on its name
	 * @param name
	 * @return
	 */
	public User getUser(String name) {
		return users.stream().filter(a -> a.getName().equals(name)).collect(Collectors.toList()).get(0);
	}
	/**
	 * makes a list of buttons, one for each user
	 * @param stage
	 * @return
	 */
	public List<Button> makeButtons(Stage stage){
		List<Button> buttonList = new ArrayList<>();
		for(User user: users) {
			buttonList.add(ButtonFactory.makeButton(user.getName(), e -> new UserScreen(stage, user), "title_button"));
		}
		return buttonList;
	}
	/**
	 * updates a user
	 * @param updatedUser
	 */
	public void updateUser(User updatedUser) {
		for(int i = users.size() - 1; i >= 0; i--) {
			User user = users.get(i);
			if(user.getName().equals(updatedUser.getName())) {
				users.remove(user);
				users.add(updatedUser);
			}
		}
	}
}
