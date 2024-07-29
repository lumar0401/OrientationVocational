package com.co.orientationVocational.app.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.services.service.userService;

@RestController
public class userController {
	
	@Autowired
	private userService userservice;
	
	@GetMapping("usuarios/{identificacion}")
	public int userLogin(@PathVariable("identificacion") String identificacionI) {
		int ingresoExitoso = userservice.loginValidation(identificacionI);
		
		if(ingresoExitoso == 0) {
			return 0;
		}
		
		return ingresoExitoso;
	}
}
