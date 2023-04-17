package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;

import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
}
