package com.co.orientationVocational.app.services.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.services.models.pregunta;

@Repository
public interface preguntaRepository extends JpaRepository<pregunta, Integer>{
	Optional<pregunta> findByDescripcionPregunta(String nombre);
	boolean existsByDescripcionPregunta(String descripcion);
}
