package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.dataaccess.*;
import com.saulmmbp.just_another_hotel_alura.model.*;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class ReservaService {
	
	public static Map<String, ? super Dto> registrarReserva(HuespedDTO huespedDto, ReservaDTO reservaDto) {
		Map<String, ? super Dto> data = new HashMap<>();
		try(Connection conn = MySqlConnection.getConnection()) {
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			try {
				ReservaDao reservaDao = new ReservaDaoImpl(conn);
				HuespedDao huespedDao = new HuespedDaoImpl(conn);
				Reserva reserva = new Reserva(reservaDto);
				Huesped huesped = new Huesped(huespedDto);
				
				Long id_huesped = huespedDao.save(huesped);
				huesped.setId(id_huesped);
				reserva.setHuesped_id(id_huesped);
				
				Long id_reserva = reservaDao.save(reserva);
				reserva.setId(id_reserva);
				
				data.put("huesped", new HuespedDTO(huesped));
				data.put("reserva", new ReservaDTO(reserva));
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos",
					"Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return data;
		
	}
	
}
