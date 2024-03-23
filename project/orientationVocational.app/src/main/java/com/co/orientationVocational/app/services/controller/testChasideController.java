package com.co.orientationVocational.app.services.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.co.orientationVocational.app.business.test.testChaside;
import com.co.orientationVocational.app.business.university.ApiUniversity;
import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.services.dto.chasideQuestionDto;
import com.co.orientationVocational.app.services.implementation.testService;
import com.co.orientationVocational.app.services.implementation.userServiceImplements;

@RestController
@RequestMapping("/test-chaside")
public class testChasideController extends utils {
	private final static Logger logger = LoggerFactory.getLogger(testChasideController.class);
	
	@Autowired
	userServiceImplements userServices;
	
	@Autowired
	testService testservice;
	
	@PostMapping("/result")
	public ResponseEntity<LinkedList<testModelResponse>> resultTest(@RequestBody chasideQuestionDto chasideQuestion) throws SQLException {		
		testChaside chaside = new testChaside();
		
		LinkedList<testModelResponse>  responseTest = chaside.resultTest(chasideQuestion.getTestQuestion());
		
		insertarRegistroTest(chasideQuestion.getIdentificacion(), responseTest);
		
    	try {
    		ApiUniversity Listauniversidades = new ApiUniversity();
    		
    		String ciudad = userServices.ciudadValidation(chasideQuestion.getIdentificacion());

    		List<String> universidades = Listauniversidades.consultaUniversidades(ciudad);
    		
    		
    		for (String string : universidades) {
				System.out.println(string);
			}
    		
		} catch (Exception e) {
			logger.error("Error en la busquedad de datos (API University)");
		}
		
		return new ResponseEntity(responseTest, HttpStatus.OK);
	}
	
	private void insertarRegistroTest(String identificacion, LinkedList<testModelResponse> responseTest) throws SQLException {
		Map<String, Object> mapDatos = new HashMap<String, Object>();
		String resultado = "";
		String separador = "";
		int temp = 1;
		
		mapDatos.put("stIdentificacionTest", identificacion);
		
		for (testModelResponse testModelResponse : responseTest) {
			resultado += separador + "resultado prueba " + temp + ": " + "Llave " + testModelResponse.getLlave() +
					" Resultado " + testModelResponse.getResultado() + " intereses " + testModelResponse.getAreaInteres();
			
			separador = " - ";
			temp++;
		}
		
		mapDatos.put("stResultado", resultado);
		mapDatos.put("stTipoTest", "Test Chaside");
		
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		mapDatos.put("stFechaTest", dft.format(LocalDateTime.now()));
		
		if(responseTest.isEmpty()) {
			mapDatos.put("stObservacionTest", "El test fue realizado incorrectamente");
		}else {
			mapDatos.put("stObservacionTest", "");			
		}
		
		testservice.inserRegistre(mapDatos);
	}
}
