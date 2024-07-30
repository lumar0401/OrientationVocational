package com.co.orientationVocational.app.business.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.data.testModel;
import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.services.implementation.testService;

public class testHolland extends utils {
	private final static Logger logger = LoggerFactory.getLogger(testHolland.class);

	public testHolland() {}
	
	public LinkedList<testModelResponse> resultTest(int[] questions, String identificacion) throws SQLException {
		LinkedList<testModelResponse> resultFinal = new LinkedList<>();
		LinkedList<testModel> test = new LinkedList<>();
		
		if(questions.length > 0) {
			test = validationPositionResult(questions);
			
			resultFinal = asignationResult(test, questions, identificacion);
		}else {
			logger.error("Numero invalido de respuestas");
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
		String[] llaves = {"C","R","I","E","S","A"};
		
		for (int i = 0; i < arguments.length; i++) {
			mapDatos.put(llaves[i], arguments[i]);
		}
	}
	
	private static void comparateResult(LinkedList<testModel> result, Map<String, Integer> mapDatos) {
		testModel test1 = new testModel();
		testModel test2 = new testModel();
		testModel test3 = new testModel();
		
		int primerValorMasAlto = Integer.MIN_VALUE;
        int segundoValorMasAlto = Integer.MIN_VALUE;
        int tercerValorMasAlto = Integer.MIN_VALUE;
        
        String primeraLlave = null;
        String segundaLlave = null;
        String terceraLlave = null;
		
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
	            }else if (entry.getValue() > tercerValorMasAlto) {
	                tercerValorMasAlto = entry.getValue();
	                terceraLlave = entry.getKey();
	            }
			}
			
			test1.setLlave(primeraLlave);
			test1.setResultado(primerValorMasAlto);
			
			test2.setLlave(segundaLlave);
			test2.setResultado(segundoValorMasAlto);
			
			test3.setLlave(terceraLlave);
			test3.setResultado(tercerValorMasAlto);
			
			result.add(test1);
			result.add(test2);
			result.add(test3);
		} catch (Exception e) {
			logger.error("Error al crear los modelos");
		}
	}
	
	private static LinkedList<testModelResponse>  asignationResult(LinkedList<testModel> test, int[] questions, String identificacion) throws SQLException {
		LinkedList<testModelResponse> resultFinal = new LinkedList<testModelResponse>();
		
		String validacionTestUsuario = correlacionTest(identificacion, questions);
		
		testModelResponse[] respons = new testModelResponse[3]; 
		
		for (int i = 0; i < test.size(); i++) {
			respons[i] = new testModelResponse();
			
			respons[i].setResultado(test.get(i).getResultado());
			
			AsignationInters(respons[i], test.get(i).getLlave(), test.get(i).getResultado());
			
			String llaveDesc = "";
			
			switch(test.get(i).getLlave().toString()) {
				case "C":
					llaveDesc = "Convencional o Detallista";
					break;
				case "R":
					llaveDesc = "Realista";
					break;
				case "I":
					llaveDesc = "Investigador";
					break;
				case "E":
					llaveDesc = "Emprendedor";
					break;
				case "S":
					llaveDesc = "Social";
					break;
				case "A":
					llaveDesc = "Artista";
					break;
				default:
					llaveDesc = "Error";
					break;
			}
			
			respons[i].setLlave(llaveDesc.toString());
			respons[i].setMensaje(validacionTestUsuario);
			
			resultFinal.add(respons[i]);
			
			llaveDesc = "";
		}
		
		return resultFinal;
	}
	
	private static void AsignationInters(testModelResponse testResponse, String valorCampo, int resultado) {
		String career = "";
		String caract = "";
		
		comparation valor = comparation.valueOf(valorCampo);
		
		if((comparation.C.equals(valor)) && resultado > 0) {
			career = "Desarrollo Informático, venta de soluciones técnicas,"
					+ "análisis financieros, contabilidad, búsqueda científica"
					+ "formación, consejo.";
			
			caract = "Incluye a las personas con un alto grado de control y que "
					+ "prefieren trabajar con (C) números y cifras. Son precisos "
					+ "en su trabajo y siempre cumplen las normas, leyes  y "
					+ "reglamentos laborales.";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.R.equals(valor)) && resultado > 0) {
			career = "La agricultura, el medio ambiente, industria en las cadenas"
					+ " de producción, instalador técnico, reparador o administrador"
					+ " como informático de redes.";
			
			caract = "Este ambiente incluye personas que se destacan por sus capacidades"
					+ " mecánicas y deportivas. Prefieren trabajar con maquinaria,"
					+ " equipamiento, plantas y animales. Es posible que también les"
					+ " guste trabajar fuera de una oficina.";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.I.equals(valor)) && resultado > 0) {
			career = "Informática, gestión de producción, pre-venta, consejo en "
					+ "organización, control de gestión, finanzas, contabilidad "
					+ "y area jurífica.";
			
			caract = "Este ambiente representa a los que prefieren profesiones científicas"
					+ " e intelectuales. Disfrutan de reunir información identificar teorías"
					+ " o hechos y analizar e interpretar información.";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.E.equals(valor)) && resultado > 0) {
			career = "Manager, jefe de ventas, recursos humanos, dirección financiera"
					+ " relaciones públicas, comercial, marketing operacional, compras"
					+ ", distribución, logística desarrollo de proyectos o negocios"
					+ ", dirección de PYME.";
			
			caract = "Este ambiente incluye a las personas con personalidad administrativa"
					+ ". Pueden conectar eficientemente sus ideas y opiniones con los demás "
					+ "y persuadirlos. Además, confían mucho en sí mismos y tienen la energía "
					+ "necesaria para lograr sus aspiraciones.";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.S.equals(valor)) && resultado > 0) {
			career = "Atención a clientes, soporte de ventas, comunicación y relaciones "
					+ "públicas, maestría, psicología, responsable de formación,"
					+ " servicio post-ventas, recursos humanos.";
			
			caract = "Este ambiente está representado por personas sociales que disfrutan "
					+ "al ayudar a otros. Prefieren  trabajar en grupos y se caracterizan "
					+ "también por sus grandes habilidades de comunicación.";
			
			testResponse.setAreaInteres(career);
			testResponse.setCaracteristicas(caract);
		}else if((comparation.A.equals(valor)) && resultado > 0) {
			career = "Artista, grafista, arquitecto, jefe de proyecto multimedia, "
					+ "marketing y publicidad.";
			
			caract = "Este ambiente incluye a las personas que aprecian las cualidades "
					+ "estéticas que expresan a través de su trabajo artístico y literario."
					+ " Se caracterizan por su flexibilidad y no conformidad o compromiso "
					+ "con su sistema específico.";
			
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
		
		LinkedList<String> busquedadTest = testservice.testPreviosUsuario(identificacion);
		
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
		C,R,I,E,S,A
	}
}