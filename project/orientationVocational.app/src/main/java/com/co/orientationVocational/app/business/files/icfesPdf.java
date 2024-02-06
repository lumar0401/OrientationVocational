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

public class icfesPdf extends utils {

	private static String asignaturaTemp;

	public icfesPdf() {
	}

	public LinkedList<evidenceModel> gestionArchivo(String rutaArchivo, String identificacion)
			throws InvalidPasswordException, IOException {
		LinkedList<evidenceModel> listPrueba = new LinkedList<evidenceModel>();
		boolean existe = existFile(rutaArchivo);

		if (existe && !esVacio(identificacion)) {
			try {
				obtenerInfoPdf(rutaArchivo, identificacion, listPrueba);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listPrueba;
	}

	private boolean existFile(String rutaArchivo) {
		File archivoPDF = new File(rutaArchivo);

		if (archivoPDF.exists()) {
			return true;
		} else {
			return false;
		}
	}

	private static void obtenerInfoPdf(String ruta, String identificacion, LinkedList<evidenceModel> listPruebas)
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

				obtenerLineas(lineasXPage, arregloXLineas);

				obtenerResultados(arregloXLineas, listPruebas);
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

	private static void obtenerResultados(String[] arregloXLineas, LinkedList<evidenceModel> listPruebas) {
		evidenceModel[] evidenceModels = new evidenceModel[arregloXLineas.length - 1];
		String[] arregloTemp = new String[] {};

		for (int i = 1; i < arregloXLineas.length; i++) {
			arregloTemp = arregloXLineas[i].split(" ");

			evidenceModels[i - 1] = new evidenceModel();

			asignaturas asignaturaTemp = asignaturas.valueOf(arregloTemp[0]);
			
			if(asignaturas.LECTURA.equals(asignaturaTemp)) {
				evidenceModels[i - 1].setPrueba(arregloTemp[0] + " " + arregloTemp[1]);
				evidenceModels[i - 1].setPuntaje(arregloTemp[2]);
				evidenceModels[i - 1].setNivel("");
				evidenceModels[i - 1].setDecil((arregloTemp[3] != null) ? arregloTemp[3] : "");
			}else if(asignaturas.MATEMÁTICAS.equals(asignaturaTemp)) {
				evidenceModels[i - 1].setPrueba(arregloTemp[0]);
				evidenceModels[i - 1].setPuntaje(arregloTemp[1]);
				evidenceModels[i - 1].setNivel("");
				evidenceModels[i - 1].setDecil((arregloTemp[2] != null) ? arregloTemp[2] : "");
			}else if(asignaturas.SOCIALES.equals(asignaturaTemp)) {
				evidenceModels[i - 1].setPrueba(arregloTemp[0] + " " + arregloTemp[1] + " " + arregloTemp[2]);
				evidenceModels[i - 1].setPuntaje(arregloTemp[3]);
				evidenceModels[i - 1].setNivel("");
				evidenceModels[i - 1].setDecil((arregloTemp[4] != null) ? arregloTemp[4] : "");
			} else if(asignaturas.CIENCIAS.equals(asignaturaTemp)) {
				evidenceModels[i - 1].setPrueba(arregloTemp[0] + " " + arregloTemp[1]);
				evidenceModels[i - 1].setPuntaje(arregloTemp[2]);
				evidenceModels[i - 1].setNivel("");
				evidenceModels[i - 1].setDecil((arregloTemp[3] != null) ? arregloTemp[3] : "");
			} else if(asignaturas.INGLÉS.equals(asignaturaTemp)) {
				evidenceModels[i - 1].setPrueba(arregloTemp[0]);
				evidenceModels[i - 1].setPuntaje(arregloTemp[1]);
				evidenceModels[i - 1].setNivel((arregloTemp[2] != null) ? arregloTemp[2] : "");
				evidenceModels[i - 1].setDecil(arregloTemp[3].toString());
			} else if(asignaturas.RAZONAMIENTO.equals(asignaturaTemp)) {
				evidenceModels[i - 1].setPrueba(arregloTemp[0] + " " + arregloTemp[1]);
				evidenceModels[i - 1].setPuntaje(arregloTemp[2]);
				evidenceModels[i - 1].setNivel("");
				evidenceModels[i - 1].setDecil((arregloTemp[3] != null) ? arregloTemp[3] : "");
			} else if(asignaturas.COMPETENCIAS.equals(asignaturaTemp)) {
				evidenceModels[i - 1].setPrueba(arregloTemp[0] + " " + arregloTemp[1]);
				evidenceModels[i - 1].setPuntaje(arregloTemp[2]);
				evidenceModels[i - 1].setNivel("");
				evidenceModels[i - 1].setDecil((arregloTemp[3] != null) ? arregloTemp[3] : "");
			}  
			
			listPruebas.add(evidenceModels[i - 1]);
		}
	}
	
	public enum asignaturas {
		LECTURA, MATEMÁTICAS, SOCIALES, CIENCIAS, INGLÉS, RAZONAMIENTO, COMPETENCIAS
	}
}
