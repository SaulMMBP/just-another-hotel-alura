package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

import com.saulmmbp.just_another_hotel_alura.model.*;

public class ReservaDaoImpl implements ReservaDao {

	private Connection conn;
	
	public ReservaDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Reserva> findAll() throws SQLException {
		List<Reserva> reservas = new ArrayList<>();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM reservas")) {
			while(rs.next()) {
				Reserva reserva = new Reserva(rs);
				reserva.getHuesped().setId(rs.getLong("huesped_id"));
				reservas.add(reserva);
			}
		}
		return reservas;
	}

	@Override
	public Reserva findById(Long id) throws SQLException {
		Reserva reserva = null;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservas AS r "
				+ "INNER JOIN huespedes AS h ON r.huesped_id=h.id_huesped WHERE r.id_reserva=?")) {
			stmt.setLong(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					reserva = new Reserva(rs);
					Huesped huesped = new Huesped(rs);
					reserva.setHuesped(huesped);
				}
			}
		}
		return reserva;
	}

	@Override
	public List<Reserva> findByDate(LocalDateTime fecha) throws SQLException {
		List<Reserva> reservas = new ArrayList<>();
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservas WHERE fecha_entrada BETWEEN ? AND ?")) {
			stmt.setTimestamp(1, Timestamp.valueOf(fecha));
			stmt.setTimestamp(2, Timestamp.valueOf(fecha.plusDays(1)));
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					reservas.add(new Reserva(rs));
				}
			}
		}
		return reservas;
	}

	@Override
	public Long save(Reserva reserva) throws SQLException {
		int affectedRows = 0; 
		Long generatedKey = 0L;
		String sql;
		if(reserva.getId() != null && reserva.getId() != 0) {
			sql = "UPDATE reservas SET fecha_entrada=?, fecha_salida=?, valor=?, forma_pago=?, huesped_id=? WHERE id_reserva=?";
		} else {
			sql = "INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_pago, huesped_id) VALUES (?,?,?,?,?)";
		}
		try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
			stmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
			stmt.setString(3, reserva.getValor().toPlainString());
			stmt.setString(4, reserva.getFormaPago());
			stmt.setLong(5, reserva.getHuesped().getId());
			if(reserva.getId() != null && reserva.getId() != 0) {
				stmt.setLong(6, reserva.getId());
			} 
			affectedRows = stmt.executeUpdate();
			
			try(ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					generatedKey = rs.getLong(1);
				}
			}
			
		}
		if(reserva.getId() != null && reserva.getId() != 0) {
			return (long) affectedRows;
		} else {
			return generatedKey;
		}
	}

	@Override
	public int deleteById(Long id) throws SQLException {
		int affectedRows = 0;
		try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM reservas WHERE id_reserva=?")) {
			stmt.setLong(1, id);
			affectedRows = stmt.executeUpdate();
		}
		return affectedRows;
	}
	
}
