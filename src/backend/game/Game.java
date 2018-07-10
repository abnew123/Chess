package backend.game;

import java.util.List;

import backend.HalfTurn;
import backend.Position;

public abstract class Game {
	public abstract void load();
	public abstract void save();
	public abstract List<HalfTurn> getTurns();
	public abstract Position getPosition();
}
