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

	        if (entries.size() > 1 && !entries.get(1).getValue().equals(0)) {
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
		
		String resultadoCorrelacion = "";
		
		LinkedList<String> busquedadTest = testservice.testPreviosUsuario(identificacion, "Test Chaside");
		
		if(busquedadTest.size() < 1) {
			return "analysis completed";
		}
		
		List<double[]> tests = new ArrayList<>();
		
		for (String test : busquedadTest) {
			if(esArregloDouble(test)) {
				tests.add(convertirCadenaADouble(test));
			}
		}
		
		List<double[]> busquedadTestFinal = ListArrayComplement(tests, questions.length);
		
		double[] test1 = convertirArregloADouble(questions);
		
		List<double[]> busquedadCorrelacionFinal = ArrayComplement(busquedadTestFinal, test1);
		
		PearsonsCorrelation correlacion = new PearsonsCorrelation();
		List<Double> correlaciones = new ArrayList<>();
		
		if(!busquedadCorrelacionFinal.isEmpty()) {
			for (int i = 0; i < (busquedadCorrelacionFinal.size() - 1); i++) {
				double correlacionTest = correlacion.correlation(busquedadCorrelacionFinal.get(busquedadCorrelacionFinal.size() - 1), busquedadCorrelacionFinal.get(i));
				correlaciones.add(correlacionTest);
			}
		}
		
		resultadoCorrelacion = resultadoCorrelacion(correlaciones);
		
		return resultadoCorrelacion;
	}
	
	private static List<double[]> ArrayComplement(List<double[]> busquedadTest, double[] question) {
		if (question.length > busquedadTest.get(0).length) {
	        busquedadTest.add(Arrays.copyOf(question, question.length));
	    } else if (question.length < busquedadTest.get(0).length) {
	        double[] newArray = Arrays.copyOf(question, busquedadTest.get(0).length);
	        busquedadTest.add(newArray);
	    } else {
	        busquedadTest.add(question);
	    }
	    
	    return busquedadTest;
	}
	
	private static List<double[]> ListArrayComplement(List<double[]> busquedadTest, int size) {
		int valorArreglo = busquedadTest.stream().mapToInt(arr -> arr.length).max().orElse(0);
		
		if(size > valorArreglo) {
			valorArreglo = size;
		}
		
		int maxSize = valorArreglo;
		
		return busquedadTest.stream()
				.map(arr -> {
					if(arr.length < maxSize) {
						return Arrays.copyOf(arr, maxSize);
					}else {
						return arr;
					}
				})
				.collect(Collectors.toList());
	}
	
	private static double[] convertirCadenaADouble(String cadena) {
		String[] numerosComoString = cadena.replaceAll("\\[|\\]","").split(", ");
		double[] arregloDouble = new double[numerosComoString.length];
		
		for (int i = 0; i < numerosComoString.length; i++) {
			arregloDouble[i] = Double.parseDouble(numerosComoString[i]);
		}
		
		return arregloDouble;
	}
	
	private static double[] convertirArregloADouble(int[] numeros) {
	    double[] arregloDouble = new double[numeros.length];
	    
	    for (int i = 0; i < numeros.length; i++) {
	        arregloDouble[i] = (double) numeros[i];
	    }
	    
	    return arregloDouble;
	}
	
	private static String resultadoCorrelacion(List<Double> correlaciones) {
		HashMap<Double, Integer> frecuencia = new HashMap<Double, Integer>();
		
		String resultadoAnalisis = "";
		
		for (double datos : correlaciones) {
			if(frecuencia.containsKey(datos)) {
				frecuencia.put(datos, frecuencia.get(datos) + 1);
			}else {
				frecuencia.put(datos, 1);
			}
		}
		
		double valorMasFrecuente = 0.0;
		int maxFrecuencia = 0;
		
		for (double key : frecuencia.keySet()) {
			if(frecuencia.get(key) > maxFrecuencia) {
				valorMasFrecuente = key;
				maxFrecuencia = frecuencia.get(key);
			}
		}
		
		if(valorMasFrecuente < -0.9) {
			resultadoAnalisis = "De acuerdo al analisis y proyeccion no hay una ninguna relación entre los resultados generados por el test"
					+ " y los aptitudes del usuario, así mismo se encuentra una relación nula entre la especialidad y el interes vocacional"
					+ " Se recomienda realizar nuevamente la prueba, recuerde que se debe realizar a consciencia y puede tomarse el"
					+ " tiempo necesario.";
		}else if(valorMasFrecuente > -0.99 && valorMasFrecuente < -0.01) {
			resultadoAnalisis = "De acuerdo al analisis y entre los diferentes test realizados, se encuentra una relación limitada entre los"
					+ " conocimientos del usuario y las actitudes e intereses, al tener un relacion baja es recomendable revisar documentacion"
					+ " de las diferentes carreras, una vez encontrado nueva información es recomendable realizar nuevament el test";
		}else if(valorMasFrecuente > 0.0 && valorMasFrecuente < 0.99) {
			resultadoAnalisis = "Tras un análisis y la realización de diversos tests, se ha identificado una relación significativa entre el "
					+ "nivel de conocimientos del usuario y sus actitudes e intereses. Aunque la correlación es moderada, se sugiere explorar "
					+ "la documentación sobre las diferentes carreras para obtener una perspectiva más completa. Al obtener nueva información, "
					+ "se recomienda volver a realizar el test para afinar aún más la evaluación";
		}else if(valorMasFrecuente >= 1.0) {
			resultadoAnalisis = "De acuerdo a los test de orientación vocacional, el usuario cuenta con una alta relación entre los intereses "
					+ "y los requisitos de la carrera universitaria.";
		}else {
			resultadoAnalisis = "Error al realizar el analisis de Correlación Pearsons";
		}
		
		return resultadoAnalisis;
	}
	
	public enum comparation{
		C,H,A,S,I,D,E
	}
}