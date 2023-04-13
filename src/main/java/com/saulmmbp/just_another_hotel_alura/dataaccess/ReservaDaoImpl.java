package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.saulmmbp.just_another_hotel_alura.model.Reserva;

public class ReservaDaoImpl implements ReservaDao {

	private Connection conn;
	
	public ReservaDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Reserva> findAll() {
		List<Reserva> reservas = new ArrayList<>();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM reservas")) {
			while(rs.next()) {
				reservas.add(new Reserva(rs));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		return reservas;
	}

}
