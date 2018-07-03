package voogasalad_raptiltswagger;
import java.util.List;
public interface Reader {
	/**
	 * reads data of type category from location
	 * @param location
	 * @param category
	 * @return
	 */
	public List<?> read(String location, String category);
	/**
	 * reads all data at target location
	 * @param location
	 * @param category
	 * @return
	 */
	public List<?> read(String location);
}
