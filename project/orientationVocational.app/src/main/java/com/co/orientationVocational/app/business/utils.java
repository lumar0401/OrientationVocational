package com.co.orientationVocational.app.business;

import java.text.SimpleDateFormat;
import java.util.Date;

public class utils {
	
	public static boolean esVacio(Object pstField) {
		if(pstField instanceof String) {
			String pstCampo = (String)pstField;
			return (pstCampo == null || pstCampo.trim().length() == 0 || "$".equals(pstCampo.trim()))?true:false;
		}else {
			return (pstField == null);
		}
	}
	
	public static boolean esNumeroDouble(String valor) {
		try {
	        Double.parseDouble(valor);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public boolean esNumeroEntero(String valor) {
		try {
	        Integer.parseInt(valor);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public boolean esNumero(String valor, String simbolo) {
		
		if(valor == null || valor.equals("") || valor.isEmpty()) {
			return false;
		}
		
		if(simbolo != null && !simbolo.isEmpty()) {
			String[] arreglo = valor.split(simbolo);
			
			for (String aux : arreglo) {
	            if (!esNumeroEnt(aux)) {
	                return false;
	            }
	        }
		}else {
			for (char c : valor.toCharArray()) {
	            if (!Character.isDigit(c)) {
	                return false;
	            }
	        }
		}
		
		return true;
	}
	
	private boolean esNumeroEnt(String valor) {
	    try {
	        Integer.parseInt(valor);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public boolean esEnlaceWeb(String cadena) {
        String patronEnlaceWeb = "^(http[s]?:\\/\\/)?([w]{3}\\.)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(\\/\\S*)?$";
        return cadena.matches(patronEnlaceWeb);
    }
	
	public boolean compararFechas(String fecha1, String fecha2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date date1 = sdf.parse(fecha1);
            Date date2 = sdf.parse(fecha2);
            
            long diffEnMilisegundos = date2.getTime() - date1.getTime();
            long diffEnMeses = diffEnMilisegundos / (30L * 24L * 60L * 60L * 1000L * 60L);
            
            if(diffEnMeses <= 6) {
            	return true;
            }else {
            	return false;
            }
			
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean esArregloDouble(String test) {
		String[] args = test.replace("[", "").replace("]", "").split(",");
		
		try {
			for(String elemento: args) {
				Double.parseDouble(elemento);
			}
	        
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
