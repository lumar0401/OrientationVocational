package com.co.orientationVocational.app.services.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.services.models.pregunta;

@Repository
public interface consultasDataBaseRepository {
	public boolean cantidadDatos(String tabla);
	public int insertRegistre(pregunta newPregunta);
	List<pregunta> ListaPreguntas(String tipoTest);
}
