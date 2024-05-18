package com.co.orientationVocational.app.services.service;

import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.data.modelUniversityPage;

@Repository
public interface universityRepository {
	public modelUniversityPage pageUniversity(String datos, String intereses, String test);
	public String obtainMetadatos(String datos);
}
