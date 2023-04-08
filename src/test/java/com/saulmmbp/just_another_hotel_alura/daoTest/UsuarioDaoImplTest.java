package com.saulmmbp.just_another_hotel_alura.daoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.saulmmbp.just_another_hotel_alura.dataaccess.UsuarioDaoImpl;
import com.saulmmbp.just_another_hotel_alura.model.Usuario;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

@TestMethodOrder(OrderAnnotation.class)
class UsuarioDaoImplTest {
	
	@Test
	@Order(1)
	void testFindAll() {
		List<Usuario> usuarios = new ArrayList<>();
		String usernameActual;
		String usernameExpected;
		try(Connection conn = MySqlConnection.getConnection()) {
			UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl(conn);
			usuarios = usuarioDaoImpl.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertNotNull(usuarios);
		usernameActual = usuarios.get(0).getUsername();
		usernameExpected = "admin";
		assertEquals(usernameExpected, usernameActual);
	}
	
	@Test
	@Order(2)
	void testFindById() {
		Usuario usuario = null;
		String usernameActual;
		String usernameExpected;
		try(Connection conn = MySqlConnection.getConnection()) {
			UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl(conn);
			usuario = usuarioDaoImpl.findById(1L);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertNotNull(usuario);
		usernameActual = usuario.getUsername();
		usernameExpected = "admin";
		assertEquals(usernameExpected, usernameActual);
	}
	
	@Test
	@Order(3)
	void testFindByUsername() {
		Usuario usuario = null;
		Long idActual;
		Long idExpected;
		try(Connection conn = MySqlConnection.getConnection()) {
			UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl(conn);
			usuario = usuarioDaoImpl.findByUsername("admin");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertNotNull(usuario);
		idActual = usuario.getId();
		idExpected = 1L;
		assertEquals(idActual, idExpected);
	}

	@Test
	@Order(4)
	void testCreate() {
		Usuario usuario = new Usuario("userTest", "passwordTest");
		int affectedRows = 0;
		try(Connection conn = MySqlConnection.getConnection()) {
			UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl(conn);
			affectedRows = usuarioDaoImpl.save(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(affectedRows > 0);
	}

	@Test
	@Order(5)
	void testUpdate() {
		int affectedRows = 0;
		try(Connection conn = MySqlConnection.getConnection()) {
			UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl(conn);
			Usuario usuario = usuarioDaoImpl.findAll().get(1);
			usuario.setUsername("userTestUpdated");
			usuario.setPassword("passwordTestUpdated");
			affectedRows = usuarioDaoImpl.save(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(affectedRows > 0);
	}
	
	@Test
	@Order(6)
	void testDelete() {
		int affectedRows = 0;
		try(Connection conn = MySqlConnection.getConnection()) {
			UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl(conn);
			Usuario usuario = usuarioDaoImpl.findAll().get(1);
			affectedRows = usuarioDaoImpl.deleteById(usuario.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(affectedRows > 0);
	}

}
