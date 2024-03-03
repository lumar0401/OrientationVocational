package com.co.orientationVocational.app.services.controller;

import java.util.LinkedList;
import java.util.List;

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
import com.co.orientationVocational.app.services.implementation.userServiceImplements;

@RestController
@RequestMapping("/test-chaside")
public class testChasideController extends utils {
	private final static Logger logger = LoggerFactory.getLogger(testChasideController.class);
	
	@Autowired
	userServiceImplements userServices;
	
	@PostMapping("/result")
	public ResponseEntity<LinkedList<testModelResponse>> resultTest(@RequestBody chasideQuestionDto chasideQuestion) {		
		testChaside chaside = new testChaside();
		
		LinkedList<testModelResponse>  responseTest = chaside.resultTest(chasideQuestion.getTestQuestion());
		
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
}
