package com.co.orientationVocational.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.security.entity.rol;
import com.co.orientationVocational.app.security.enums.rolNombre;

@Repository
public interface rolRepository extends JpaRepository<rol, Integer>{
	Optional<rol> findByDescripcion(rolNombre rol);
}
