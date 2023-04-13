package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.saulmmbp.just_another_hotel_alura.business.dto.HuespedDTO;
import com.saulmmbp.just_another_hotel_alura.dataaccess.*;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class HuespedService {

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
	
	public static List<HuespedDTO> getHuespedesByName(String huespedName) {
		List<HuespedDTO> huespedes = new ArrayList<>();
		try(Connection conn = MySqlConnection.getConnection()) {
			HuespedDao huespedDao = new HuespedDaoImpl(conn);
			huespedes = huespedDao.findHuespedByName(huespedName).stream().map(huesped -> new HuespedDTO(huesped)).toList();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		return huespedes;
	}
	
	public List<HuespedDTO> getHuesped(Long id) {
		List<HuespedDTO> huespedes = new ArrayList<>();
		return huespedes;
	}
}
