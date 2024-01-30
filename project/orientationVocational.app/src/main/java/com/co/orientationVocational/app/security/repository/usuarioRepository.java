package com.co.orientationVocational.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.orientationVocational.app.security.entity.usuario;

public interface usuarioRepository extends JpaRepository<usuario, Integer> {
	Optional<usuario> findByIdentificacion(String identificacion);
	boolean existsByIdentificacion(String identificacion);
	boolean existsByEmail(String email);
}
