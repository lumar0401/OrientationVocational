package com.co.orientationVocational.app.services.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.data.icfesUsuario;
import com.co.orientationVocational.app.services.dto.icfesDto;

@Repository
public interface usuarioIcfesRepository{
	public int insertRegistre(icfesUsuario newUsuarioIcfes);
	public boolean RegistroIcfes(String registro);
	List<icfesDto> icfesxUsuario(String identificacion);
}
