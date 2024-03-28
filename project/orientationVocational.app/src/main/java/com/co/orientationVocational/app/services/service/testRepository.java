package com.co.orientationVocational.app.services.service;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface testRepository {
	public int inserRegistre(Map<String, Object> testNew);
}
