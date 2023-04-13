package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;

import com.saulmmbp.just_another_hotel_alura.dataaccess.*;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class LoginService {
	
	public static boolean login(String username, String password) {
		try(Connection conn = MySqlConnection.getConnection()) {
			UsuarioDao usuarioDao = new UsuarioDaoImpl(conn);
			String pass = usuarioDao.getPassword(username);
			if(pass != null) {
				if(pass.equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error de conexión con la base de datos");
		}
		return false;
	}
}
