package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.saulmmbp.just_another_hotel_alura.model.Reserva;

public interface ReservaDao {
	
	List<Reserva> findAll() throws SQLException;
	
	Reserva findById(Long id) throws SQLException;
	
	List<Reserva> findByDate(LocalDateTime fecha) throws SQLException;
	
	Long save(Reserva reserva) throws SQLException;

	int deleteById(Long id) throws SQLException;

}
