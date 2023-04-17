package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.*;

public class UsuarioDaoImpl implements UsuarioDao {

	private Connection conn;
	
	public UsuarioDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public String getPassword(String username) throws SQLException {
		String password = null;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT password FROM usuarios WHERE username=?")) {
			stmt.setString(1, username);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					password = rs.getString("password");
				}
			}
		}
		return password;
	}
	
	

}
