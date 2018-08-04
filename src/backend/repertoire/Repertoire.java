package backend.repertoire;

import java.util.List;

public abstract class Repertoire {
	protected List<Repertoire> children;
	/**
	 * models a repertoire in chess
	 * @param repertoire
	 */
	public void addChild(Repertoire repertoire) {
		children.add(repertoire);
	}
	public boolean isEmpty() {
		return children.size() == 0;
	}
	public abstract String toString() ;
}
