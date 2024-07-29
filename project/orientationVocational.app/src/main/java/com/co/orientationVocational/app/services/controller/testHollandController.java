package com.co.orientationVocational.app.services.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.business.test.testHolland;
import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.services.dto.hollandQuestionDto;
import com.co.orientationVocational.app.services.implementation.testService;
import com.co.orientationVocational.app.services.implementation.userServiceImplements;

@RestController
@RequestMapping("/test-Holland")
public class testHollandController {
	@Autowired
	userServiceImplements userServices;
	
	@Autowired
	testService testservice;
	
	@PostMapping("/result")
	public ResponseEntity<LinkedList<testModelResponse>> resultTest(@RequestBody hollandQuestionDto hollandQuestion) throws SQLException {		
		testHolland holland = new testHolland();
		
		LinkedList<testModelResponse>  responseTest = holland.resultTest(hollandQuestion.getTestQuestion());
		
		insertarRegistroTest(hollandQuestion.getIdentificacion(), responseTest);
    	
		return new ResponseEntity(responseTest, HttpStatus.OK);
	}
	
	private void insertarRegistroTest(String identificacion, LinkedList<testModelResponse> responseTest) throws SQLException {
		Map<String, Object> mapDatos = new HashMap<String, Object>();
		String resultado = "";
		String separador = "";
		int contador = 1;
		int temp = 1;
		
		mapDatos.put("stIdentificacionTest", identificacion);
		
		for (testModelResponse testModelResponse : responseTest) {
			resultado += separador + "resultado prueba " + temp + ": " + "Llave " + testModelResponse.getLlave() +
					" Resultado " + testModelResponse.getResultado() + " intereses " + testModelResponse.getAreaInteres();
			
			separador = " - ";
			temp++;
		}
		
		for (testModelResponse testModelResponse : responseTest) {
			String campoTemp = "";
			String campoMapa = "";
			
			campoTemp = testModelResponse.getLlave().toString() + "-" + testModelResponse.getResultado();
			
			campoMapa = "stDetalle" + contador;
			
			mapDatos.put(campoMapa, campoTemp);
			
			contador++;
		}
		
		if(!mapDatos.containsKey("stDetalle1")) {
			mapDatos.put("stDetalle1", "");
		}
		
		if(!mapDatos.containsKey("stDetalle2")) {
			mapDatos.put("stDetalle2", "");
		}
		
		if(!mapDatos.containsKey("stDetalle3")) {
			mapDatos.put("stDetalle3", "");
		}
		
		mapDatos.put("stResultado", resultado);
		mapDatos.put("stTipoTest", "Test Holland");
		
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		mapDatos.put("stFechaTest", dft.format(LocalDateTime.now()));
		
		if(responseTest.isEmpty()) {
			mapDatos.put("stObservacionTest", "El test fue realizado incorrectamente");
		}else {
			mapDatos.put("stObservacionTest", "El test fue realizado correctamente");			
		}
		
		testservice.inserRegistre(mapDatos);
	}
}
