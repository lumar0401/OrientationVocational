package com.co.orientationVocational.app.services.controller;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.business.files.icfesPdf;
import com.co.orientationVocational.app.data.evidenceModel;
import com.co.orientationVocational.app.services.dto.icfesPdfDto;

@RestController
@RequestMapping("/icfes")
public class icfesPdfController {
	
	@PostMapping("/pdf")
	public ResponseEntity<LinkedList<evidenceModel>> resultTest(@RequestBody icfesPdfDto icfes) throws InvalidPasswordException, IOException {
		icfesPdf icfesPdf = new icfesPdf();
		
		LinkedList<evidenceModel>  responseTest = icfesPdf.gestionArchivo(icfes.getRuta(), icfes.getIdentificacion());
		
		return new ResponseEntity(responseTest, HttpStatus.OK);
	}
}
