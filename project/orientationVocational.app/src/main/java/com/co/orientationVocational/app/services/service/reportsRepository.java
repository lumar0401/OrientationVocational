package com.co.orientationVocational.app.services.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.services.models.logUsuario;
import com.co.orientationVocational.app.services.models.pregunta;
import com.co.orientationVocational.app.services.models.universidades;

@Repository
public interface reportsRepository {
	public List<pregunta> listaPreguntas();
	public List<universidades> listaUniversidades();
	public List<logUsuario> listaLogsUsuarios();
}
