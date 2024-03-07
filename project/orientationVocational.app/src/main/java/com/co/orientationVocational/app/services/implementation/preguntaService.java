package com.co.orientationVocational.app.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.orientationVocational.app.services.models.pregunta;
import com.co.orientationVocational.app.services.service.preguntaRepository;

@Service
@Transactional
public class preguntaService {
	
	@Autowired
	preguntaRepository preguntaRepo;
	
	public List<pregunta> List() {
		return preguntaRepo.findAll();
	}
	
	public Optional<pregunta> getByNombre(String nombre){
        return preguntaRepo.findByDescripcionPregunta(nombre);
    }
	
	public Optional<pregunta> getOne(int id) {
		return preguntaRepo.findById(id);
	}
	
	public void save(pregunta preguntaNew) {
		preguntaRepo.save(preguntaNew);
	}
	
	public void saveList(List<pregunta> preguntaNew) {
		preguntaRepo.saveAll(preguntaNew);
	}
	
	public void delete(int id) {
		preguntaRepo.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return preguntaRepo.existsById(id);
	}
	
	public boolean existsByDescripcionPregunta(String descripcion) {
		return preguntaRepo.existsByDescripcionPregunta(descripcion);
	}
}
