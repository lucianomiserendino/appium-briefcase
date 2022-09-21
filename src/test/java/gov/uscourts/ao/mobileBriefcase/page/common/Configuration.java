package gov.uscourts.ao.mobileBriefcase.page.common;

import java.io.FileInputStream;

import java.util.Properties;
public class Configuration {

	private static Properties configFile;
	static String path = "";

	static {

		try {

			path = "./src/test/resources/test-properties/amb.properties";

			FileInputStream input = new FileInputStream(path);

			configFile = new Properties();
			configFile.load(input);

			input.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static String getProperty(String keyName) {
		return configFile.getProperty(keyName);
	}

}
