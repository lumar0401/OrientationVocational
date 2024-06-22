package com.co.orientationVocational.app.business.task;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.orientationVocational.app.business.utils;

public class webScraping extends utils{
	private final static Logger logger = LoggerFactory.getLogger(webScraping.class);
	
	public webScraping() {}
	
	public Map<String, String> webScrapingData(String urlPagina, String cadenaBusqueda) {
	    Map<String, String> responseWebScraping = new HashMap<>();

	    try {
	        if (!esEnlaceWeb(urlPagina)) {
	            logger.error("la Url no contiene un formato correcto");
	            responseWebScraping.clear();
	            return responseWebScraping;
	        }

	        String[] arregloHtml = cadenaBusqueda.split("#");

	        Document doc = Jsoup.connect(urlPagina).get();

	        Arrays.stream(arregloHtml)
	                .map(htmlElement -> (htmlElement.toLowerCase().contains(";")) ? htmlElement.split(";") : htmlElement.split(","))
	                .forEach(tempBusqueda -> {
	                    Element elements = doc.select(tempBusqueda[1]).first();
	                    String temp = elements.nextSibling().toString().trim();

	                    if (elements.toString().contains("SMMLV")) {
	                        String temp2 = elements.childNode(0).toString().trim();
	                        String valorSemestre;
							valorSemestre = ValueFinalsalaryMinimum(temp2);
							temp = valorSemestre;
	                    }

	                    if (!esVacio(temp)) {
	                        System.out.println(temp + " - ");
	                        responseWebScraping.put(tempBusqueda[0], temp);
	                    }
	                });
	    } catch (Exception e) {
	        logger.error("Error al realizar el web scraping. Datos: " + e);
	        responseWebScraping.clear();
	    }

	    return responseWebScraping;
	}
	
	public String ValueFinalsalaryMinimum(String value) {
	    try {
	        String[] arreglo = value.split(" ");
	        double cantidadsemestre = Double.parseDouble(arreglo[0].replace(",", "."));
	        double temp = 1300000.0 * cantidadsemestre;
	        return formatoMoneda(temp);
	    } catch (NumberFormatException e) {
	        logger.error("Error al convertir el valor a double: " + e);
	        return "";
	    } catch (Exception e) {
	        logger.error("Error al calcular el salario mínimo: " + e);
	        return "";
	    }
	}
	
	private String formatoMoneda(double valorEntrada) {
		Locale zonaLocal = new Locale("es", "CO");
		NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(zonaLocal);
		String cantidadFormateada = formatoMoneda.format(valorEntrada); 
		return cantidadFormateada;
	}
}
