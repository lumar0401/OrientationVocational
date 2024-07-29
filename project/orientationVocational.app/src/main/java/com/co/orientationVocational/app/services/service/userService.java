package com.co.orientationVocational.app.services.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.data.infoTest;

@Repository
public interface userService {
	public int loginValidation(String identificacion);
	public String ciudadValidation(String identificacion);
	int loginValidationCoordenadas(String identificacion);
	List<String> findByTest(String identificacion, String test);
}