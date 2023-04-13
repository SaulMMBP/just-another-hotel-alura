package com.saulmmbp.just_another_hotel_alura.controller;

import java.util.*;

import com.saulmmbp.just_another_hotel_alura.business.*;
import com.saulmmbp.just_another_hotel_alura.business.dto.*;

public class ServiceController {

	public static boolean login(String username, String password) {
		return LoginService.login(username, password);
	}

	public static List<HuespedDTO> getHuespedes() {
		return HuespedService.getHuespedes();
	}

	public static List<ReservaDTO> getReservas() {
		return ReservaService.getReservas();
	}

	public static List<HuespedDTO> searchHuesped(String search) {
		List<HuespedDTO> huespedes = new ArrayList<>();
		if (search.contains(":")) {
			String searchType = search.split(":")[0];
			String keyword = search.split(":")[1];
			if (searchType.equals("nombre")) {
				huespedes = HuespedService.getHuespedesByName(keyword);
			}
		} else if (!search.equals("Ingrese busqueda")) {
			huespedes = HuespedService.getHuespedesByName(search);
		} else {
			huespedes = HuespedService.getHuespedes();
		}
		return huespedes;
	}
}
