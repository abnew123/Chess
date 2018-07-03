package images;

public interface Game {
	public void load();
	public void save();
	public String getWinner();
	public List<HalfTurn> getTurns();
}
