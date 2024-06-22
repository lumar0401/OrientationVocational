package com.co.orientationVocational.app.services.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.co.orientationVocational.app.security.entity.usuario;

@Repository
public interface logUsuarioRepository{
	public int insertRegistre(Map<String, Object> newRegistro);
	boolean existsByIdentificacion(String identificacion);
	public List<usuario> listaUsuarios();
}
