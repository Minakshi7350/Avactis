package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileRead {
	public String value;
	public String getPropertyvalue(String keys) throws IOException {
	BufferedReader readers = new BufferedReader(new FileReader("D:\\Selenium\\Avactis\\src\\test\\resources\\project configuration Data\\Config.properties"));
	Properties prop = new Properties();
	prop.load(readers);
	return value = prop.getProperty(keys);
	
	}
}