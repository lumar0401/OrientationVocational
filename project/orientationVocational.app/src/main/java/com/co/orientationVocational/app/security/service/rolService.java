package com.co.orientationVocational.app.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.orientationVocational.app.security.entity.rol;
import com.co.orientationVocational.app.security.enums.rolNombre;
import com.co.orientationVocational.app.security.repository.rolRepository;

@Service
@Transactional
public class rolService {
	@Autowired
	rolRepository rolrepository;
	
	public Optional<rol> getByNombre(rolNombre rolNombre){
		return rolrepository.findByDescripcion(rolNombre);
	}
}
