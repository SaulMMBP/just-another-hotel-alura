package com.saulmmbp.just_another_hotel_alura.businessTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.saulmmbp.just_another_hotel_alura.business.LoginService;

class LoginTest {

	@Test
	void testLogin() {
		LoginService loginService = new LoginService();
		boolean login = loginService.login("admin", "1234");
		assertTrue(login);
	}

}
