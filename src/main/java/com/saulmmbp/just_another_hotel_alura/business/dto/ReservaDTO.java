package com.saulmmbp.just_another_hotel_alura.business.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.saulmmbp.just_another_hotel_alura.model.Reserva;

public record ReservaDTO(Long id, LocalDate fechaEntrada, LocalDate fechaSalida, BigDecimal valor, String formaPago,
		Long huesped_id) implements Dto {
	public ReservaDTO(Reserva reserva) {
		this(reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getValor(),
				reserva.getFormaPago(), reserva.getHuesped_id());
	}
	
	public String[] getRow() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		return new String[] { String.valueOf(this.id), this.fechaEntrada.format(formatter), this.fechaSalida.format(formatter), this.valor.toPlainString(),
				this.formaPago, String.valueOf(this.huesped_id) };
	}
}
