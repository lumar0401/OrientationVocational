package com.co.orientationVocational.app.services.service;

import org.springframework.stereotype.Repository;

@Repository
public interface userService {
	
	public int loginValidation(String identificacion);
}
