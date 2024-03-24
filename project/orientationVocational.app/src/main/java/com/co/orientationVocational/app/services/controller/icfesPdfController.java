package com.co.orientationVocational.app.services.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.co.orientationVocational.app.business.FileMessages;
import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.business.files.file;
import com.co.orientationVocational.app.business.files.icfesPdf;
import com.co.orientationVocational.app.data.icfesUsuario;
import com.co.orientationVocational.app.services.dto.icfesPdfDto;
import com.co.orientationVocational.app.services.implementation.fileService;
import com.co.orientationVocational.app.services.service.usuarioIcfesRepository;

@RestController
@RequestMapping("/icfes")
public class icfesPdfController extends utils {
	@Autowired
	usuarioIcfesRepository usuarioicfes;
	
	@Autowired
	fileService fileservice;
	
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
	
	@PostMapping("/upload")
	public ResponseEntity<FileMessages> uploadFiles(@RequestParam("file")MultipartFile[] files){
		String message = "";
		
		try {
			List<String> fileNames = new ArrayList<String>();
			
			Arrays.asList(files).stream().forEach(file -> {
				fileservice.save(file);
				fileNames.add(file.getOriginalFilename());
			});
			
			message = "Se subió el/los archivo(s) Correctamente";
			return ResponseEntity.status(HttpStatus.OK).body(new FileMessages(message));
		} catch (Exception e) {
			message = "Fallo al subir el archivo";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessages(message));
		}
	}
	
	@GetMapping("/files")
	public ResponseEntity<List<file>> getFiles(){
		List<file> fileInfos = fileservice.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder.fromMethodName(icfesPdfController.class, "getfile", 
					path.getFileName().toString()).build().toString();
			return new file(filename, url);
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}
	
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename){
		Resource file = fileservice.load(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\""+ file.getFilename() + "\"").body(file);
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
}
