package com.co.orientationVocational.app.services.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.services.implementation.logUsuarioService;
import com.co.orientationVocational.app.services.implementation.reportsService;

@RestController
@RequestMapping("/reportes")
public class informesController {
	@Autowired
	logUsuarioService logUsuario;

	@Autowired
	reportsService reportService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/informes/{informe}")
	public ResponseEntity<InputStreamResource> exportAllData(@PathVariable("informe") String tipoInforme)
			throws Exception {
		HttpHeaders header = new HttpHeaders();

		String nombreArchivo = "";

		if (tipoInforme.equalsIgnoreCase("usuarios")) {
			ByteArrayInputStream stream = logUsuario.exportAllData(tipoInforme);

			nombreArchivo = "attachment; filename=usuarios.xls";

			header.add("Content-Disposition", nombreArchivo);

			return ResponseEntity.ok().headers(header).body(new InputStreamResource(stream));
		} else if (tipoInforme.equalsIgnoreCase("preguntas")) {
			ByteArrayInputStream stream = reportService.exportAllData(tipoInforme);

			nombreArchivo = "attachment; filename=preguntas.xls";

			header.add("Content-Disposition", nombreArchivo);

			return ResponseEntity.ok().headers(header).body(new InputStreamResource(stream));
		} else if (tipoInforme.equalsIgnoreCase("universidades")) {
			ByteArrayInputStream stream = reportService.exportAllData(tipoInforme);

			nombreArchivo = "attachment; filename=universidades.xls";

			header.add("Content-Disposition", nombreArchivo);

			return ResponseEntity.ok().headers(header).body(new InputStreamResource(stream));
		} else if (tipoInforme.equalsIgnoreCase("logs")) {
			ByteArrayInputStream stream = reportService.exportAllData(tipoInforme);

			nombreArchivo = "attachment; filename=logs.xls";

			header.add("Content-Disposition", nombreArchivo);

			return ResponseEntity.ok().headers(header).body(new InputStreamResource(stream));
		}

		return null;
	}
	@GetMapping("/resultado-usuario/{identificacion}")
	public ResponseEntity<InputStreamResource> exportPDF(@PathVariable("identificacion") String identificacion) throws Exception {
	    HttpHeaders header = new HttpHeaders();
	    String nombreArchivo = identificacion + ".pdf";

	    ByteArrayInputStream stream = reportService.exportAllDataPDF(identificacion);

	    if (stream.available() == 0) {
	        throw new Exception("El PDF generado está vacío.");
	    }

	    header.add("Content-Disposition", "attachment; filename=" + nombreArchivo);
	    header.add("Content-Type", "application/pdf");

	    return ResponseEntity.ok().headers(header).body(new InputStreamResource(stream));
	}
}