package backend;

import java.util.List;

public interface Writer {
	/**
	 * writes data to location
	 * @param location
	 * @param data
	 */
	public void write(String location, List<Object> data);
}
