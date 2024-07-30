package com.co.orientationVocational.app.services.dto;

public class usuarioIcfesDto {
	private String identificacion;
	
	public usuarioIcfesDto() {}

	public usuarioIcfesDto(String identificacion) {
		this.identificacion = identificacion;
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
