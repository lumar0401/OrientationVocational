package com.co.orientationVocational.app.services.dto;

public class universityDto {
	private String identificacion;
	private String test;
	private String especialidad;
	
	public universityDto() {}

	public universityDto(String identificacion, String test, String especialidad) {
		this.identificacion = identificacion;
		this.test = test;
		this.especialidad = especialidad;
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

	/**
	 * @return the especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}

	/**
	 * @param especialidad the especialidad to set
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
