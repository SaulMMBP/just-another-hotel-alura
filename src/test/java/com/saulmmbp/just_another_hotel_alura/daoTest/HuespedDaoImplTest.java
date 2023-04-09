package com.saulmmbp.just_another_hotel_alura.daoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import com.saulmmbp.just_another_hotel_alura.dataaccess.HuespedDaoImpl;
import com.saulmmbp.just_another_hotel_alura.model.Huesped;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

class HuespedDaoImplTest {

	@Test
	void testFindAllWithReservas() {
		List<Huesped> huespedes = new ArrayList<>();
		try(Connection conn = MySqlConnection.getConnection()) {
			HuespedDaoImpl dao = new HuespedDaoImpl(conn);
			huespedes = dao.findAllWithReservas();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		assertTrue(huespedes.size() > 1);
		huespedes.forEach(huesped -> {
			assertTrue(huesped.getReservas().size() > 0);
		});
	}

}
