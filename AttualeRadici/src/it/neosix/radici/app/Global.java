/**
 * 
 */
package it.neosix.radici.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author NeosiX
 *
 */
public class Global {

	private static Global INSTANCE = null;
	private static Properties props; 

	public static Global getInstance() {
		if (INSTANCE == null) {
			try {
				INSTANCE = new Global();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return INSTANCE;
	}

	/**
	 * Singletone
	 */
	private Global() throws FileNotFoundException {
		try {
			InputStream in = getClass().getResourceAsStream("/resource/global.properties");
			props = new Properties(); 
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getkey(String str) {
		return props.getProperty(str);
	}
	
}
