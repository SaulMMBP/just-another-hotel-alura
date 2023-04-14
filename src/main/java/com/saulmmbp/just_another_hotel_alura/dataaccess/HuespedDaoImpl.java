package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.saulmmbp.just_another_hotel_alura.model.*;

public class HuespedDaoImpl implements HuespedDao {

	private Connection conn;

	public HuespedDaoImpl(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Return all the guests from the data base
	 */
	@Override
	public List<Huesped> findAll() {
		List<Huesped> huespedes = new ArrayList<>();
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM huespedes")) {
			while (rs.next()) {
				huespedes.add(new Huesped(rs));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + ":" + e.getCause());
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta", "Hotel Alura Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}

	/**
	 * Return a guest in the data base where name or lastname is the keyword 
	 */
	@Override
	public List<Huesped> findHuespedByNombreApellido(String keyword) {
		List<Huesped> huespedes = new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM huespedes WHERE nombre LIKE ? OR apellido LIKE ?")) {
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					huespedes.add(new Huesped(rs));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + ":" + e.getCause());
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta", "Hotel Alura Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}

	/**
	 * Return a guest with the id indicated
	 */
	@Override
	public Huesped findById(Long id) {
		Huesped huesped = null;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM huespedes WHERE id_huesped=?")) {
			stmt.setLong(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					huesped = new Huesped(rs);
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + ":" + e.getCause());
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta", "Hotel Alura Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return huesped;
	}

}
