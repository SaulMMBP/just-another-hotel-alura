package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.SQLException;

public interface UsuarioDao {

	String getPassword(String username) throws SQLException;
}
