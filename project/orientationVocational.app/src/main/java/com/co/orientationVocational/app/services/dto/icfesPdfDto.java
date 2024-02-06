package com.co.orientationVocational.app.services.dto;

public class icfesPdfDto {
	String ruta;
	String identificacion;

	public icfesPdfDto() {
	}
	
	public icfesPdfDto(String ruta, String identificacion) {
		this.ruta = ruta;
		this.identificacion = identificacion;
	}

	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
}
