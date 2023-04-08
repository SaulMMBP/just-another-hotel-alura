package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;
import java.util.*;

import com.saulmmbp.just_another_hotel_alura.model.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	private Connection conn;
	
	public UsuarioDaoImpl(Connection conn) throws SQLException {
		this.conn = conn;
	}

	@Override
	public int save(Usuario entity) throws SQLException {
		int affectedRows = 0;
		String sql;
		if (entity.getId() == null || entity.getId() == 0) {
			sql = "INSERT INTO usuarios(username, password) VALUES (?,?)";
		} else {
			sql = "UPDATE usuarios SET username=?, password=? WHERE id=?";
		}
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, entity.getUsername());
			stmt.setString(2, entity.getPassword());
			if(entity.getId() != null && entity.getId() != 0) {
				stmt.setLong(3, entity.getId());
			}
			affectedRows = stmt.executeUpdate();
		}
		return affectedRows;
	}

	@Override
	public Usuario findById(Long id) throws SQLException {
		Usuario usuario = null; 
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id=?")) {
			stmt.setLong(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					usuario = getUsuario(rs);
				}
			}
		}
		return usuario;
	}

	@Override
	public List<Usuario> findAll() throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");) {
			while(rs.next()) {
				usuarios.add(getUsuario(rs));
			}
		}
		return usuarios;
	}

	@Override
	public int deleteById(Long id) throws SQLException {
		int affectedRows = 0;
		try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id=?")) {
			stmt.setLong(1, id);
			affectedRows = stmt.executeUpdate();
		}
		return affectedRows;
	}
	
	private Usuario getUsuario(ResultSet rs) throws SQLException {
		Usuario u = new Usuario();
		u.setId(rs.getLong(1));
		u.setUsername(rs.getString(2));
		u.setPassword(rs.getString(3));
		return u;
	}

	@Override
	public Usuario findByUsername(String username) throws SQLException {
		Usuario usuario = null; 
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE username=?")) {
			stmt.setString(1, username);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					usuario = getUsuario(rs);
				}
			}
		}
		return usuario;
	}

}
