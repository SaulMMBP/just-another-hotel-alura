package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.SQLException;

import com.saulmmbp.just_another_hotel_alura.model.Usuario;

public interface UsuarioDao extends Dao<Usuario, Long> {

	Usuario findByUsername(String username) throws SQLException;
}
