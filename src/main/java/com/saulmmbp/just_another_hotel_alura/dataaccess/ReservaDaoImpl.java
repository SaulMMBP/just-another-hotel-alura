package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;
import java.time.LocalDateTime;
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

	@Override
	public Reserva findById(Long id) {
		Reserva reserva = null;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservas WHERE id_reserva=?")) {
			stmt.setLong(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					reserva = new Reserva(rs);
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		return reserva;
	}

	@Override
	public List<Reserva> findByDate(LocalDateTime fecha) {
		List<Reserva> reservas = new ArrayList<>();
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservas WHERE fecha_entrada BETWEEN ? AND ?")) {
			stmt.setTimestamp(1, Timestamp.valueOf(fecha));
			stmt.setTimestamp(2, Timestamp.valueOf(fecha.plusDays(1)));
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					reservas.add(new Reserva(rs));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		return reservas;
	}
	
	
	
	

}
