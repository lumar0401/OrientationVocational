package com.co.orientationVocational.app.services.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.co.orientationVocational.app.business.FileMessages;
import com.co.orientationVocational.app.business.Mensajes;
import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.business.files.icfesPdf;
import com.co.orientationVocational.app.data.icfesUsuario;
import com.co.orientationVocational.app.security.service.usuarioService;
import com.co.orientationVocational.app.services.dto.icfesDto;
import com.co.orientationVocational.app.services.implementation.fileService;
import com.co.orientationVocational.app.services.service.usuarioIcfesRepository;

@RestController
@RequestMapping("/icfes")
public class icfesPdfController extends utils {
	private final static Logger logger = LoggerFactory.getLogger(icfesPdfController.class);
	
	@Autowired
	usuarioIcfesRepository usuarioicfes;
	
	@Autowired
	fileService fileservice;
	
	@Autowired
    usuarioService usuarioservice;
	
	@PostMapping("/upload-pdf")
	public ResponseEntity<icfesUsuario> uploadPdf(@RequestParam("file") MultipartFile file, @RequestParam("identificacion") String identificacion) {
		icfesUsuario responseTest = new icfesUsuario();
		icfesPdf icfesPdf = new icfesPdf();
		boolean existeRegistro;
		int insert = 2;
		
		try {
			String folder = "uploads";
			String currentDirectory = System.getProperty("user.dir");
			Path directoryPath = Paths.get(currentDirectory, folder);

			if (!Files.exists(directoryPath)) {
				Files.createDirectories(directoryPath);
			}
			
	        try {
	        	Path destinationPath = directoryPath.resolve(file.getOriginalFilename());
		        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
		        
		        responseTest = icfesPdf.gestionArchivo(destinationPath.toString(), identificacion);
		        
		        if(responseTest.getRegistroExamen() != null) {
					existeRegistro = usuarioicfes.RegistroIcfes(responseTest.getRegistroExamen());
					
					if(existeRegistro == false) {
						insert = usuarioicfes.insertRegistre(responseTest);
					}else {
						insert = 0;
					}
				}
		        
		        Files.delete(destinationPath);
				
				if(insert == 1) {
					return new ResponseEntity(responseTest, HttpStatus.CREATED);
				}else if(insert == 0) {
					return new ResponseEntity(responseTest, HttpStatus.OK);
				}else {
					return new ResponseEntity(responseTest, HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				logger.error("Error guardando el archivo: " + e.getMessage());
                return new ResponseEntity<>(responseTest, HttpStatus.BAD_REQUEST);
			}
		} catch (IOException e) {
			logger.error("Error al cargar el archivo, Datos: " + e.getMessage());
			return new ResponseEntity(responseTest, HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error("Error desconocido, Datos: " + ex.getMessage());
			return new ResponseEntity(responseTest, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/delete/{filename:.+}")
	public ResponseEntity<FileMessages> deleteFile(@PathVariable String filename){
		String message = "";
		
		try {
			message = fileservice.deleteFile(filename);
			return ResponseEntity.status(HttpStatus.OK).body(new FileMessages(message));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessages(message));
		}
	}
	
	@PostMapping("/icfes-usuarios/{id}")
	public ResponseEntity<List<icfesDto>> icfesUsuarios(@PathVariable("id") String identificacion){
		if(!usuarioservice.existsByIdentificacion(identificacion))
			return new ResponseEntity(new Mensajes("Usuario no existe"), HttpStatus.NOT_FOUND);
		
		try {
			List<icfesDto> icfes = usuarioicfes.icfesxUsuario(identificacion);
			
			return new ResponseEntity(icfes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new Mensajes("Contraseña Incorrecta"), HttpStatus.NOT_FOUND);
		}
	}
}
