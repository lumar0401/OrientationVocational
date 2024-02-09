package com.co.orientationVocational.app.services.service;

import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.data.icfesUsuario;

@Repository
public interface usuarioIcfesRepository{
	public int insertRegistre(icfesUsuario newUsuarioIcfes);
	
	public boolean RegistroIcfes(String registro);
}
