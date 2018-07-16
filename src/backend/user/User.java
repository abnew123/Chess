package backend.user;

import java.util.ArrayList;
import java.util.List;

import backend.game.FinishedGame;

public class User {
	private List<FinishedGame> history;
	private String name;
	public User(String name) {
		history = new ArrayList<>();
		this.name = name;
	}
	
	public void addGame(FinishedGame game) {
		history.add(game);
	}
	
	public String getName() {
		return name;
	}
	
	public String getStats() {
		String stats = "";
		stats += "Won: " + history.stream().filter(g->g.getWinner().equals("WHITE")).count() + "\n";
		stats += "Drawn: " + history.stream().filter(g->g.getWinner().equals("DRAW")).count() + "\n";
		stats += "Lost: " + history.stream().filter(g->g.getWinner().equals("BLACK")).count();
		return stats;
	}
}
