package com.co.orientationVocational.app.business.university;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.services.implementation.universityService;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

public class ApiUniversity {	
	private final GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBwf_p4e_yxObrIfh7GxA2LcUpnOZuGKPc").build();
	
	private final static Logger logger = LoggerFactory.getLogger(ApiUniversity.class);
	
	@Autowired
	universityService university;
	
	public ApiUniversity() {}
	
	public List<String> consultaUniversidades(LinkedList<testModelResponse> responseTest, List<String> datosUsuario) throws ApiException, InterruptedException, IOException {
		List<String> searchResults = new LinkedList<String>();
		
		try {
			searchResults = buscarUniversidades(datosUsuario, "Universidades en " + datosUsuario.get(2) + ", " + datosUsuario.get(1));

            if (searchResults.size() < 4) {
                searchResults = buscarUniversidades(datosUsuario, "Universidades en " + datosUsuario.get(1));
            }
            
			if(!searchResults.isEmpty() && !responseTest.isEmpty()) {
				Map<String, Object> mapUniversity = new HashMap<String, Object>();
				
				mapUniversity.put("stIdentificacion", datosUsuario.get(0));
				
				obtenerDatosBusqueda(mapUniversity, searchResults, responseTest);				
			}
		} catch (Exception e) {
			logger.error("Error en la busquedad de datos (API University) Datos: " + e);
		}
		
		return searchResults;
	}
	
	private List<String> buscarUniversidades(List<String> datosUsuario, String busqueda) {
        List<String> searchResults = new LinkedList<>();

        try {
            TextSearchRequest request = PlacesApi.textSearchQuery(context, busqueda);
            PlacesSearchResponse response = request.await();

            for (PlacesSearchResult result : response.results) {
                String[] temp = result.name.toString().split(" ");

                if ((temp.length > 1) && (result.toString().contains("Universidad") || result.toString().contains("University") || result.toString().contains("Universitaria"))) {
                    searchResults.add(result.name + ", " + result.formattedAddress);
                }
            }
        } catch (Exception e) {
            logger.error("Error al buscar universidades: " + e);
        }

        return searchResults;
    }
	
	private void obtenerDatosBusqueda(Map<String, Object> mapUniversity, List<String> searchOne, LinkedList<testModelResponse> responseTest) {
		String categorias = responseTest.stream().map(testModelResponse::getAreaInteres).collect(Collectors.joining(","));
        String universidades = searchOne.stream().collect(Collectors.joining(","));
		
        mapUniversity.put("stCategorias", categorias);
		mapUniversity.put("stUniversidades", universidades);
		mapUniversity.put("stCantidadUniversidades", searchOne.size());
	}
}
