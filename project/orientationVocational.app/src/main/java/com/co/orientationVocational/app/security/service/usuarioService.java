package com.co.orientationVocational.app.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.orientationVocational.app.security.entity.usuario;
import com.co.orientationVocational.app.security.repository.usuarioRepository;

@Service
@Transactional
public class usuarioService {
	@Autowired
	usuarioRepository usuariorepository;
	
	public Optional<usuario> getByIdentificacion(String identificacion){
		return usuariorepository.findByIdentificacion(identificacion);
	}
	
	public boolean existsByIdentificacion(String identificacion) {
		return usuariorepository.existsByIdentificacion(identificacion);
	}
	
	public boolean existsByEmail(String email) {
		return usuariorepository.existsByEmail(email);
	}
	
	public void save(usuario usuario) {
		usuariorepository.save(usuario);
	}
}
