package com.saulmmbp.just_another_hotel_alura.business;

import java.sql.*;
import java.util.*;

import com.saulmmbp.just_another_hotel_alura.business.dto.HuespedDTO;
import com.saulmmbp.just_another_hotel_alura.dataaccess.HuespedDaoImpl;
import com.saulmmbp.just_another_hotel_alura.util.MySqlConnection;

public class HuespedService {

	private Connection conn;
	private HuespedDaoImpl huespedDao;

	public HuespedService() {
		try {
			this.conn = MySqlConnection.getConnection();
			this.huespedDao = new HuespedDaoImpl(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<HuespedDTO> getHuespedList() {
		List<HuespedDTO> huespedes = new ArrayList<>();
		try {
			huespedes = this.huespedDao.findAll().stream().map(huesped -> new HuespedDTO(huesped)).toList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return huespedes;
	}
}
