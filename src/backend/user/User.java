package backend.user;

import java.util.ArrayList;
import java.util.List;

import backend.game.Game;

public class User {
	private List<Game> history;
	public User() {
		history = new ArrayList<>();
	}
	public void loadUser() {
		//reserved for front end compatibility
	}
	public void addGame(Game game) {
		history.add(game);
	}
	public String getStats() {
		String stats = "";
		stats += "Won: " + history.stream().filter(g->g.getWinner().equals("WHITE")).count();
		stats += "Drawn: " + history.stream().filter(g->g.getWinner().equals("DRAW")).count();
		stats += "Lost: " + history.stream().filter(g->g.getWinner().equals("BLACK")).count();
		return stats;
	}
}
