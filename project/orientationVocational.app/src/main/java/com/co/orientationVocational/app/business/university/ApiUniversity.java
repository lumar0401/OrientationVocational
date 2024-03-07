package com.co.orientationVocational.app.business.university;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

public class ApiUniversity {	
	private final GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBwf_p4e_yxObrIfh7GxA2LcUpnOZuGKPc").build();
	
	public ApiUniversity() {}
	
	public List<String> consultaUniversidades(String ciudad) throws ApiException, InterruptedException, IOException {
		String busqueda = "Universidades en " + ciudad.toString();
		List<String> searchOne = new LinkedList<String>();
		
		TextSearchRequest request = PlacesApi.textSearchQuery(context, busqueda);
		PlacesSearchResponse response = request.await();
		
		for (PlacesSearchResult result : response.results) {
			String[] temp = result.toString().split(" ");
			
			if((temp.length > 2) && (result.toString().contains("Universidad") || result.toString().contains("University") || result.toString().contains("Universitaria"))) {
				searchOne.add(result.toString());
			}
		}
		
		return searchOne;
	}
}
