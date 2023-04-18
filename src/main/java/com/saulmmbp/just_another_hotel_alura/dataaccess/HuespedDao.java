package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.SQLException;
import java.util.List;

import com.saulmmbp.just_another_hotel_alura.model.Huesped;

public interface HuespedDao {

	List<Huesped> findAll() throws SQLException;
	
	Huesped findById(Long id) throws SQLException;
	
	List<Huesped> findHuespedByNombreApellido(String username) throws SQLException;
	
	Long save(Huesped huesped) throws SQLException;
	
	int deleteById(Long id) throws SQLException;
	
}
