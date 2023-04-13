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

	@Override
	public List<Huesped> findAll() {
		List<Huesped> huespedes = new ArrayList<>();
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM huespedes")) {
			while (rs.next()) {
				huespedes.add(new Huesped(rs));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta", "Hotel Alura Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}

	@Override
	public List<Huesped> findHuespedByName(String huespedName) {
		List<Huesped> huespedes = new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM huespedes WHERE nombre LIKE ?")) {
			stmt.setString(1, "%" + huespedName + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					huespedes.add(new Huesped(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return huespedes;
	}

}
