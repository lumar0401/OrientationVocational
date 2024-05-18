package com.co.orientationVocational.app.business.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.data.testModel;
import com.co.orientationVocational.app.data.testModelResponse;

public class testHolland extends utils {
	private final static Logger logger = LoggerFactory.getLogger(testHolland.class);

	public testHolland() {}
	
	public LinkedList<testModelResponse> resultTest(int[] questions) {
		LinkedList<testModelResponse> resultFinal = new LinkedList<>();
		LinkedList<testModel> test = new LinkedList<>();
		
		if(questions.length > 0) {
			test = validationPositionResult(questions);
			
			resultFinal = asignationResult(test);
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
	
	private static LinkedList<testModelResponse>  asignationResult(LinkedList<testModel> test) {
		LinkedList<testModelResponse> resultFinal = new LinkedList<testModelResponse>();
		
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
	
	public enum comparation{
		C,R,I,E,S,A
	}
}