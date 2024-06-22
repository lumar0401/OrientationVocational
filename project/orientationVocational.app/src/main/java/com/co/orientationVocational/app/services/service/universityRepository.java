package com.co.orientationVocational.app.services.service;

import java.util.LinkedList;

import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.data.modelUniversityPage;
import com.co.orientationVocational.app.data.responseCardUniversity;

@Repository
public interface universityRepository {
	public modelUniversityPage pageUniversity(String datos, String intereses, String test);
	public LinkedList<responseCardUniversity> informacionUniversidad(String idUniversidad, String programa);
	public String obtainMetadatos(String datos);
}
