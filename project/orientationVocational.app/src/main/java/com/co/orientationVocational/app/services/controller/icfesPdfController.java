package com.co.orientationVocational.app.services.controller;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.business.files.icfesPdf;
import com.co.orientationVocational.app.data.icfesUsuario;
import com.co.orientationVocational.app.services.dto.icfesPdfDto;
import com.co.orientationVocational.app.services.service.usuarioIcfesRepository;

@RestController
@RequestMapping("/icfes")
public class icfesPdfController extends utils {
	@Autowired
	usuarioIcfesRepository usuarioicfes;
	
	@PostMapping("/pdf")
	public ResponseEntity<icfesUsuario> resultTest(@RequestBody icfesPdfDto icfes) throws InvalidPasswordException, IOException {
		icfesPdf icfesPdf = new icfesPdf();
		boolean existeRegistro;
		int insert = 2;
		
		icfesUsuario responseTest = icfesPdf.gestionArchivo(icfes.getRuta(), icfes.getIdentificacion());
		
		if(responseTest.getRegistroExamen() != null) {
			existeRegistro = usuarioicfes.RegistroIcfes(responseTest.getRegistroExamen());
			
			if(existeRegistro == false) {
				insert = usuarioicfes.insertRegistre(responseTest);
			}else {
				insert = 0;
			}
		}
		
		if(insert == 1) {
			return new ResponseEntity(responseTest, HttpStatus.CREATED);
		}else if(insert == 0) {
			return new ResponseEntity(responseTest, HttpStatus.OK);
		}else {
			return new ResponseEntity(responseTest, HttpStatus.BAD_REQUEST);
		}
	}
}
