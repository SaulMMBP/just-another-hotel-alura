package com.saulmmbp.just_another_hotel_alura.configsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.saulmmbp.just_another_hotel_alura.config.PropertiesReader;

class PropertiesReaderTest {
	
	@Test
	void testGetProperties() {
		Properties properties = PropertiesReader.getProperties();
		assertNotNull(properties);
	}

	@Test
	void testGetProperty() {
		String usernameActual = PropertiesReader.getProperty("hotel_alura.db.username");
		String passwordActual = PropertiesReader.getProperty("hotel_alura.db.password");
		
		String usernameExpected = System.getenv("MYSQL_USERNAME");
		String passwordExpected = System.getenv("MYSQL_PASSWORD");
		
		assertEquals(usernameExpected, usernameActual);
		assertEquals(passwordExpected, passwordActual);
	}

}
