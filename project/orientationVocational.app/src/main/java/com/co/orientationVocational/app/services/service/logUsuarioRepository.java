package com.co.orientationVocational.app.services.service;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface logUsuarioRepository{
	public int insertRegistre(Map<String, Object> newRegistro);
	boolean existsByIdentificacion(String identificacion);
}
