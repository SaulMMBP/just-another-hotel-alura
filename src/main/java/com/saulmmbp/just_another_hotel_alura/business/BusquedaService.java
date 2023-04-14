package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.dataaccess.*;
import com.saulmmbp.just_another_hotel_alura.model.Reserva;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class BusquedaService {

	public static List<HuespedDTO> getHuespedes() {
		List<HuespedDTO> huespedes = new ArrayList<>();
		try(Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			huespedes = huespedDao.findAll().stream().map(huesped -> new HuespedDTO(huesped)).toList();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}
	
	public static List<ReservaDTO> getReservas() {
		List<ReservaDTO> reservas = new ArrayList<>();
		try(Connection conn = MySqlConnection.getConnection()) {
			ReservaDao reservaDao = new ReservaDaoImpl(conn);
			reservas = reservaDao.findAll().stream().map(reserva -> new ReservaDTO(reserva)).toList();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		return reservas;
	}
	
	public static List<HuespedDTO> searchHuesped(String keyword) {
		List<HuespedDTO> huespedes = new ArrayList<>();
		try(Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			huespedes = huespedDao.findHuespedByNombreApellido(keyword).stream().map(huesped -> new HuespedDTO(huesped)).toList();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}
	
	public static List<ReservaDTO> searchReserva(String keyword) {
		Long idReserva = null;
		LocalDateTime fecha = null;
		List<ReservaDTO> reservas = new ArrayList<>();
		try(Connection conn = MySqlConnection.getConnection()) {
			ReservaDao reservaDao = new ReservaDaoImpl(conn);
			if (keyword.chars().allMatch(Character::isDigit)) {
				idReserva = Long.parseLong(keyword);
				Reserva r = reservaDao.findById(idReserva);
				if(r != null) {
					reservas.add(new ReservaDTO(r));
				}
			} else if (keyword.matches("\\d{2}/\\d{2}/\\d+")) {
				fecha = LocalDateTime.parse(keyword + " - 00:00:00", DateTimeFormatter.ofPattern("d/MM/yyyy - HH:mm:ss"));
				reservas = reservaDao.findByDate(fecha).stream().map(reserva -> new ReservaDTO(reserva)).toList();
			}
		} catch (SQLException | NumberFormatException | DateTimeParseException e) {
			JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		return reservas;
	}
	
	public List<HuespedDTO> getHuesped(Long id) {
		List<HuespedDTO> huespedes = new ArrayList<>();
		return huespedes;
	}
}
