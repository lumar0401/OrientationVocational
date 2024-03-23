package com.co.orientationVocational.app.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.co.orientationVocational.app.business.Mensajes;
import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.services.dto.preguntaDto;
import com.co.orientationVocational.app.services.implementation.consultasDataBase;
import com.co.orientationVocational.app.services.implementation.preguntaService;
import com.co.orientationVocational.app.services.models.pregunta;

import org.springframework.web.bind.annotation.GetMapping;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/pregunta")
public class preguntaController extends utils {
	@Autowired
	consultasDataBase consulta;
	
	@Autowired
	preguntaService preguntaSer;
	
	@GetMapping("/lista")
	public ResponseEntity<List<pregunta>> list() {
		List<pregunta> list = preguntaSer.List();
		
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/lista-chaside/{tipoTest}")
	public ResponseEntity<List<pregunta>> ListaPreguntas(@PathVariable("tipoTest") String tipoTest) {
		List<pregunta> list = consulta.ListaPreguntas(tipoTest);
		
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<pregunta> getById(@PathVariable("id") int id){
		if(!preguntaSer.existsById(id))
			return new ResponseEntity(new Mensajes("Pregunta no existe"), HttpStatus.NOT_FOUND);
		
		pregunta preguntaEncontrada = preguntaSer.getOne(id).get();
		
		return new ResponseEntity(preguntaEncontrada, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detailsname/{nombre}")
	public ResponseEntity<pregunta> getByNombre(@PathVariable("nombre") String nombre){
		if(!preguntaSer.existsByDescripcionPregunta(nombre))
			return new ResponseEntity(new Mensajes("No Existe descripcion"), HttpStatus.NOT_FOUND);
		
		pregunta preguntaEncontrada = preguntaSer.getByNombre(nombre).get();
		
		return new ResponseEntity(preguntaEncontrada, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody preguntaDto pregunta){
		if(StringUtils.isBlank(pregunta.getDescripcionPregunta()))
			return new ResponseEntity(new Mensajes("la Descipcion es obligatoria"), HttpStatus.BAD_REQUEST);
		if(preguntaSer.existsByDescripcionPregunta(pregunta.getDescripcionPregunta()))
			return new ResponseEntity(new Mensajes("La pregunta ya existe"), HttpStatus.BAD_REQUEST);
		
		pregunta preguntaNew = new pregunta(pregunta.getDescripcionPregunta(), pregunta.getOpcion1(), pregunta.getOpcion2(),
				pregunta.getOpcion3(), pregunta.getOpcion4(), pregunta.getRespuesta1(), pregunta.getRespuesta2(),
				pregunta.getTipoTest(), pregunta.getOrdenPregunta());
		
		preguntaSer.save(preguntaNew);
		
		return new ResponseEntity(new Mensajes("Pregunta creada"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!preguntaSer.existsById(id))
            return new ResponseEntity(new Mensajes("Pregunta no existe"), HttpStatus.NOT_FOUND);
        
        preguntaSer.delete(id);
        
        return new ResponseEntity(new Mensajes("Pregunta Eliminada"), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<pregunta> update(@PathVariable("id") int id, @RequestBody preguntaDto question){
		if(!preguntaSer.existsById(id))
            return new ResponseEntity(new Mensajes("No existe"), HttpStatus.NOT_FOUND);
		
		if(preguntaSer.existsByDescripcionPregunta(question.getDescripcionPregunta()) && preguntaSer.getByNombre(question.getDescripcionPregunta()).get().getId() != id)
            return new ResponseEntity(new Mensajes("Pregunta ya existe"), HttpStatus.BAD_REQUEST);
		
		pregunta preguntaActualizada = preguntaSer.getOne(id).get();
		
		if(!esVacio(question.getDescripcionPregunta()) && !preguntaActualizada.getDescripcionPregunta().equals(question.getDescripcionPregunta())) {
			preguntaActualizada.setDescripcionPregunta(question.getDescripcionPregunta());
		}
		
		if(!esVacio(question.getOpcion1()) && !preguntaActualizada.getOpcion1().equals(question.getOpcion1())) {
			preguntaActualizada.setOpcion1(question.getOpcion1());
		}
		
		if(!esVacio(question.getOpcion2()) && !preguntaActualizada.getOpcion2().equals(question.getOpcion2())) {
			preguntaActualizada.setOpcion2(question.getOpcion2());
		}
		
		if(!esVacio(question.getOpcion3()) && !preguntaActualizada.getOpcion3().equals(question.getOpcion3())) {
			preguntaActualizada.setOpcion3(question.getOpcion3());
		}
		
		if(!esVacio(question.getOpcion4()) && !preguntaActualizada.getOpcion4().equals(question.getOpcion4())) {
			preguntaActualizada.setOpcion4(question.getOpcion4());
		}
		
		if(!esVacio(question.getRespuesta1()) && !preguntaActualizada.getRespuesta1().equals(question.getRespuesta1())) {
			preguntaActualizada.setRespuesta1(question.getRespuesta1());
		}
		
		if(!esVacio(question.getRespuesta2()) && !preguntaActualizada.getRespuesta2().equals(question.getRespuesta2())) {
			preguntaActualizada.setRespuesta2(question.getRespuesta2());
		}
		
		if(!esVacio(question.getTipoTest()) && !preguntaActualizada.getTipoTest().equals(question.getTipoTest())) {
			preguntaActualizada.setTipoTest(question.getTipoTest());
		}
		
		if(!esVacio(question.getOrdenPregunta()) && (preguntaActualizada.getOrdenPregunta() != question.getOrdenPregunta())) {
			preguntaActualizada.setOrdenPregunta(question.getOrdenPregunta());
		}
		
		preguntaSer.save(preguntaActualizada);
		
		return new ResponseEntity(new Mensajes("Pregunta actualizada"), HttpStatus.OK);
	}
}
