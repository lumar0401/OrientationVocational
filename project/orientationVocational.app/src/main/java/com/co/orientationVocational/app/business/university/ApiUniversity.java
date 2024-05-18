package com.co.orientationVocational.app.business.university;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.business.task.webScraping;
import com.co.orientationVocational.app.data.modelUniversityPage;
import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.data.responseCardUniversity;
import com.co.orientationVocational.app.data.respuestaUniversidades;
import com.co.orientationVocational.app.services.implementation.universityService;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

public class ApiUniversity extends utils{	
	private final GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBwf_p4e_yxObrIfh7GxA2LcUpnOZuGKPc").build();
	
	private final static Logger logger = LoggerFactory.getLogger(ApiUniversity.class);
	
	public ApiUniversity() {}
	
	public respuestaUniversidades consultaUniversidades(LinkedList<testModelResponse> responseTest, List<String> datosUsuario) throws ApiException, InterruptedException, IOException {
		respuestaUniversidades response = new respuestaUniversidades();
		List<String> searchResults = new LinkedList<String>();
		
		try {
			searchResults = buscarUniversidades(datosUsuario, "Universidades en " + datosUsuario.get(1));

			if(!searchResults.isEmpty() && !responseTest.isEmpty()) {
				LinkedList<responseCardUniversity> responseCard = new LinkedList<responseCardUniversity>();
				Map<String, Object> mapUniversity = new HashMap<String, Object>();
				
				response.setStatus(false);
				
				mapUniversity.put("stIdentificacion", datosUsuario.get(0));
				mapUniversity.put("stTest", datosUsuario.get(3));
				
				responseCard = obtenerDatosBusqueda(mapUniversity, searchResults, responseTest);		
				
				response.setCardsUniversidades(responseCard);
				response.setCantidadUniversidades(responseCard.size());
				response.setTest(mapUniversity.get("stTest").toString());
				response.setCategorias(mapUniversity.get("stCategorias").toString());
				
				if(!esVacio(responseCard))
					response.setStatus(true);
			}
		} catch (Exception e) {
			logger.error("Error en la busquedad de datos (API University) Datos: " + e);
		}
		
		return response;
	}
	
	private List<String> buscarUniversidades(List<String> datosUsuario, String busqueda) {
        List<String> searchResults = new LinkedList<>();

        try {
            TextSearchRequest request = PlacesApi.textSearchQuery(context, "universidad").query(busqueda);
            PlacesSearchResponse response = request.await();

            for (PlacesSearchResult result : response.results) {
                String[] temp = result.name.toString().split(" ");

                if ((temp.length > 1) && (result.toString().toLowerCase().contains("Universidad") || result.toString().toLowerCase().contains("University") || result.toString().toLowerCase().contains("Universitaria")
                		|| result.toString().toLowerCase().contains("institución") || result.toString().toLowerCase().contains("Institucion") || result.toString().toLowerCase().contains("Instituto"))) {
                    searchResults.add(result.name);
                }
            }
        } catch (Exception e) {
            logger.error("Error al buscar universidades: " + e);
        }

        return searchResults;
    }
	
	private LinkedList<responseCardUniversity> obtenerDatosBusqueda(Map<String, Object> mapUniversity, List<String> searchOne, LinkedList<testModelResponse> responseTest) throws IOException {
		String categorias = responseTest.stream().map(testModelResponse::getAreaInteres).collect(Collectors.joining(","));
        String universidades = searchOne.stream().collect(Collectors.joining(","));
        String intereses = "";
        
        LinkedList<responseCardUniversity> responseCard = new LinkedList<responseCardUniversity>();
        
        mapUniversity.put("stCategorias", categorias);
		mapUniversity.put("stUniversidades", universidades);		
		
		if(!esVacio(categorias)) {
	    	intereses = equivalenciaIntereses(categorias, mapUniversity.get("stTest").toString());
	    }
		
		if(!esVacio(categorias) && !esVacio(universidades)) {
			try {
				Map<String, modelUniversityPage> pDatos = consultaUrlUniversidad(searchOne, intereses, mapUniversity.get("stTest").toString());
				Map<String, String> responseWebScraping = new HashMap<String, String>();
				
				if(!pDatos.isEmpty()) {				
					webScraping scraping = new webScraping();
					
					for (modelUniversityPage modeloUniversidad : pDatos.values()) {	
						if(esVacio(modeloUniversidad.getId())) 
							continue;
												
					    String cadenaDatos = consultaMetadatosHtml(modeloUniversidad);
					    
					    if(!esVacio(cadenaDatos)) {
					    	responseWebScraping = scraping.webScrapingData(modeloUniversidad.getPaginaUrl().toString(), cadenaDatos);
					    						    	
					    	responseCardUniversity responseTemp = responseUniversity(responseWebScraping, modeloUniversidad.getDirecciones());
					    	
					    	responseCard.add(responseTemp);
					    }
					}
					
					if(!esVacio(responseCard))
						return responseCard;
				}
			} catch (Exception e) {
				LinkedList<responseCardUniversity> responseError = new LinkedList<responseCardUniversity>();
				logger.error("Error obteniendo información de universidades: " + e);
				return responseError;
			}
		}
		
		return responseCard;
	}
	
	private Map<String, modelUniversityPage> consultaUrlUniversidad(List<String> listaUniversidades, String intereses, String test) {
	    Map<String, modelUniversityPage> pDatosBusqueda = new HashMap<String, modelUniversityPage>();

	    try {
	    	universityService universityservice = new universityService(); 
	    	
	    	for (String string : listaUniversidades) {
		        Pattern patron = Pattern.compile("\\b[A-Z]+\\b");
		        Matcher coincidencia = patron.matcher(string);

		        if (coincidencia.find()) {
		            String prueba = "%" + coincidencia.group() + "%";
		            modelUniversityPage page = universityservice.pageUniversity(prueba, intereses, test);
		            pDatosBusqueda.put(string, page);
		            continue;
		        } else {
		        	long cantidadPalabras = Arrays.stream(string.split(" ")).count();
		        	
		            if (cantidadPalabras < 3) {
		                String temp = string.replace(" ", "%");
		                modelUniversityPage page = universityservice.pageUniversity("%" + temp + "%", intereses, test);
		                
		                if(!esVacio(string) && !esVacio(page.getPaginaUrl())) {
	            			pDatosBusqueda.put(string, page);
	            		}
		                
		                continue;
		            } else {
		            	String frase = StringUtils.stripAccents(string);
		            	
		            	if (frase.toLowerCase().contains("universidad") || frase.toLowerCase().contains("university") 
		            			|| frase.toLowerCase().contains("universitaria") || frase.toLowerCase().contains("institucion")
		            			|| frase.toLowerCase().contains("instituto")) {
		            		String parametro1 = (frase.toLowerCase().contains("universidad")) ? "universidad" : "";
		            		String parametro2 = (frase.toLowerCase().contains("university")) ? "university" : "";
		            		String parametro3 = (frase.toLowerCase().contains("universitaria")) ? "universitaria" : "";
		            		String parametro4 = (frase.toLowerCase().contains("institucion")) ? "institucion" : "";
		            		String parametro5 = (frase.toLowerCase().contains("instituto")) ? "instituto" : "";
		            		
		            		String temp = "";
		            		
		            		if(!esVacio(parametro1)) {
		            			temp = valorBusqueda(frase, parametro1);
		            		}else if(!esVacio(parametro2)) {
		            			temp = valorBusqueda(frase, parametro2);
		            		}else if(!esVacio(parametro3)) {
		            			temp = valorBusqueda(frase, parametro3);
		            		}else if(!esVacio(parametro4)) {
		            			temp = valorBusqueda(frase, parametro4);
		            		}else if(!esVacio(parametro5)) {
		            			temp = valorBusqueda(frase, parametro5);
		            		}else {
		            			temp = valorBusqueda(frase, " ");
		            		}
		            				            		
		            		modelUniversityPage page = universityservice.pageUniversity(temp, intereses, test);
		            		
		            		if(!esVacio(string) && !esVacio(page.getPaginaUrl())) {
		            			pDatosBusqueda.put(string, page);
		            		}
		            		
		            		continue;
		                }else {
		                	modelUniversityPage page = universityservice.pageUniversity(" ", intereses, test);		            		
		                	
		                	if(!esVacio(string) && !esVacio(page.getPaginaUrl())) {
		            			pDatosBusqueda.put(string, page);
		            		}
		                	
		            		continue;
		                }
		            }
		        }
		    }
		} catch (Exception e) {
			Map<String, modelUniversityPage> resultFallo = new HashMap<String, modelUniversityPage>();
			logger.error("Error al buscar url universidades: " + e);
			return resultFallo;
		}
	    
	    return pDatosBusqueda;
	}
	
	private String equivalenciaIntereses(String pDatos, String test) {
		String[] arregloIntereses = pDatos.split(",");
	    StringBuilder resultBuilder = new StringBuilder();

	    if (test.toLowerCase().equals("chaside")) {
	        for (String interes : arregloIntereses) {
	            switch (interes) {
	                case "Área Administrativa":
	                    resultBuilder.append("administrativa,");
	                    break;
	                case "Área de Humanidades y Ciencias Sociales y Jurídicas":
	                    resultBuilder.append("humanidades y sociales,");
	                    break;
	                case "Área Artística":
	                    resultBuilder.append("artistica,");
	                    break;
	                case "Área de Ciencias de la Salud":
	                    resultBuilder.append("salud,");
	                    break;
	                case "Área de Enseñanzas Técnicas":
	                    resultBuilder.append("tecnica,");
	                    break;
	                case "Área de Defensa y Seguridad":
	                    resultBuilder.append("seguridad,");
	                    break;
	                case "Área de Ciencias Experimentales":
	                    resultBuilder.append("ciencias experimentales,");
	                    break;
	                default:	                    
	                	resultBuilder.append("");
	                    break;
	            }
	        }
	    } else if (test.toLowerCase().equals("holland")) {
	        // Lógica para el caso "holland" (se puede agregar según sea necesario)
	    }

	    if (resultBuilder.length() > 0 && resultBuilder.charAt(resultBuilder.length() - 1) == ',') {
	        resultBuilder.deleteCharAt(resultBuilder.length() - 1);
	    }

	    return resultBuilder.toString();
	}
	
	private String valorBusqueda(String cadena, String valor) {
	    String resultado = "";
	    
	    try {
	        String[] cantidadPalabras = cadena.toLowerCase().split(" ");
	        String lowerCadena = cadena.toLowerCase();

	        if (valor.equalsIgnoreCase("universidad") || valor.equalsIgnoreCase("university")) {
	            int indice = (valor.equalsIgnoreCase("universidad")) ? lowerCadena.indexOf("universidad") : lowerCadena.indexOf("university");

	            if (indice != -1 && indice == cantidadPalabras.length - 1) {
	                resultado = cadena.replace(" ", "%") + "%";
	            } else {  
	                List<String> palabrasFiltradas = Arrays.stream(cantidadPalabras)
	                	    .filter(palabra -> !palabra.equals("universidad"))
	                	    .filter(palabra -> !palabra.equals("university"))
	                	    .filter(palabra -> !palabra.equals("universitaria"))
	                	    .filter(palabra -> !palabra.equals("institucion"))
	                	    .filter(palabra -> !palabra.equals("instituto"))
	                	    .filter(palabra -> palabra.length() > 3)
	                	    .collect(Collectors.toList());
	                String fraseFinal = String.join(" ", palabrasFiltradas);
	                resultado = fraseFinal;
	            }
	        }else {
	        	List<String> palabrasFiltradas = Arrays.stream(cantidadPalabras)
                	    .filter(palabra -> !palabra.equals(valor))
                	    .filter(palabra -> palabra.length() > 3)
                	    .collect(Collectors.toList());
                String fraseFinal = String.join(" ", palabrasFiltradas);
                resultado = fraseFinal;
	        }
	    } catch (Exception e) {
	        resultado = "";
	    }
	    return resultado;
	}
	
	private String consultaMetadatosHtml(modelUniversityPage datos) throws SQLException {
		universityService universityservice = new universityService(); 
		String metadatos = "";
		
		try {
			if(esEnlaceWeb(datos.getPaginaUrl().toString())) {
				metadatos = universityservice.obtainMetadatos(datos.getId().toString());
				
				if(!esVacio(metadatos)) {
					return metadatos;
				}
			}
		} catch (Exception e) {
			metadatos = "";
			logger.error("Error al obtener los metadatos de la universidad, Datos: "+ datos + "Exception: " + e);
			return metadatos;
		}		
		
		return null;
	}
	
	private responseCardUniversity responseUniversity(Map<String, String> pDatos, String direcciones) {
		responseCardUniversity response = new responseCardUniversity();
		
		if(pDatos.isEmpty()) {
			return response;
		}
		
		response.setTitulo(pDatos.getOrDefault("titulo", ""));
	    response.setSemestres(pDatos.getOrDefault("semestres", ""));
	    response.setValorSemestre(pDatos.getOrDefault("valorSemestre", ""));
	    response.setModalidad(pDatos.getOrDefault("modalidad", ""));
	    response.setDirectorPrograma(pDatos.getOrDefault("directorPrograma", ""));
		
	    if(pDatos.containsKey("requisitos")) {
	    	String arregloRequisitos = pDatos.get("requisitos");
	    	List<String> lista = Arrays.asList(arregloRequisitos.split("#")); 
	    	response.setRequisitos(lista);
	    }
	    
	    if(!esVacio(direcciones)) {
	    	List<String> lista = Arrays.asList(direcciones.split("%")); 
	    	response.setDirecciones(lista);
	    }
	    
		return response;
	}
}