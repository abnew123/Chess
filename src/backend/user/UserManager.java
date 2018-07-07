package backend.user;

import java.util.HashMap;
import java.util.Map;

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
}
