package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
	
	static Properties prop=new Properties();
	
	public static void loadProperties()
	{
			try {
				System.out.println("hhh");
				FileInputStream in = new FileInputStream("src/test/java/config/configure.properties");
				prop.load(in);	
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IO Exception while accessing the file");
			}	
	}
	public static String getProperty(String keyvalue)
	{
		
		return prop.getProperty(keyvalue);	
	}
	
	
	
	
  
}
