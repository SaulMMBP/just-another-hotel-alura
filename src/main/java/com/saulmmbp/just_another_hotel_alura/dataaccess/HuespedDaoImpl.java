package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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
	public List<Huesped> findAll() throws SQLException{
		List<Huesped> huespedes = new ArrayList<>();
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM huespedes")) {
			while (rs.next()) {
				huespedes.add(new Huesped(rs));
			}
		}
		return huespedes;
	}

	/**
	 * Return a guest in the data base where name or lastname is the keyword 
	 */
	@Override
	public List<Huesped> findHuespedByNombreApellido(String keyword) throws SQLException{
		List<Huesped> huespedes = new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM huespedes WHERE nombre LIKE ? OR apellido LIKE ?")) {
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					huespedes.add(new Huesped(rs));
				}
			}
		}
		return huespedes;
	}

	/**
	 * Return a guest with the id indicated
	 */
	@Override
	public Huesped findById(Long id) throws SQLException{
		Huesped huesped = null;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM huespedes AS h "
				+ "INNER JOIN reservas AS r ON h.id_huesped=r.huesped_id WHERE id_huesped=?")) {
			stmt.setLong(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					huesped = new Huesped(rs);
					huesped.addReserva(new Reserva(rs));
				}
				while(rs.next()) {
					huesped.addReserva(new Reserva(rs));
				}
			}
		}
		return huesped;
	}

	@Override
	public Long save(Huesped huesped) throws SQLException{
		int affectedRows = 0; 
		Long generatedKey = 0L;
		String sql;
		if(huesped.getId() != null && huesped.getId() != 0) {
			sql = "UPDATE huespedes SET nombre=?, apellido=?, fecha_nacimiento=?, nacionalidad=?, telefono=? WHERE id_huesped=?";
		} else {
			sql = "INSERT INTO huespedes(nombre, apellido, fecha_nacimiento, nacionalidad, telefono) VALUES (?,?,?,?,?)";
		}
		try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, huesped.getNombre());
			stmt.setString(2, huesped.getApellido());
			stmt.setDate(3, Date.valueOf(huesped.getFechaNacimiento()));
			stmt.setString(4, huesped.getNacionalidad().toString());
			stmt.setString(5, huesped.getTelefono());
			if(huesped.getId() != null && huesped.getId() != 0) {
				stmt.setLong(6, huesped.getId());
			} 
			affectedRows = stmt.executeUpdate();
			
			try(ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					generatedKey = rs.getLong(1);
				}
			}
		}
		
		if(huesped.getId() != null && huesped.getId() != 0) {
			return (long) affectedRows;
		} else {
			return generatedKey;
		}
		
	}

	/**
	 * Delete a huesped and his related reservations
	 */
	@Override
	public int deleteById(Long id) throws SQLException {
		int affectedRows = 0;
		try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM huespedes WHERE id_huesped=?")) {
			stmt.setLong(1, id);
			affectedRows = stmt.executeUpdate();
		}
		return affectedRows;
	}

}
