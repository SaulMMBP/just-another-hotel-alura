package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

import javax.swing.JOptionPane;

import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.dataaccess.*;
import com.saulmmbp.just_another_hotel_alura.model.*;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class BusquedaService {

	public static List<HuespedDTO> getHuespedes() {
		List<HuespedDTO> huespedes = new ArrayList<>();
		try (Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			huespedes = huespedDao.findAll().stream().map(huesped -> new HuespedDTO(huesped)).toList();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}

	public static List<ReservaDTO> getReservas() {
		List<ReservaDTO> reservas = new ArrayList<>();
		try (Connection conn = MySqlConnection.getConnection()) {
			ReservaDao reservaDao = new ReservaDaoImpl(conn);
			reservas = reservaDao.findAll().stream().map(reserva -> new ReservaDTO(reserva)).toList();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return reservas;
	}

	public static List<HuespedDTO> searchHuesped(String keyword) {
		List<HuespedDTO> huespedes = new ArrayList<>();
		try (Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			huespedes = huespedDao.findHuespedByNombreApellido(keyword).stream().map(huesped -> new HuespedDTO(huesped))
					.toList();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}
	
	public static List<HuespedDTO> searchHuesped(Long idHuesped) {
		List<HuespedDTO> huespedes = new ArrayList<>();
		try (Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			Huesped h = huespedDao.findById(idHuesped);
			if(h != null) {
				huespedes.add(new HuespedDTO(h));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}

	public static List<ReservaDTO> searchReserva(Long idReserva) {
		List<ReservaDTO> reservas = new ArrayList<>();
		try (Connection conn = MySqlConnection.getConnection()) {
			ReservaDao reservaDao = new ReservaDaoImpl(conn);
			Reserva r = reservaDao.findById(idReserva);
			if (r != null) {
				reservas.add(new ReservaDTO(r));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return reservas;
	}

	public static List<ReservaDTO> searchReserva(LocalDateTime fechaEntrada) {
		List<ReservaDTO> reservas = new ArrayList<>();
		try (Connection conn = MySqlConnection.getConnection()) {
			ReservaDao reservaDao = new ReservaDaoImpl(conn);
			reservas = reservaDao.findByDate(fechaEntrada).stream().map(reserva -> new ReservaDTO(reserva)).toList();
		} catch (SQLException | NumberFormatException | DateTimeParseException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return reservas;
	}

	public static HuespedDTO getHuesped(Long id) {
		HuespedDTO huespedDto = null;
		try(Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			Huesped huesped = huespedDao.findById(id);
			if(huesped != null) {
				huespedDto = new HuespedDTO(huesped);
			} else {
				JOptionPane.showMessageDialog(null, "Este huesped no tienen ninguna reservación registrada",
						"Info", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return huespedDto;
	}
	
	public static ReservaDTO getReserva(Long id) {
		ReservaDTO reservaDto = null;
		try(Connection conn = MySqlConnection.getConnection()) {
			ReservaDao reservaDao = new ReservaDaoImpl(conn);
			Reserva reserva = reservaDao.findById(id);
			reservaDto = new ReservaDTO(reserva);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return reservaDto;
	}
	
	public static Long editHuesped(HuespedDTO huespedDto) {
		Long affectedRows = 0L;
		try(Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			affectedRows = huespedDao.save(new Huesped(huespedDto));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return affectedRows;
	}
	
	public static Long editReserva(ReservaDTO reservaDto) {
		Long affectedRows = 0L;
		try(Connection conn = MySqlConnection.getConnection()) {
			ReservaDao reservaDao = new ReservaDaoImpl(conn);
			affectedRows = reservaDao.save(new Reserva(reservaDto));
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return affectedRows;
	}
	
	public static int deleteHuesped(Long id) {
		int affectedRows = 0;
		try(Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			affectedRows = huespedDao.deleteById(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return affectedRows;
	}
}
