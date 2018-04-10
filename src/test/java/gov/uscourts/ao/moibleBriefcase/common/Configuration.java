package gov.uscourts.ao.moibleBriefcase.common;

import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {

	private static Properties configFile;

	static {

		try {
			String path = "./src/test/resources/test-properties/test.properties";
			FileInputStream input = new FileInputStream(path);

			configFile = new Properties();
			configFile.load(input);

			input.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static String getFile(String path) {

		try {
			String location = "./src/test/resources/" + path;
			FileInputStream input = new FileInputStream(location);
			configFile = new Properties();
			configFile.load(input);

			input.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return path;

	}

	public static String getProperty(String keyName) {
		return configFile.getProperty(keyName);
	}

}
