package org.bdd.jbehave;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocalProperties {

	private static Properties properties = null;

	private static Properties initializePropertiesFiles() {
		if (properties == null) {
			try (FileInputStream fis = new FileInputStream(new File("./src/test/resources/local.properties"))) {
				properties = new Properties();
				properties.load(fis);
			} catch (IOException e) {

			}
		}

		return properties;
	}

	private static String getProperty(String key) {
		String value;

		if ((value = System.getProperty(key)) != null)
			return value;

		properties = initializePropertiesFiles();
		value = properties.getProperty(key);
		return value;
	}

	public static String getProxyPort() {
		return getProperty("proxy.port");
	}

	public static String getProxyHost() {
		return getProperty("proxy.host");
	}

	public static String getApiUser() {
		return getProperty("api.user");
	}

	public static String getApiPassword() {
		return getProperty("api.password");
	}

	public static String getApiEndPoint() {
		return getProperty("api.endpoint");
	}

}
