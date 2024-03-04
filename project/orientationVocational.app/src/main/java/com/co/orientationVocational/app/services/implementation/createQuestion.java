package com.co.orientationVocational.app.services.implementation;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.co.orientationVocational.app.business.files.ProtegerArchivo;
import com.co.orientationVocational.app.data.icfesUsuario;

@Component
public class createQuestion implements CommandLineRunner {
	@Autowired
	preguntaService preguntaS;
	
	@Override
	public void run(String... args) throws Exception {
		String testChaside = "TestChaside.txt";
		File rutaArchivo = new File(testChaside);

        String rutaAbsoluta = rutaArchivo.getAbsolutePath();
        
        boolean archivo = existFile(rutaAbsoluta);
                
        if(archivo == true) {
        	//obtenerInfoArchivo(rutaArchivo.toString());
        	System.out.println(archivo + rutaAbsoluta);
        	ProtegerArchivo.cifrarArchivo(rutaAbsoluta.toString(), rutaAbsoluta.toString());
        }
	}
	
	private boolean existFile(String rutarelativa) {
		File archivoPDF = new File(rutarelativa);
		System.out.println(archivoPDF);

		if (archivoPDF.exists()) {
			return true;
		} else {
			return false;
		}
	}
//	
//	private static void obtenerInfoArchivo(String ruta)
//			throws InvalidPasswordException, IOException {
//		PDDocument documento = PDDocument.load(new File(ruta));
//		String[] lineasXPage = new String[] {};
//		String[] arregloXLineas = new String[7];
//
//		int numeroPagina;
//		int posicion;
//
//		try {
//			PDFTextStripper stripper = new PDFTextStripper();
//
//			numeroPagina = documento.getNumberOfPages();
//
//			stripper.setStartPage(numeroPagina);
//			stripper.setEndPage(numeroPagina);
//
//			String texto = stripper.getText(documento).toString();
//
//			posicion = texto.indexOf(identificacion);
//
//			if (posicion != -1) {
//				lineasXPage = obtenerDatosPdf(texto);
//
//				obtenerInfoAdicional(identificacion, lineasXPage, result);
//				
//				obtenerLineas(lineasXPage, arregloXLineas);
//
//				obtenerResultados(arregloXLineas, result);
//			}
//
//			documento.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (documento != null) {
//				try {
//					documento.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
}
