package com.co.orientationVocational.app.services.service;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.data.testModelResponse;

@Repository
public interface testRepository {
	public int inserRegistre(Map<String, Object> testNew);
	public LinkedList<testModelResponse> consultarUltimoRegistro(String fecha);
	public LinkedList<String> testPreviosUsuario(String identificacion);
}
