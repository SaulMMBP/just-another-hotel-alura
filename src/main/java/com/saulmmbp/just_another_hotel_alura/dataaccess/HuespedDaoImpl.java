package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;
import java.util.*;

import com.saulmmbp.just_another_hotel_alura.model.*;

public class HuespedDaoImpl implements HuespedDao {

	private Connection conn;
	
	public HuespedDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Huesped> findAllWithReservas() {
		List<Huesped> huespedes = new ArrayList<>();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT h.id AS id_huesped, h.nombre, h.apellido, h.fecha_nacimiento, h.nacionalidad, h.telefono,"
						+ "r.id AS id_reserva, r.fecha_entrada, r.fecha_salida, r.valor, r.forma_pago "
						+ "FROM huespedes AS h "
						+ "INNER JOIN reservas AS r ON h.id=r.huesped_id")) {
			while(rs.next()) {
				Huesped huesped = getHuesped(rs);
				Reserva reserva = getReserva(rs);
				if(!huespedes.contains(huesped)) {
					huesped.addReserva(reserva);
					huespedes.add(huesped);
				} else {
					huespedes.get(huespedes.indexOf(huesped)).addReserva(reserva);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return huespedes;
	}

	@Override
	public List<Huesped> findHuespedByName(String username) {
		List<Huesped> huespedes = new ArrayList<>();
		try(PreparedStatement stmt = conn.prepareStatement("SELECT h.id AS id_huesped, h.nombre, "
				+ "h.apellido, h.fecha_nacimiento, h.nacionalidad, h.telefono,"
				+ "r.id AS id_reserva, r.fecha_entrada, r.fecha_salida, r.valor, r.forma_pago "
				+ "FROM huespedes AS h "
				+ "INNER JOIN reservas AS r ON h.id=r.huesped_id WHERE h.nombre LIKE ?")) {
			stmt.setString(1, "%" + username + "%");
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					Huesped huesped = getHuesped(rs);
					Reserva reserva = getReserva(rs);
					if(!huespedes.contains(huesped)) {
						huesped.addReserva(reserva);
						huespedes.add(huesped);
					} else {
						huespedes.get(huespedes.indexOf(huesped)).addReserva(reserva);
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return huespedes;
	}
	
	private Nacionalidad toNacionalidadEnum(String nacionalidad) {
		int i = 0;
		Nacionalidad[] values = Nacionalidad.values();
		while(i < values.length && !values[i].toString().equals(nacionalidad)) i++;
		
		if (i != values.length && values[i].toString().equals(nacionalidad)) {
			return values[i];
		}
		throw new RuntimeException("No se encontró la nacionalidad en el sistema");
	}

	private Reserva getReserva(ResultSet rs) throws SQLException {
		Reserva reserva = new Reserva();
		reserva.setId(rs.getLong("id_reserva"));
		reserva.setFechaEntrada(rs.getDate("fecha_entrada").toLocalDate());
		reserva.setFechaSalida(rs.getDate("fecha_salida").toLocalDate());
		reserva.setValor(rs.getBigDecimal("valor"));
		reserva.setFormaPago(rs.getString("forma_pago"));
		reserva.setHuesped_id(rs.getLong("id_huesped"));
		return reserva;
	}

	private Huesped getHuesped(ResultSet rs) throws SQLException {
		Huesped huesped = new Huesped();
		huesped.setId(rs.getLong("id_huesped"));
		huesped.setNombre(rs.getString("nombre"));
		huesped.setApellido(rs.getString("apellido"));
		huesped.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
		huesped.setNacionalidad(toNacionalidadEnum(rs.getString("nacionalidad")));
		huesped.setTelefono(rs.getString("telefono"));
		return huesped;
	}

}
