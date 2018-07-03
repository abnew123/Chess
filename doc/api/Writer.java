package voogasalad_raptiltswagger;
public interface Writer {
	/**
	 * writes data to location
	 * @param location
	 * @param data
	 */
	public void write(String location, List<Object> data);
}
