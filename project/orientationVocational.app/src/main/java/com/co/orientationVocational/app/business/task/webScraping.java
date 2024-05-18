package com.co.orientationVocational.app.business.task;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.orientationVocational.app.business.utils;

public class webScraping extends utils{
	private final String paginaSalarioMinimo = "https://www.salariominimocolombia.net/";	
	
	private final static Logger logger = LoggerFactory.getLogger(webScraping.class);
	
	public webScraping() {}
	
	public Map<String, String> webScrapingData(String urlPagina, String cadenaBusqueda) throws IOException {
		Map<String, String> responseWebScraping = new HashMap<>();
		
		try {
			if (!paginaValida(urlPagina)) {
				logger.error("la Url no contiene un formato correcto");
				responseWebScraping.clear();
	            return responseWebScraping;
	        }
			
			String[] arregloHtml = cadenaBusqueda.split("#");
			
	        Document doc = Jsoup.connect(urlPagina).get();

	        for (String htmlElement : arregloHtml) {
	        	String[] tempBusqueda = (htmlElement.toLowerCase().contains(";")) ? htmlElement.split(";") : htmlElement.split(",");
	        	
	            Element elements = doc.select(tempBusqueda[1]).first();
	            
	            String temp = elements.nextSibling().toString().trim();
	            
	            if(temp.contains("SMMLV")) {
	            	String temp2 = elements.previousSibling().toString().trim();

	                System.out.println(temp2 + " - "); 
	            }
	            
	            
	            if (!esVacio(temp)) {
	                System.out.println(temp + " - "); 
	                
	                responseWebScraping.put(tempBusqueda[0].toString(), temp);
	            }
	        }
		} catch (Exception e) {
			logger.error("Error al realizar el web scraping. Datos: " + e);			
			responseWebScraping.clear();
			return responseWebScraping;
		}
		
		return responseWebScraping;
	}
	
	public String ValueFinalsalaryMinimum(String value) throws IOException {
		String valueFinal = "";
		
		if(!esNumeroDouble(value)) {
			return valueFinal;
		}
		
		try {
			Document doc = Jsoup.connect(paginaSalarioMinimo.toString()).get();        
	        Elements elements = doc.select("span.preciodolar");

	        for (Element element : elements) {
	        	double salarioMinimo = convertirValor(element.text().toString());
	        	double cantidadsemestre = Double.parseDouble(value);
	        	double temp = salarioMinimo * cantidadsemestre;
	        	
	        	valueFinal = formatoMoneda(temp);
	        }
	        
	        return valueFinal;
		} catch (Exception e) {
			logger.error("Error al consultar datos web scraping Datos: " + e);
			
			valueFinal = "";
			return valueFinal;
		}
    }
	
	private double convertirValor(String cadena) {
		String salarioNumerico = cadena.replaceAll("[^\\d.]", ""); 		
		double salario = Double.parseDouble(salarioNumerico);
		return salario;
	}
	
	private String formatoMoneda(double valorEntrada) {
		Locale zonaLocal = new Locale("es", "CO");
		NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(zonaLocal);
		String cantidadFormateada = formatoMoneda.format(valorEntrada); 
		return cantidadFormateada;
	}
	
	private boolean paginaValida(String url) {
		if((url.toLowerCase().contains(".co") || url.toLowerCase().contains(".com")) && (url.toLowerCase().contains("http") || url.toLowerCase().contains("https"))) {
			return true;
		}
		
		return false;
	}
}
