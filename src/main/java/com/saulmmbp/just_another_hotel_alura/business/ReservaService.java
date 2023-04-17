package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;

import javax.swing.JOptionPane;

import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.dataaccess.*;
import com.saulmmbp.just_another_hotel_alura.model.*;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class ReservaService {
	
	public static HuespedDTO registrarReserva(HuespedDTO huespedDto, ReservaDTO reservaDto) {
		HuespedDTO data = null;
		try(Connection conn = MySqlConnection.getConnection()) {
			conn.setAutoCommit(false);
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
				
				huesped.addReserva(reserva);
				data = new HuespedDTO(huesped);
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Ups... El registro no se pudo completar",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ups... Hubo un problema con la conexión a la base de datos",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return data;
	}
	
}
