package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;

import com.saulmmbp.just_another_hotel_alura.dataaccess.*;
import com.saulmmbp.just_another_hotel_alura.model.Usuario;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class LoginService {
	
	public boolean login(String username, String password) {
//		boolean login = false;
//		try(Connection conn = MySqlConnection.getConnection()) {
//			UsuarioDao usuarioDao = new UsuarioDaoImpl(conn);
//			Usuario usuario = usuarioDao.findByUsername(username);
//			if(usuario != null) {
//				if(usuario.getPassword().equals(password)) {
//					login = true;
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return false;
	}
}
