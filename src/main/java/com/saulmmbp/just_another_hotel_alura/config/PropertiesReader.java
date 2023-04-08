package com.saulmmbp.just_another_hotel_alura.config;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	private static Properties properties;

	public static Properties getProperties() {
		/* Inicializamos propiedades */
		if (PropertiesReader.properties == null) {
			PropertiesReader.properties = new Properties();
		}
		if (properties != null) {
			/* Cargamos archivo properties y obtenemos variables de entorno */
			try (InputStream input = PropertiesReader.class.getClassLoader()
					.getResourceAsStream("hotel-alura.properties")) {
				properties.load(input);
				
				properties.forEach((key, value) -> {
					if (String.valueOf(value).contains("$")) {
						String env = String.valueOf(value).substring(2, String.valueOf(value).length() - 1);
						properties.setProperty(String.valueOf(key), System.getenv(env));
					}
				});
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return properties;
	}

	public static String getProperty(String key) {
		return getProperties().getProperty(key);
	}

}
