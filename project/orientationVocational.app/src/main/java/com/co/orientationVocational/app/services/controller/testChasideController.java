package com.co.orientationVocational.app.services.controller;

import java.util.LinkedList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.business.test.testChaside;
import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.services.dto.chasideQuestionDto;

@RestController
@RequestMapping("/test-chaside")
@CrossOrigin(origins = "*")
public class testChasideController extends utils {
	
	@PostMapping("/result")
	public ResponseEntity<LinkedList<testModelResponse>> resultTest(@RequestBody chasideQuestionDto chasideQuestion) {
		testChaside chaside = new testChaside();
		
		LinkedList<testModelResponse>  responseTest = chaside.resultTest(chasideQuestion.getTestQuestion());
		
		return new ResponseEntity(responseTest, HttpStatus.OK);
	}
}
