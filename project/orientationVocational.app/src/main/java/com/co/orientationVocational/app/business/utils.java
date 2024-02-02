package com.co.orientationVocational.app.business;

public class utils {
	
	public static boolean esVacio(Object pstField) {
		if(pstField instanceof String) {
			String pstCampo = (String)pstField;
			return (pstCampo == null || pstCampo.trim().length() == 0 || "$".equals(pstCampo.trim()))?true:false;
		}else {
			return (pstField == null);
		}
	}
	
	public boolean esNumero(String valor, String simbolo) {
		
		if(valor == null || valor.equals("") || valor.isEmpty()) {
			return false;
		}
		
		if(simbolo != null && !simbolo.isEmpty()) {
			String[] arreglo = valor.split(simbolo);
			
			for (String aux : arreglo) {
	            if (!esNumeroEntero(aux)) {
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
	
	private boolean esNumeroEntero(String valor) {
	    try {
	        Integer.parseInt(valor);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
