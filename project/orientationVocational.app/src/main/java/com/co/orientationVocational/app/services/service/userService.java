package com.co.orientationVocational.app.services.service;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface userService {
	public int loginValidation(String identificacion);
	public List<String> ciudadValidation(String identificacion);
}