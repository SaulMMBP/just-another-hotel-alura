package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;
import java.util.*;

import com.saulmmbp.just_another_hotel_alura.model.*;

public class HuespedDaoImpl implements Dao<Huesped, Long> {

	private Connection conn;
	
	public HuespedDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int save(Huesped entity) throws SQLException {
		int affectedRows = 0;
		String sql;
		if(entity.getId() != null && entity.getId() != 0) {
			sql = "UPDATE huespedes SET nombre=?, apellido=?, fecha_nacimiento=?, nacionalidad=?, telefono=? WHERE id=?";
		} else {
			sql = "INSERT INTO huespedes(nombre, apellido, fecha_nacimiento, nacionalidad, telefono) VALUES (?,?,?,?,?)";
		}
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, entity.getNombre());
			stmt.setString(2, entity.getApellido());
			stmt.setDate(3, entity.getFechaNacimiento());
			stmt.setString(4, entity.getNacionalidad().toString());
			stmt.setString(5, entity.getTelefono());
			if(entity.getId() != null && entity.getId() != 0) {
				stmt.setLong(6, entity.getId());
			}
			affectedRows = stmt.executeUpdate();
		}
		return affectedRows;
	}

	@Override
	public Huesped findById(Long id) throws SQLException {
		Huesped huesped = null;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM huespedes WHERE id=?")) {
			stmt.setLong(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					huesped = getHuesped(rs);
				}
			}
		}
		return huesped;
	}

	@Override
	public List<Huesped> findAll() throws SQLException {
		List<Huesped> huespedes = new ArrayList<>();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM huespedes")) {
			while(rs.next()) {
				huespedes.add(getHuesped(rs));
			}
		}
		return huespedes;
	}

	@Override
	public int deleteById(Long id) throws SQLException {
		int affectedRows = 0;
		try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM huespedes WHERE id=?")) {
			stmt.setLong(1, id);
			affectedRows = stmt.executeUpdate();
		}
		return affectedRows;
	}
	
	private Huesped getHuesped(ResultSet rs) throws SQLException {
		Huesped huesped = new Huesped();
		huesped.setId(rs.getLong("id"));
		huesped.setNombre(rs.getString("nombre"));
		huesped.setApellido(rs.getString("apellido"));
		huesped.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
		huesped.setNacionalidad(getNacionalidad(rs.getString("nacionalidad")));
		huesped.setTelefono(rs.getString("telefono"));
		return huesped;
	}
	
	private Nacionalidad getNacionalidad(String nacionalidad) {
		int i = 0;
		for(; i < Nacionalidad.values().length && !Nacionalidad.values()[i].toString().equals(nacionalidad); i++) {}
		if (Nacionalidad.values()[i].toString().equals(nacionalidad)) {
			return Nacionalidad.values()[i];
		}
		throw new RuntimeException("Nacionalidad no encontrada");
	}

}
