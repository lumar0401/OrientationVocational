package com.co.orientationVocational.app.business.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.data.testModel;
import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.services.implementation.testService;

public class testChaside extends utils {
	private final static Logger logger = LoggerFactory.getLogger(testChaside.class);

	public testChaside() {}
	
	public LinkedList<testModelResponse> resultTest(int[] questions, String identificacion) throws SQLException {
		LinkedList<testModelResponse> resultFinal = new LinkedList<>();
		LinkedList<testModel> test = new LinkedList<>();
		
		try {
			if(questions.length > 0) {
				test = validationPositionResult(questions);
				
				resultFinal = asignationResult(test, questions, identificacion);
			}else {
				logger.error("Numero invalido de respuestas");
			}
		} catch (Exception e) {
			logger.error("Error al crear la correlacion entre resultados: " + e);
		}
		
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
		if (esVacio(arguments)) {
	        return;
	    }
		
		testChasideInteres(arguments, mapDatos, new int[]{1, 12, 20, 53, 64, 71, 78, 85, 91, 98, 2, 15, 46, 51}, "C");
	    testChasideInteres(arguments, mapDatos, new int[]{9, 25, 34, 41, 56, 67, 74, 80, 89, 95, 30, 63, 72, 86}, "H");
	    testChasideInteres(arguments, mapDatos, new int[]{3, 11, 21, 28, 36, 45, 50, 57, 81, 96, 22, 39, 76, 82}, "A");
	    testChasideInteres(arguments, mapDatos, new int[]{8, 16, 23, 33, 44, 52, 62, 70, 87, 92, 4, 29, 40, 69}, "S");
	    testChasideInteres(arguments, mapDatos, new int[]{6, 19, 27, 38, 47, 54, 60, 75, 83, 97, 10, 26, 59, 90}, "I");
	    testChasideInteres(arguments, mapDatos, new int[]{5, 14, 24, 31, 37, 48, 58, 65, 73, 84, 13, 18, 43, 66}, "D");
	    testChasideInteres(arguments, mapDatos, new int[]{17, 32, 35, 42, 49, 61, 68, 77, 88, 93, 7, 55, 79, 94}, "E");
	}
	
	private static void testChasideInteres(int[] arregloPrincipal, Map<String, Integer> mapDatos, int[] arryInteres, String key) {
	    Set<Integer> setArregloPrincipal = Arrays.stream(arregloPrincipal).boxed().collect(Collectors.toSet());
	    Set<Integer> setArryInteres = Arrays.stream(arryInteres).boxed().collect(Collectors.toSet());

	    setArregloPrincipal.retainAll(setArryInteres);

	    mapDatos.put(key, setArregloPrincipal.size());
	}
	
	private static void comparateResult(LinkedList<testModel> result, Map<String, Integer> mapDatos) {
		if(esVacio(mapDatos)) {
			return;
		}
		
		try {
	        List<Map.Entry<String, Integer>> entries = new ArrayList<>(mapDatos.entrySet());
	        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	        testModel test1 = new testModel();
	        testModel test2 = new testModel();

	        if (!entries.isEmpty()) {
	            Map.Entry<String, Integer> entry1 = entries.get(0);
	            test1.setLlave(entry1.getKey());
	            test1.setResultado(entry1.getValue());
	            result.add(test1);
	        }

	        if (entries.size() > 1) {
	            Map.Entry<String, Integer> entry2 = entries.get(1);
	            test2.setLlave(entry2.getKey());
	            test2.setResultado(entry2.getValue());
	            result.add(test2);
	        }
	    } catch (Exception e) {
	        logger.error("Error al crear los modelos: " + e);
	    }
	}

	private static LinkedList<testModelResponse>  asignationResult(LinkedList<testModel> test, int[] questions, String identificacion) throws SQLException {
		LinkedList<testModelResponse> resultFinal = new LinkedList<testModelResponse>();
		
		String validacionTestUsuario = correlacionTest(identificacion, questions);
		
		testModelResponse[] respons = new testModelResponse[2]; 
				
		for (int i = 0; i < test.size(); i++) {
			respons[i] = new testModelResponse();
			
			respons[i].setLlave(test.get(i).getLlave().toString());
			respons[i].setResultado(test.get(i).getResultado());
			respons[i].setMensaje(validacionTestUsuario);
			
			AsignationInters(respons[i], test.get(i).getLlave(), test.get(i).getResultado());
			
			resultFinal.add(respons[i]);
		}
		
		return resultFinal;
	}
	
	private static void AsignationInters(testModelResponse testResponse, String valorCampo, int resultado) {
		String career = "";
		String caract = "";
		
		comparation valor = comparation.valueOf(valorCampo);
		
		if((comparation.C.equals(valor)) && resultado > 0) {
			career = "Área Administrativa";
			caract = "Organización, Supervisión, Orden, Análisis y síntesis, Colaboración, "
					+ "Cálculo, Persuasivo, Objetivo, Práctico, Tolerante, Responsable, "
					+ "Ambicioso";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.H.equals(valor)) && resultado > 0) {
			career = "Área de Humanidades y Ciencias Sociales y Jurídicas";
			caract = "Precisión Verbal, Organización, Relación de hechos, Lingüística, "
					+ "Orden, Justicia, Responsable, Justo, Conciliador, Persuasivo, "
					+ "Sagaz, Imaginativo";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.A.equals(valor)) && resultado > 0) {
			career = "Área Artística";
			caract = "Estético, Armónico, Manual, Visual, Auditivo, Sensible, Imaginativo, "
					+ "Creativo, Detallista, Innovador, Intuitivo";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.S.equals(valor)) && resultado > 0) {
			career = "Área de Ciencias de la Salud";
			caract = "Asistir, Investigar, Precisión, Percepción, Análisis, Ayudar, "
					+ "Altruista, Solidario, Paciente, Comprensivo, Respetuoso, "
					+ "Persuasivo";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.I.equals(valor)) && resultado > 0) {
			career = "Área de Enseñanzas Técnicas";
			caract = "Cálculo, Científico, Manual, Exactitud, Planificar, Preciso, "
					+ "Práctico, Crítico, Analítico, Rígido";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.D.equals(valor)) && resultado > 0) {
			career = "Área de Defensa y Seguridad";
			caract = "Justicia, Equidad, Colaboración, Espíritu de equipo, Liderazgo, "
					+ "Arriesgado, Solidario, Valiente, Agresivo, Persuasivo";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.E.equals(valor)) && resultado > 0) {
			career = "Área de Ciencias Experimentales";
			caract = "Investigación, Orden, Organización, Análisis y Síntesis, Cálculo numérico, "
					+ "Clasificar, Metódico, Analítico, Observador, Introvertido, Paciente, "
					+ "Seguro";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else {
			career = "Campo Incorrecto";
			logger.error("Error al asignar valores (campo invalido o incorrecto)");
		}
	}
	
	private static String correlacionTest(String identificacion, int[] questions) throws SQLException {
		testService testservice = new testService();
		
		LinkedList<String> busquedadTest = testservice.testPreviosUsuario(identificacion);
		
		List<double[]> tests = new ArrayList<>();
		
		for (String test : busquedadTest) {
			tests.add(convertirCadenaADouble(test));
		}
		
		PearsonsCorrelation correlacion = new PearsonsCorrelation();
		
		List<Double> correlaciones = new ArrayList<>();
		
		double[] test1 = tests.get(0);
		
		for (int i = 0; i < tests.size(); i++) {
			double correlacionTest = correlacion.correlation(test1, tests.get(i));
			correlaciones.add(correlacionTest);
		}
		
		return null;
	}
	
	private static double[] convertirCadenaADouble(String cadena) {
		String[] numerosComoString = cadena.replaceAll("\\[|\\]","").split(", ");
		double[] arregloDouble = new double[numerosComoString.length];
		
		for (int i = 0; i < numerosComoString.length; i++) {
			arregloDouble[i] = Double.parseDouble(numerosComoString[i]);
		}
		
		return arregloDouble;
	}
	
	public enum comparation{
		C,H,A,S,I,D,E
	}
}