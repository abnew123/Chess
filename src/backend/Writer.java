package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
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
	public void write(String location, List<? extends Object> data)  {
		XStream xstream = new XStream(new DomDriver());
		File file = new File(location);
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			ObjectOutputStream out = xstream.createObjectOutputStream(writer);
			for(Object datum: data) {
				out.writeObject(datum);
		}
		out.close();
		} catch (IOException e) {
			AlertMaker.makeAlert("IOException", "Could not successfully write to file");
		}
	}
	
	/**
	 * writes data to location without overwriting previous data
	 * @param location
	 * @param data
	 */
	public void writeNoOverwrite(String location, List<Object> data)  {
		Reader myReader = new Reader();
		List<Object> prevData= myReader.read(location);
		data.addAll(prevData);
	}
}
