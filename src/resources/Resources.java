package resources;

import java.util.ResourceBundle;
/**
 * place for commonly used strings to have specific tags
 * @author shichengrao
 *
 */
public class Resources {
	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("resources/resourceKeys");
	public static String getString(String key) {
	        return RESOURCEKEYS.getString(key);
	}
}