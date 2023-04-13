package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.util.List;

import com.saulmmbp.just_another_hotel_alura.model.Reserva;

public interface ReservaDao {
	
	List<Reserva> findAll();

}
