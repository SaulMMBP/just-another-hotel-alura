package com.saulmmbp.just_another_hotel_alura.business.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.saulmmbp.just_another_hotel_alura.model.*;

public record HuespedDTO(Long id, String nombre, String apellido, LocalDate fechaNacimiento, Nacionalidad nacionalidad,
		String telefono, List<Reserva> reservas) implements Dto {
	public HuespedDTO(Huesped huesped) {
		this(huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(),
				huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReservas());
	}

	public String[] getRow() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		return new String[] { String.valueOf(this.id), this.nombre, this.apellido, this.fechaNacimiento.format(formatter),
				this.nacionalidad.toString(), this.telefono };
	}
	
	public List<ReservaDTO> getReservasDTO() {
		return this.reservas.stream().map(reserva -> new ReservaDTO(reserva)).toList();
	}
}
