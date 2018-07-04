package backend;

import java.util.List;

public interface Game {
	public void load();
	public void save();
	public String getWinner();
	public List<HalfTurn> getTurns();
}
