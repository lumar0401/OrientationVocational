package com.co.orientationVocational.app.services.dto;

public class infoTestDto {
	private String identificacion;
	private String test;
	
	public infoTestDto() {}

	public infoTestDto(String identificacion, String test) {
		this.identificacion = identificacion;
		this.test = test;
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

	/**
	 * @return the test
	 */
	public String getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(String test) {
		this.test = test;
	}
}
