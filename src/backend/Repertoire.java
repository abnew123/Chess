package backend;

import java.util.List;

public interface Repertoire {
	public void addChild(Repertoire repertoire);
	public List<Repertoire> getChildren();
}
