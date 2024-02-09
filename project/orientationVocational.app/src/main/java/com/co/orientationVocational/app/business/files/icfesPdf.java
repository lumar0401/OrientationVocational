package com.co.orientationVocational.app.business.files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.data.evidenceModel;
import com.co.orientationVocational.app.data.icfesUsuario;

public class icfesPdf extends utils {

	public icfesPdf() {}

	public icfesUsuario gestionArchivo(String rutaArchivo, String identificacion) throws InvalidPasswordException, IOException {
		LinkedList<evidenceModel> listPrueba = new LinkedList<evidenceModel>();
		icfesUsuario result = new icfesUsuario();
		boolean existe = existFile(rutaArchivo);

		if (existe && !esVacio(identificacion)) {
			try {
				obtenerInfoPdf(rutaArchivo, identificacion, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private boolean existFile(String rutaArchivo) {
		File archivoPDF = new File(rutaArchivo);

		if (archivoPDF.exists()) {
			return true;
		} else {
			return false;
		}
	}

	private static void obtenerInfoPdf(String ruta, String identificacion, icfesUsuario result)
			throws InvalidPasswordException, IOException {
		PDDocument documento = PDDocument.load(new File(ruta));
		String[] lineasXPage = new String[] {};
		String[] arregloXLineas = new String[7];

		int numeroPagina;
		int posicion;

		try {
			PDFTextStripper stripper = new PDFTextStripper();

			numeroPagina = documento.getNumberOfPages();

			stripper.setStartPage(numeroPagina);
			stripper.setEndPage(numeroPagina);

			String texto = stripper.getText(documento).toString();

			posicion = texto.indexOf(identificacion);

			if (posicion != -1) {
				lineasXPage = obtenerDatosPdf(texto);

				obtenerInfoAdicional(identificacion, lineasXPage, result);
				
				obtenerLineas(lineasXPage, arregloXLineas);

				obtenerResultados(arregloXLineas, result);
			}

			documento.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (documento != null) {
				try {
					documento.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String[] obtenerDatosPdf(String texto) {
		String[] lineaPage = new String[] {};

		lineaPage = texto.split("\\r?\\n");

		return lineaPage;
	}

	private static void obtenerInfoAdicional(String identificacion, String[] lineasXPage, icfesUsuario result) {
		String[] arregloInfo = new String[] {};
		int contador = 0;
		
		for (String string : lineasXPage) {
			if (string.contains("Fecha de Examen")) {
				arregloInfo = string.split(" ");
				result.setFechaExamen(arregloInfo[3] + " " + arregloInfo[4] + " " + arregloInfo[5] + " " + arregloInfo[6] + " " + arregloInfo[7]);
				Arrays.fill(arregloInfo, "");
			} else if (string.contains(identificacion)){
				arregloInfo = string.split(" ");
				result.setRegistroExamen(arregloInfo[0]);				
				obtenerNombre(arregloInfo, result);
				result.setIdentificacionUsuario(arregloInfo[(arregloInfo.length - 1)]);
				Arrays.fill(arregloInfo, "");
			} else if(string.contains("Puntaje")) {
				contador = 1;
				continue;
			} else if(contador == 1) {
				arregloInfo = string.split(" ");
				
				result.setPuntajeGlobal(Integer.valueOf(arregloInfo[1]));
				
				break;
			}
		}
	}
	
	private static void obtenerNombre(String[] arregloInfo, icfesUsuario result) {
		String nombre = "";
		
		for (int i = 1; i < (arregloInfo.length - 2); i++) {
			nombre = nombre + " " + arregloInfo[i];
		}
		
		result.setNombreEstudiante(nombre);
	}

	private static void obtenerLineas(String[] texto, String[] arregloXLineas) {
		Arrays.fill(arregloXLineas, "");
		String temp = "";

		for (String string : texto) {
			if (string.contains("LECTURA")) {
				temp = string;
				arregloXLineas[0] = temp;
				temp = "";
			} else if (string.contains("MATEMÁTICAS")) {
				temp = string;
				arregloXLineas[1] = temp;
				temp = "";
			} else if (string.contains("SOCIALES")) {
				temp = string;
				arregloXLineas[2] = temp;
				temp = "";
			} else if (string.contains("CIENCIAS")) {
				temp = string;
				arregloXLineas[3] = temp;
				temp = "";
			} else if (string.contains("INGLÉS")) {
				temp = string;
				arregloXLineas[4] = temp;
				temp = "";
			} else if (string.contains("RAZONAMIENTO")) {
				temp = string;
				arregloXLineas[5] = temp;
				temp = "";
			} else if (string.contains("COMPETENCIAS")) {
				temp = string;
				arregloXLineas[6] = temp;
				temp = "";
			}
		}
	}

	private static void obtenerResultados(String[] arregloXLineas, icfesUsuario result) {
		evidenceModel[] evidenceModels = new evidenceModel[arregloXLineas.length];
		LinkedList<evidenceModel> listPruebas = new LinkedList<evidenceModel>();
		String[] arregloTemp = new String[] {};

		for (int i = 0; i < arregloXLineas.length; i++) {
			arregloTemp = arregloXLineas[i].split(" ");

			evidenceModels[i] = new evidenceModel();

			asignaturas asignaturaTemp = asignaturas.valueOf(arregloTemp[0]);
			
			if(asignaturas.LECTURA.equals(asignaturaTemp)) {
				evidenceModels[i].setPrueba(arregloTemp[0] + " " + arregloTemp[1]);
				evidenceModels[i].setPuntaje(arregloTemp[2]);
				evidenceModels[i].setNivel("");
				evidenceModels[i].setDecil((arregloTemp[3] != null) ? arregloTemp[3] : "");
			}else if(asignaturas.MATEMÁTICAS.equals(asignaturaTemp)) {
				evidenceModels[i].setPrueba(arregloTemp[0]);
				evidenceModels[i].setPuntaje(arregloTemp[1]);
				evidenceModels[i].setNivel("");
				evidenceModels[i].setDecil((arregloTemp[2] != null) ? arregloTemp[2] : "");
			}else if(asignaturas.SOCIALES.equals(asignaturaTemp)) {
				evidenceModels[i].setPrueba(arregloTemp[0] + " " + arregloTemp[1] + " " + arregloTemp[2]);
				evidenceModels[i].setPuntaje(arregloTemp[3]);
				evidenceModels[i].setNivel("");
				evidenceModels[i].setDecil((arregloTemp[4] != null) ? arregloTemp[4] : "");
			} else if(asignaturas.CIENCIAS.equals(asignaturaTemp)) {
				evidenceModels[i].setPrueba(arregloTemp[0] + " " + arregloTemp[1]);
				evidenceModels[i].setPuntaje(arregloTemp[2]);
				evidenceModels[i].setNivel("");
				evidenceModels[i].setDecil((arregloTemp[3] != null) ? arregloTemp[3] : "");
			} else if(asignaturas.INGLÉS.equals(asignaturaTemp)) {
				evidenceModels[i].setPrueba(arregloTemp[0]);
				evidenceModels[i].setPuntaje(arregloTemp[1]);
				evidenceModels[i].setNivel((arregloTemp[2] != null) ? arregloTemp[2] : "");
				evidenceModels[i].setDecil(arregloTemp[3].toString());
			} else if(asignaturas.RAZONAMIENTO.equals(asignaturaTemp)) {
				evidenceModels[i].setPrueba(arregloTemp[0] + " " + arregloTemp[1]);
				evidenceModels[i].setPuntaje(arregloTemp[2]);
				evidenceModels[i].setNivel("");
				evidenceModels[i].setDecil((arregloTemp[3] != null) ? arregloTemp[3] : "");
			} else if(asignaturas.COMPETENCIAS.equals(asignaturaTemp)) {
				evidenceModels[i].setPrueba(arregloTemp[0] + " " + arregloTemp[1]);
				evidenceModels[i].setPuntaje(arregloTemp[2]);
				evidenceModels[i].setNivel("");
				evidenceModels[i].setDecil((arregloTemp[3] != null) ? arregloTemp[3] : "");
			}  
			
			listPruebas.add(evidenceModels[i]);
		}
		
		result.setListaMaterias(listPruebas);
	}
	
	public enum asignaturas {
		LECTURA, MATEMÁTICAS, SOCIALES, CIENCIAS, INGLÉS, RAZONAMIENTO, COMPETENCIAS
	}
}
