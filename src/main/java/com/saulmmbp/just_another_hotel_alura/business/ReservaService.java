package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.saulmmbp.just_another_hotel_alura.business.dto.ReservaDTO;
import com.saulmmbp.just_another_hotel_alura.dataaccess.*;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class ReservaService {

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
}
