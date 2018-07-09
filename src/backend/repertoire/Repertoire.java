package backend.repertoire;

import java.util.List;

import backend.HalfTurn;

public abstract class Repertoire {
	protected List<Repertoire> children;
	
	public void addChild(Repertoire repertoire) {
		children.add(repertoire);
	}
	public boolean isEmpty() {
		return children.size() == 0;
	}
	public abstract String toString() ;
}
