package com.saulmmbp.just_another_hotel_alura.utilTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.*;

import org.junit.jupiter.api.Test;

import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

class MySqlConnectionTest {

	@Test
	void testGetConnection() {
		try(Connection conn = MySqlConnection.getConnection()) {
			String schemaActual = conn.getCatalog();
			String schemaExpected = "hotel_alura";
			
			assertEquals(schemaExpected, schemaActual);
		} catch (SQLException e) {
			System.err.println(e.getMessage() + ":" + e.getCause());
		}
	}

}
