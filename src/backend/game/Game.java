package backend.game;

import java.util.List;

import backend.HalfTurn;

public interface Game {
	public void load();
	public void save();
	public String getWinner();
	public List<HalfTurn> getTurns();
}
