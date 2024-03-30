package com.co.orientationVocational.app.services.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface universityRepository {
	public List<String> pageUniversity(Map<String, String> mapDatos);
}
