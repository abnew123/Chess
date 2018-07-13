package backend.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import backend.AlertMaker;
/**
 * class for writing data
 * errors checks location
 * @author shichengrao
 */
public class Writer {
	/**
	 * writes data to location
	 * @param location
	 * @param data
	 */
	public static void write(String location, Object data)  {
		XStream xstream = new XStream(new DomDriver());
		File file = new File(location);
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			ObjectOutputStream out = xstream.createObjectOutputStream(writer);
			out.writeObject(data);
		out.close();
		} catch (IOException e) {
			AlertMaker.makeAlert("IOException", "Could not successfully write to file");
		}
	}
}
