package com.saulmmbp.just_another_hotel_alura.business.dto;

import java.util.Date;

import com.saulmmbp.just_another_hotel_alura.model.*;

public record HuespedDTO(Long id, String nombre, String apellido, Date fechaNacimiento, Nacionalidad nacionalidad,
		String telefono) {
	public HuespedDTO(Huesped huesped) {
		this(huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(),
				huesped.getNacionalidad(), huesped.getTelefono());
	}

	public String[] getHuespedRow() {
		return new String[] { String.valueOf(this.id), this.nombre, this.apellido, this.fechaNacimiento.toString(),
				this.nacionalidad.toString(), this.telefono };
	}
}
