package com.saulmmbp.just_another_hotel_alura.model;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

import com.saulmmbp.just_another_hotel_alura.business.dto.ReservaDTO;

public class Reserva {

	private Long id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private BigDecimal valor;
	private String formaPago;
	private Huesped huesped;

	public Reserva() {
		this.huesped = new Huesped();
	}

	public Reserva(ResultSet rs) throws SQLException {
		this();
		this.id = rs.getLong("id_reserva");
		this.fechaEntrada = rs.getDate("fecha_entrada").toLocalDate();
		this.fechaSalida = rs.getDate("fecha_salida").toLocalDate();
		this.valor = new BigDecimal(rs.getString("valor"));
		this.formaPago = rs.getString("forma_pago");
	}

	public Reserva(ReservaDTO reservaDto) {
		this();
		this.id = reservaDto.id();
		this.fechaEntrada = reservaDto.fechaEntrada();
		this.fechaSalida = reservaDto.fechaSalida();
		this.valor = reservaDto.valor();
		this.formaPago = reservaDto.formaPago();
		this.huesped = reservaDto.huesped();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", valor="
				+ valor + ", formaPago=" + formaPago + "huesped_id" + huesped.getId() + "]";
	}

}
