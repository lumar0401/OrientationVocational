package com.co.orientationVocational.app.data;

public class infoTest {
	private String test;
	private double porcentaje;
	private String perfilProfesional;
	
	public infoTest() {}

	public infoTest(String test, double porcentaje, String perfilProfesional) {
		this.test = test;
		this.porcentaje = porcentaje;
		this.perfilProfesional = perfilProfesional;
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
	 * @return the porcentaje
	 */
	public double getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * @return the perfilProfesional
	 */
	public String getPerfilProfesional() {
		return perfilProfesional;
	}

	/**
	 * @param perfilProfesional the perfilProfesional to set
	 */
	public void setPerfilProfesional(String perfilProfesional) {
		this.perfilProfesional = perfilProfesional;
	}
}
