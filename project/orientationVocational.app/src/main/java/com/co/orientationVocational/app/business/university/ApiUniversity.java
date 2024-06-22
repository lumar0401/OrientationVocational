package com.co.orientationVocational.app.business.university;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.co.orientationVocational.app.data.responseCardUniversity;
import com.co.orientationVocational.app.data.respuestaUniversidades;
import com.co.orientationVocational.app.services.implementation.universityService;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

public class ApiUniversity extends utils {
	private final GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBwf_p4e_yxObrIfh7GxA2LcUpnOZuGKPc").build();

	private final static Logger logger = LoggerFactory.getLogger(ApiUniversity.class);

	public ApiUniversity() {}

	public respuestaUniversidades consultaUniversidades(String ciudadUsuario, String responseTest, String identificacion, String test) 
			throws ApiException, InterruptedException, IOException {
		respuestaUniversidades response = new respuestaUniversidades();
		List<String> searchResults = new LinkedList<String>();

		try {
			searchResults = buscarUniversidades("Universidades en " + ciudadUsuario);

			if (!searchResults.isEmpty() && !esVacio(responseTest)) {
				LinkedList<responseCardUniversity> responseCard = new LinkedList<responseCardUniversity>();
				Map<String, Object> mapUniversity = new HashMap<String, Object>();

				response.setStatus(false);

				mapUniversity.put("stIdentificacion", identificacion);
				mapUniversity.put("stTest", test);

				responseCard = obtenerDatosBusqueda(mapUniversity, searchResults, responseTest);

				response.setCardsUniversidades(responseCard);
				response.setCantidadUniversidades(responseCard.size());
				response.setTest(mapUniversity.get("stTest").toString());
				response.setCategorias(mapUniversity.get("stCategorias").toString());

				if (responseCard.size() > 0)
					response.setStatus(true);
			}
		} catch (Exception e) {
			logger.error("Error en la busquedad de datos (API University) Datos: " + e);
		}

		return response;
	}

	private List<String> buscarUniversidades(String busqueda) {
		List<String> searchResults = new LinkedList<>();

		try {
			TextSearchRequest request = PlacesApi.textSearchQuery(context, "universitaria").query(busqueda);
			PlacesSearchResponse response = request.await();

			for (PlacesSearchResult result : response.results) {
				String[] temp = result.name.toString().split(" ");

				if ((temp.length > 1) && (result.name.toString().toLowerCase().contains("universidad")
						|| result.toString().toLowerCase().contains("university")
						|| result.toString().toLowerCase().contains("universitaria")
						|| result.toString().toLowerCase().contains("institución")
						|| result.toString().toLowerCase().contains("institucion")
						|| result.toString().toLowerCase().contains("instituto"))) {
					searchResults.add(result.name);
				}
			}
		} catch (Exception e) {
			logger.error("Error al buscar universidades: " + e);
		}

		return searchResults;
	}
	
	private LinkedList<responseCardUniversity> obtenerDatosBusqueda(Map<String, Object> mapUniversity, List<String> searchOne, String responseTest) throws IOException, SQLException {
		String universidades = searchOne.stream().collect(Collectors.joining(","));
		String intereses = "";
		
		LocalDate fechaActual = LocalDate.now();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String fechaFormateada = fechaActual.format(formato);

		LinkedList<responseCardUniversity> responseCard = new LinkedList<responseCardUniversity>();

		mapUniversity.put("stCategorias", responseTest);
		mapUniversity.put("stUniversidades", universidades);

		if (!esVacio(responseTest)) {
			intereses = equivalenciaIntereses(responseTest, mapUniversity.get("stTest").toString());
		}
		
		Map<String, modelUniversityPage> pDatos = consultaUrlUniversidad(searchOne, intereses, mapUniversity.get("stTest").toString());
		
		if (!esVacio(responseTest) && !esVacio(universidades) && !pDatos.isEmpty()) {			
			organizarMapaUniversidades(pDatos);
			
			for (Map.Entry<String, modelUniversityPage> entry : pDatos.entrySet()) {
				
				boolean metodoBusqueda = compararFechas(fechaFormateada, entry.getValue().getFechaRegistro());
				
				if(metodoBusqueda) {
					responseCard = armarRespuestaDataBase(responseTest, universidades, entry.getValue());
				}else {
					armarRespuestaWebscraping(responseTest, universidades, intereses, searchOne, mapUniversity, entry.getValue(), responseCard);
				}
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
					
					if (!esVacio(string) && !esVacio(page.getPaginaUrl())) {
						pDatosBusqueda.put(string, page);
					}
					
					continue;
				} else {
					long cantidadPalabras = Arrays.stream(string.split(" ")).count();

					if (cantidadPalabras < 3) {
						String temp = string.replace(" ", "%");
						modelUniversityPage page = universityservice.pageUniversity("%" + temp + "%", intereses, test);

						if (!esVacio(string) && !esVacio(page.getPaginaUrl())) {
							pDatosBusqueda.put(string, page);
						}

						continue;
					} else {
						String frase = StringUtils.stripAccents(string);

						if (frase.toLowerCase().contains("universidad") || frase.toLowerCase().contains("university")
								|| frase.toLowerCase().contains("universitaria")
								|| frase.toLowerCase().contains("institucion")
								|| frase.toLowerCase().contains("instituto")) {
							String parametro1 = (frase.toLowerCase().contains("universidad")) ? "universidad" : "";
							String parametro2 = (frase.toLowerCase().contains("university")) ? "university" : "";
							String parametro3 = (frase.toLowerCase().contains("universitaria")) ? "universitaria" : "";
							String parametro4 = (frase.toLowerCase().contains("institucion")) ? "institucion" : "";
							String parametro5 = (frase.toLowerCase().contains("instituto")) ? "instituto" : "";

							String temp = "";

							if (!esVacio(parametro1)) {
								temp = valorBusqueda(frase, parametro1);
							} else if (!esVacio(parametro2)) {
								temp = valorBusqueda(frase, parametro2);
							} else if (!esVacio(parametro3)) {
								temp = valorBusqueda(frase, parametro3);
							} else if (!esVacio(parametro4)) {
								temp = valorBusqueda(frase, parametro4);
							} else if (!esVacio(parametro5)) {
								temp = valorBusqueda(frase, parametro5);
							} else {
								temp = valorBusqueda(frase, " ");
							}

							modelUniversityPage page = universityservice.pageUniversity(temp, intereses, test);

							if (!esVacio(string) && !esVacio(page.getPaginaUrl())) {
								pDatosBusqueda.put(string, page);
							}

							continue;
						} else {
							modelUniversityPage page = universityservice.pageUniversity(" ", intereses, test);

							if (!esVacio(string) && !esVacio(page.getPaginaUrl())) {
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
		StringBuilder resultBuilder = new StringBuilder();

		if (test.toLowerCase().equals("chaside")) {
			switch (pDatos) {
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
							.filter(palabra -> !palabra.equals("instituto")).filter(palabra -> palabra.length() > 3)
							.collect(Collectors.toList());
					String fraseFinal = String.join(" ", palabrasFiltradas);
					resultado = fraseFinal;
				}
			} else {
				List<String> palabrasFiltradas = Arrays.stream(cantidadPalabras)
						.filter(palabra -> !palabra.equals(valor)).filter(palabra -> palabra.length() > 3)
						.collect(Collectors.toList());
				String fraseFinal = String.join(" ", palabrasFiltradas);
				resultado = fraseFinal;
			}
		} catch (Exception e) {
			resultado = "";
		}
		return resultado;
	}
	
	private void organizarMapaUniversidades(Map<String, modelUniversityPage> pDatos) {
		pDatos.entrySet().stream().sorted((a,b) -> a.getValue().getPosicion().compareTo(b.getValue().getPosicion()));
	}
	
	private LinkedList<responseCardUniversity> armarRespuestaDataBase(String responseTest, String universidades, modelUniversityPage pDatos) throws SQLException {
		
		LinkedList<responseCardUniversity> responseCard = new LinkedList<responseCardUniversity>();
		
		if (!esVacio(responseTest) && !esVacio(universidades) && !esVacio(pDatos)) {
			universityService universityservice = new universityService();
			
			try {
				responseCard = universityservice.informacionUniversidad(pDatos.getId(), pDatos.getPrograma());
				
				if (!esVacio(responseCard)) {
					for (responseCardUniversity responseCardUniversity : responseCard) {
						
						if (!esVacio(pDatos.getRequisitos())) {
							List<String> lista = Arrays.asList(pDatos.getRequisitos().split(" & "));
							responseCardUniversity.setRequisitos(lista);
						}

						if (!esVacio(pDatos.getDirecciones())) {
							List<String> lista = Arrays.asList(pDatos.getDirecciones().split(" & "));
							responseCardUniversity.setDirecciones(lista);
						}
					}
				}				
			} catch (Exception e) {
				logger.error("Error obteniendo información de universidades: " + e);
			}
		}
		
		return responseCard;
	}
	
	private void armarRespuestaWebscraping(String responseTest, String universidades, String intereses, List<String> searchOne, 
			Map<String, Object> mapUniversity, modelUniversityPage pDatos, LinkedList<responseCardUniversity> responseCard) {
		
		if (!esVacio(responseTest) && !esVacio(universidades)) {					
			try {				
				Map<String, String> responseWebScraping = new HashMap<String, String>();

				if (!esVacio(pDatos)) {
					webScraping scraping = new webScraping();
					
					if(!esVacio(pDatos.getId())) {
						String cadenaDatos = "";
						
						try {
							cadenaDatos = consultaMetadatosHtml(pDatos);
							
							if (!esVacio(cadenaDatos)) {
								responseWebScraping.putAll(scraping.webScrapingData(pDatos.getPaginaUrl().toString(), cadenaDatos));
								responseCard.add(responseUniversity(responseWebScraping, pDatos.getDirecciones(), pDatos.getRequisitos()));		
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				logger.error("Error obteniendo información de universidades: " + e);
			}
		}
	}

	private String consultaMetadatosHtml(modelUniversityPage datos) throws SQLException {
		universityService universityservice = new universityService();
		String metadatos = "";

		try {
			if (esEnlaceWeb(datos.getPaginaUrl().toString())) {
				metadatos = universityservice.obtainMetadatos(datos.getId().toString());

				if (!esVacio(metadatos)) {
					return metadatos;
				}
			}
		} catch (Exception e) {
			metadatos = "";
			logger.error("Error al obtener los metadatos de la universidad, Datos: " + datos + "Exception: " + e);
			return metadatos;
		}

		return null;
	}

	private responseCardUniversity responseUniversity(Map<String, String> pDatos, String direcciones, String requisitos) {
		responseCardUniversity response = new responseCardUniversity();

		if (pDatos.isEmpty()) {
			return response;
		}

		response.setTitulo(pDatos.getOrDefault("titulo", ""));
		response.setSemestres(pDatos.getOrDefault("semestres", ""));
		response.setValorSemestre(pDatos.getOrDefault("valorSemestre", ""));
		response.setModalidad(pDatos.getOrDefault("modalidad", ""));
		response.setDirectorPrograma(pDatos.getOrDefault("directorPrograma", ""));

		if (!esVacio(requisitos)) {
			List<String> lista = Arrays.asList(requisitos.split(" & "));
			response.setRequisitos(lista);
		}

		if (!esVacio(direcciones)) {
			List<String> lista = Arrays.asList(direcciones.split(" & "));
			response.setDirecciones(lista);
		}

		return response;
	}
}