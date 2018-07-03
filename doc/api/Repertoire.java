package images;

public interface Repertoire {
	public void addChild(Repertoire repertoire);
	public List<Repertoire> getChildren();
}
