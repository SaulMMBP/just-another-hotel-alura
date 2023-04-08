package com.saulmmbp.just_another_hotel_alura.daoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.saulmmbp.just_another_hotel_alura.dataaccess.HuespedDaoImpl;
import com.saulmmbp.just_another_hotel_alura.model.*;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

@TestMethodOrder(OrderAnnotation.class)
class HuespedDaoImplTest {

	@Test
	@Order(1)
	void testSave() {
		int affectedRows = 0;
		try (Connection conn = MySqlConnection.getConnection()) {
			HuespedDaoImpl huespedDao = new HuespedDaoImpl(conn);
			Huesped huesped = new Huesped("Saul", "Malagon", Date.valueOf("1995-02-17"), Nacionalidad.MEXICO,
					"5524356453");
			affectedRows = huespedDao.save(huesped);
		} catch (SQLException e) {
			fail("La prueba lanzó una Exception");
		}
		assertTrue(affectedRows > 0);
	}

	@Test
	@Order(2)
	void testFindAll() {
		List<Huesped> huespedes = new ArrayList<>();
		try (Connection conn = MySqlConnection.getConnection()) {
			HuespedDaoImpl huespedDao = new HuespedDaoImpl(conn);
			huespedes = huespedDao.findAll();
		} catch (SQLException e) {
			fail("La prueba lanzó una Exception");
		}
		assertNotNull(huespedes);
		assertTrue(huespedes.stream().anyMatch(huesped -> huesped.getNombre().equals("Saul")));
	}

	@Test
	@Order(3)
	void testFindById() {
		Huesped huesped = null;
		try (Connection conn = MySqlConnection.getConnection()) {
			HuespedDaoImpl huespedDao = new HuespedDaoImpl(conn);
			List<Huesped> huespedes = huespedDao.findAll();
			huesped = huespedDao.findById(huespedes.get(huespedes.size() - 1).getId());
		} catch (SQLException e) {
			fail("La prueba lanzó una Exception");
		}
		assertNotNull(huesped);
		assertTrue(huesped.getNombre().equals("Saul"));
	}

	@Test
	@Order(4)
	void testUpdate() {
		int affectedRows = 0;
		try (Connection conn = MySqlConnection.getConnection()) {
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			HuespedDaoImpl huespedDao = new HuespedDaoImpl(conn);
			try {
				List<Huesped> huespedes = huespedDao.findAll();
				Huesped huesped = huespedDao.findById(huespedes.get(huespedes.size() - 1).getId());
				huesped.setApellido("Martinez");
				affectedRows = huespedDao.save(huesped);
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				fail("La prueba lanzó una Exception y se hizo rollback");
			}
		} catch (SQLException e) {
			fail("La prueba lanzó una Exception");
		}
		assertTrue(affectedRows > 0);
	}

	@Test
	@Order(5)
	void testDeleteById() {
		int affectedRows = 0;
		try (Connection conn = MySqlConnection.getConnection()) {
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			HuespedDaoImpl huespedDao = new HuespedDaoImpl(conn);
			try {
				List<Huesped> huespedes = huespedDao.findAll();
				affectedRows = huespedDao.deleteById(huespedes.get(huespedes.size() - 1).getId());
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				fail("La prueba lanzó una Exception y se hizo rollback");
			}
		} catch (SQLException e) {
			fail("La prueba lanzó una Exception");
		}
		assertTrue(affectedRows > 0);
	}

}
