package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.time.LocalDateTime;
import java.util.List;

import com.saulmmbp.just_another_hotel_alura.model.Reserva;

public interface ReservaDao {
	
	List<Reserva> findAll();
	
	Reserva findById(Long id);
	
	List<Reserva> findByDate(LocalDateTime fecha);
	
	Long save(Reserva reserva);

}
