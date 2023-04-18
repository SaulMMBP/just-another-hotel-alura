package com.saulmmbp.just_another_hotel_alura.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import com.saulmmbp.just_another_hotel_alura.business.dto.HuespedDTO;

/**
 * @author SAUL
 *
 */
public class Huesped {

	private Long id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private Nacionalidad nacionalidad;
	private String telefono;
	private List<Reserva> reservas;

	public Huesped() {
		this.reservas = new ArrayList<>();
	}

	public Huesped(ResultSet rs) throws SQLException {
		this();
		this.id = rs.getLong("id_huesped");
		this.nombre = rs.getString("nombre");
		this.apellido = rs.getString("apellido");
		this.fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
		setNacionalidad(rs.getString("nacionalidad"));
		this.telefono = rs.getString("telefono");
	}

	public Huesped(HuespedDTO huespedDto) {
		this();
		this.id = huespedDto.id();
		this.nombre = huespedDto.nombre();
		this.apellido = huespedDto.apellido();
		this.fechaNacimiento = huespedDto.fechaNacimiento();
		this.nacionalidad = huespedDto.nacionalidad();
		this.telefono = huespedDto.telefono();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		int i = 0;
		Nacionalidad valorEncontrado = null;
		Nacionalidad[] values = Nacionalidad.values();
		while (i < values.length && !values[i].toString().equals(nacionalidad))
			i++;

		if (i != values.length && values[i].toString().equals(nacionalidad)) {
			valorEncontrado = values[i];
		}
		this.nacionalidad = valorEncontrado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
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
		Huesped other = (Huesped) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Huesped [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + "]";
	}

}
