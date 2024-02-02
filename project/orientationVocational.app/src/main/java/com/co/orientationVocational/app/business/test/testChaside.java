package com.co.orientationVocational.app.business.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.data.testModel;
import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.security.jwt.jwtEntryPoint;

public class testChaside extends utils {
	private final static Logger logger = LoggerFactory.getLogger(jwtEntryPoint.class);

	public testChaside() {
	}
	
	public LinkedList<testModelResponse> resultTest(int[] questions) {
		LinkedList<testModelResponse> resultFinal = new LinkedList<>();
		LinkedList<testModel> test = new LinkedList<>();
		
		test = validationPositionResult(questions);
		
		resultFinal = asignationResult(test);
		
		return resultFinal;
	}

	private LinkedList<testModel> validationPositionResult(int[] test) {
		LinkedList<testModel> result = new LinkedList<>();

		Map<String, Integer> mapDatos = new HashMap<String, Integer>();
		
		generateResult(test, mapDatos);
		comparateResult(result, mapDatos);

		return result;
	}

	private static void generateResult(int[] arguments, Map<String, Integer> mapDatos) {		
		if(!esVacio(arguments)) {
			testChasideCInteres(arguments, mapDatos);
		}
		if(!esVacio(arguments)) {
			testChasideHInteres(arguments, mapDatos);
		}
		if(!esVacio(arguments)) {
			testChasideAInteres(arguments, mapDatos);
		}
		if(!esVacio(arguments)) {
			testChasideSInteres(arguments, mapDatos);
		}
		if(!esVacio(arguments)) {
			testChasideIInteres(arguments, mapDatos);
		}
		if(!esVacio(arguments)) {
			testChasideDInteres(arguments, mapDatos);
		}
		if(!esVacio(arguments)) {
			testChasideEInteres(arguments, mapDatos);
		}
	}
	
	private static void comparateResult(LinkedList<testModel> result, Map<String, Integer> mapDatos) {
		testModel test1 = new testModel();
		testModel test2 = new testModel();
		
		int primerValorMasAlto = Integer.MIN_VALUE;
        int segundoValorMasAlto = Integer.MIN_VALUE;
        String primeraLlave = null;
        String segundaLlave = null;
		
		try {
			for (Map.Entry<String, Integer> entry : mapDatos.entrySet()) {
				if(entry.getValue() > primerValorMasAlto) {
					segundoValorMasAlto = primerValorMasAlto;
	                segundaLlave = primeraLlave;
	                primerValorMasAlto = entry.getValue();
	                primeraLlave = entry.getKey();
				}else if (entry.getValue() > segundoValorMasAlto) {
	                segundoValorMasAlto = entry.getValue();
	                segundaLlave = entry.getKey();
	            }
			}
			
			test1.setLlave(primeraLlave);
			test1.setResultado(primerValorMasAlto);
			
			test2.setLlave(segundaLlave);
			test2.setResultado(segundoValorMasAlto);
			
			result.add(test1);
			result.add(test2);
		} catch (Exception e) {
			logger.error("Error al crear los modelos");
		}
	}

	private static void testChasideCInteres(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryC = new int[] { 1, 12, 20, 53, 64, 71, 78, 85, 91, 98 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryC.length; j++) {
				if (arregloPrincipal[i] == arryC[j]) {
					result++;
				}
			}
		}
		
		mapDatos.put("C", result);
		testChasideCAptitud(arregloPrincipal, mapDatos);
	}
	
	private static void testChasideCAptitud(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryC = new int[] { 2, 15, 46, 51 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryC.length; j++) {
				if (arregloPrincipal[i] == arryC[j]) {
					result++;
				}
			}
		}
		
		if(mapDatos.containsKey("C")) {
			final int valorCampo = result;
			mapDatos.compute("C", (k, v) -> v + valorCampo);
		}
	}
	
	private static void testChasideHInteres(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryH = new int[] { 9, 25, 34, 41, 56, 67, 74, 80, 89, 95 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryH.length; j++) {
				if (arregloPrincipal[i] == arryH[j]) {
					result++;
				}
			}
		}
		
		mapDatos.put("H", result);
		testChasideHAptitud(arregloPrincipal, mapDatos);
	}
	
	private static void testChasideHAptitud(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryH = new int[] { 30, 63, 72, 86 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryH.length; j++) {
				if (arregloPrincipal[i] == arryH[j]) {
					result++;
				}
			}
		}
		
		if(mapDatos.containsKey("H")) {
			final int valorCampo = result;
			mapDatos.compute("H", (k, v) -> v + valorCampo);
		}
	}
	
	private static void testChasideAInteres(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryA = new int[] { 3, 11, 21, 28, 36, 45, 50, 57, 81, 96 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryA.length; j++) {
				if (arregloPrincipal[i] == arryA[j]) {
					result++;
				}
			}
		}
		
		mapDatos.put("A", result);
		testChasideAAptitud(arregloPrincipal, mapDatos);
	}
	
	private static void testChasideAAptitud(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryA = new int[] { 22, 39, 76, 82 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryA.length; j++) {
				if (arregloPrincipal[i] == arryA[j]) {
					result++;
				}
			}
		}
		
		if(mapDatos.containsKey("A")) {
			final int valorCampo = result;
			mapDatos.compute("A", (k, v) -> v + valorCampo);
		}
	}
	
	private static void testChasideSInteres(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryS = new int[] { 8, 16, 23, 33, 44, 52, 62, 70, 87, 92 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryS.length; j++) {
				if (arregloPrincipal[i] == arryS[j]) {
					result++;
				}
			}
		}
		
		mapDatos.put("S", result);
		testChasideSAptitud(arregloPrincipal, mapDatos);
	}
	
	private static void testChasideSAptitud(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryS = new int[] { 4, 29, 40, 69 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryS.length; j++) {
				if (arregloPrincipal[i] == arryS[j]) {
					result++;
				}
			}
		}
		
		if(mapDatos.containsKey("S")) {
			final int valorCampo = result;
			mapDatos.compute("S", (k, v) -> v + valorCampo);
		}
	}
	
	private static void testChasideIInteres(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryI = new int[] { 6, 19, 27, 38, 47, 54, 60, 75, 83, 97 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryI.length; j++) {
				if (arregloPrincipal[i] == arryI[j]) {
					result++;
				}
			}
		}
		
		mapDatos.put("I", result);
		testChasideIAptitud(arregloPrincipal, mapDatos);
	}
	
	private static void testChasideIAptitud(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryI = new int[] { 10, 26, 59, 90 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryI.length; j++) {
				if (arregloPrincipal[i] == arryI[j]) {
					result++;
				}
			}
		}
		
		if(mapDatos.containsKey("I")) {
			final int valorCampo = result;
			mapDatos.compute("I", (k, v) -> v + valorCampo);
		}
	}
	
	private static void testChasideDInteres(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryD = new int[] { 5, 14, 24, 31, 37, 48, 58, 65, 73, 84 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryD.length; j++) {
				if (arregloPrincipal[i] == arryD[j]) {
					result++;
				}
			}
		}
		
		mapDatos.put("D", result);
		testChasideDAptitud(arregloPrincipal, mapDatos);
	}
	
	private static void testChasideDAptitud(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryD = new int[] { 13, 18, 43, 66 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryD.length; j++) {
				if (arregloPrincipal[i] == arryD[j]) {
					result++;
				}
			}
		}
		
		if(mapDatos.containsKey("D")) {
			final int valorCampo = result;
			mapDatos.compute("D", (k, v) -> v + valorCampo);
		}
	}
	
	private static void testChasideEInteres(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryE = new int[] { 17, 32, 35, 42, 49, 61, 68, 77, 88, 93 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryE.length; j++) {
				if (arregloPrincipal[i] == arryE[j]) {
					result++;
				}
			}
		}
		
		mapDatos.put("E", result);
		testChasideEAptitud(arregloPrincipal, mapDatos);
	}
	
	private static void testChasideEAptitud(int[] arregloPrincipal, Map<String, Integer> mapDatos) {
		int[] arryE = new int[] { 7, 55, 79, 94 };

		int result = 0;

		for (int i = 0; i < arregloPrincipal.length; i++) {
			for (int j = 0; j < arryE.length; j++) {
				if (arregloPrincipal[i] == arryE[j]) {
					result++;
				}
			}
		}
		
		if(mapDatos.containsKey("E")) {
			final int valorCampo = result;
			mapDatos.compute("E", (k, v) -> v + valorCampo);
		}
	}
	
	private static LinkedList<testModelResponse>  asignationResult(LinkedList<testModel> test) {
		LinkedList<testModelResponse> resultFinal = new LinkedList<testModelResponse>();
		
		testModelResponse response1 = new testModelResponse();
		testModelResponse response2 = new testModelResponse();
		
		int temp = 1;
		
		String asignation = "";
		
		for (testModel testModel : test) {
			if((temp == 1) && testModel.getResultado() > 0) {
				response1.setLlave(testModel.getLlave().toString());
				response1.setResultado(testModel.getResultado());
				
				asignation = AsignationInters(testModel.getLlave(), testModel.getResultado());
				response1.setAreaInteres(asignation);
				
				asignation = "";
				
				resultFinal.add(response1);
				
				temp++;
			}else if((temp == 2) && testModel.getResultado() > 0) {
				response2.setLlave(testModel.getLlave().toString());
				response2.setResultado(testModel.getResultado());
				
				asignation = AsignationInters(testModel.getLlave(), testModel.getResultado());
				response2.setAreaInteres(asignation);
				
				asignation = "";
				
				resultFinal.add(response2);
			}
		}
		
		return resultFinal;
	}
	
	private static String AsignationInters(String valorCampo, int resultado) {
		String career = "";
		
		comparation valor = comparation.valueOf(valorCampo);
		
		if((comparation.C.equals(valor)) && resultado > 0) {
			career = "Área Administrativa";
		}else if((comparation.H.equals(valor)) && resultado > 0) {
			career = "Área de Humanidades y Ciencias Sociales y Jurídicas";
		}else if((comparation.A.equals(valor)) && resultado > 0) {
			career = "Área Artística";
		}else if((comparation.S.equals(valor)) && resultado > 0) {
			career = "Área de Ciencias de la Salud";
		}else if((comparation.I.equals(valor)) && resultado > 0) {
			career = "Área de Enseñanzas Técnicas";
		}else if((comparation.D.equals(valor)) && resultado > 0) {
			career = "Área de Defensa y Seguridad";
		}else if((comparation.E.equals(valor)) && resultado > 0) {
			career = "Área de Ciencias Experimentales";
		}else {
			career = "Campo Incorrecto";
		}
		
		return career;
	}
	
	public enum comparation{
		C,H,A,S,I,D,E
	}
}
