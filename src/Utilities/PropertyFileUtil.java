package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyFileUtil {
	public static String getValueForkey(String key) throws Throwable, Throwable
	{
		Properties configprop=new Properties();
		configprop.load(new FileInputStream("D:\\software\\seleniumproject\\OrangeHybrideFramework\\PropertyFile\\Environment.properties"));
		return configprop.getProperty(key);
	}

}
