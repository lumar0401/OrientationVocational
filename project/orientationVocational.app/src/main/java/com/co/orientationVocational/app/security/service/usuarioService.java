package com.co.orientationVocational.app.security.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.data.infoTest;
import com.co.orientationVocational.app.security.entity.usuario;
import com.co.orientationVocational.app.security.repository.usuarioRepository;

@Service
@Transactional
public class usuarioService {
	@Autowired
	usuarioRepository usuariorepository;
	
	Connection connection;
	
	public usuarioService() throws SQLException {
		connection = dataBase.getConnection();
	}
	
	public List<usuario> List() {
		return usuariorepository.findAll();
	}
	
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
	
	public Optional<usuario> getOne(String identificacion) {
		return usuariorepository.findByIdentificacion(identificacion);
	}
	
	public void delete(String identificacion) {
		usuariorepository.deleteByIdentificacion(identificacion);
	}
}
