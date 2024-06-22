package com.co.orientationVocational.app.services.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.services.implementation.logUsuarioService;

@RestController
@RequestMapping("/reportes")
public class informesController {
	@Autowired
    logUsuarioService logUsuario;
	
	@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios")
    public ResponseEntity<InputStreamResource> exportAllData() throws Exception{
    	ByteArrayInputStream stream = logUsuario.exportAllData();
    	
    	HttpHeaders header = new HttpHeaders();
    	header.add("Content-Disposition", "attachment; filename=usuarios.xls");
    	
    	return ResponseEntity.ok().headers(header).body(new InputStreamResource(stream));
    }
}