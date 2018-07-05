package backend;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * class for loading data
 * error checks size and location
 * @author shichengrao
 */
public class Reader {
	
	public static final int MAX_CAPACITY = 3000;
	/**
	 * reads all data at target location
	 * @param location -filepath
	 * @return the list of objects stored at location
	 */
	public List<Object> read(String location){
		XStream xstream = new XStream(new DomDriver());
		FileReader reader = null;
		try {
			reader = new FileReader(location);
		} catch (FileNotFoundException e1) {
			AlertMaker.makeAlert("File Not Found", "No file at given file path");
		}
		List<Object> result = new ArrayList<>();
		ObjectInputStream in = null;
		try {
			in = xstream.createObjectInputStream(reader);
		} catch (IOException e1) {
			AlertMaker.makeAlert("IOException", "An IOException has occurred attempting to make the inputstream for reading");
			return null;
		}
		int counter = 0;
		while(true) {
			try {
				Object obj = in.readObject();
				result.add(obj);
				}
			catch(EOFException e) {
				//not real error, just signifies end of file
				break;
			} catch (ClassNotFoundException e) {
				AlertMaker.makeAlert("Class not found", "Unable to load object from data file due to object class not existing");
			} catch (IOException e) {
				AlertMaker.makeAlert("IOException", "Unable to read object");
			}
			counter += 1;
			if (counter == MAX_CAPACITY) {
				AlertMaker.makeAlert("File Size Error", "File too large for Reader to load");
				return null;
			}
		}
		return result;
	}
	/**
	 * reads data of type category from location
	 * reworked so there is no longer massive amounts of duplicated code
	 * @param location
	 * @param category
	 * @return list of objects of type category
	 */
	public List<Object> read(String location, String category) {
		List<Object> result = read(location);
		result = result.stream().filter(o->o.getClass().getName().equals(category)).collect(Collectors.toList());
		return result;
	}
}
