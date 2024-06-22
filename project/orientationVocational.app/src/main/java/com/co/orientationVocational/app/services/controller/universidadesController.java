package com.co.orientationVocational.app.services.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.business.university.ApiUniversity;
import com.co.orientationVocational.app.data.respuestaUniversidades;
import com.co.orientationVocational.app.services.dto.universityDto;
import com.co.orientationVocational.app.services.implementation.testService;
import com.co.orientationVocational.app.services.implementation.userServiceImplements;

@RestController
@RequestMapping("/university")
public class universidadesController extends utils {
	private final static Logger logger = LoggerFactory.getLogger(universidadesController.class);
	
	@Autowired
	userServiceImplements userServices;
	
	@Autowired
	testService testservice;
	
	@PostMapping("/especiality")
	public ResponseEntity<respuestaUniversidades> resultTest(@RequestBody universityDto university) throws SQLException {		
		respuestaUniversidades universidades = new respuestaUniversidades();
				
    	try {
    		ApiUniversity Listauniversidades = new ApiUniversity();
    		
    		String ciudadUsuario = userServices.ciudadValidation(university.getIdentificacion());
    		String responseTest = university.getEspecialidad().toString();
    		String identificacion = university.getIdentificacion();
    		String test = university.getTest();

    		universidades = Listauniversidades.consultaUniversidades(ciudadUsuario, responseTest, identificacion, test);
    		
		} catch (Exception e) {
			logger.error("Error en la busquedad de datos (API University) Datos: " + e);
			
			respuestaUniversidades respuestaError = new respuestaUniversidades();
			
			DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			
			String pDato = dft.format(LocalDateTime.now());
			
			respuestaError.setRespuestaBackUp(testservice.consultarUltimoRegistro(pDato));
			
			if(!esVacio(respuestaError))
				respuestaError.setStatus(true);
			
			return new ResponseEntity(respuestaError, HttpStatus.OK);	
		}
		
		return new ResponseEntity(universidades, HttpStatus.OK);
	}
}
