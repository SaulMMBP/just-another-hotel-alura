package com.saulmmbp.just_another_hotel_alura.model;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;

import com.saulmmbp.just_another_hotel_alura.business.dto.ReservaDTO;

public class Reserva {

	private Long id;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
	private BigDecimal valor;
	private String formaPago;
	private Long huesped_id;
	private Huesped huesped;

	public Reserva() {
	}

	public Reserva(ResultSet rs) throws SQLException {
		this.id = rs.getLong("id_reserva");
		this.fechaEntrada = rs.getTimestamp("fecha_entrada").toLocalDateTime();
		this.fechaSalida = rs.getTimestamp("fecha_salida").toLocalDateTime();
		this.valor = new BigDecimal(rs.getString("valor"));
		this.formaPago = rs.getString("forma_pago");
		this.huesped_id = rs.getLong("huesped_id");
	}

	public Reserva(ReservaDTO reservaDto) {
		this.fechaEntrada = reservaDto.fechaEntrada();
		this.fechaSalida = reservaDto.fechaSalida();
		this.valor = reservaDto.valor();
		this.formaPago = reservaDto.formaPago();
		this.huesped_id = reservaDto.huesped_id();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
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

	public Long getHuesped_id() {
		return huesped_id;
	}

	public void setHuesped_id(Long huesped_id) {
		this.huesped_id = huesped_id;
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
				+ valor + ", formaPago=" + formaPago + "huesped_id" + huesped_id + "]";
	}

}
