package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.util.List;

import com.saulmmbp.just_another_hotel_alura.model.Huesped;

public interface HuespedDao {

	List<Huesped> findAll();
	
	Huesped findById(Long id);
	
	List<Huesped> findHuespedByNombreApellido(String username);
	
}
