package com.co.orientationVocational.app.data;

public class infoTest {
	private String test;
	private String resultadoChasideFrecuente;
	private String cantidadChasideResultado;
	private String resultadoHollandFrecuente;
	private String cantidadHollandResultado;
	
	public infoTest() {}

	public infoTest(String test, String resultadoChasideFrecuente, String cantidadChasideResultado,
			String resultadoHollandFrecuente, String cantidadHollandResultado) {
		this.test = test;
		this.resultadoChasideFrecuente = resultadoChasideFrecuente;
		this.cantidadChasideResultado = cantidadChasideResultado;
		this.resultadoHollandFrecuente = resultadoHollandFrecuente;
		this.cantidadHollandResultado = cantidadHollandResultado;
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
	 * @return the resultadoChasideFrecuente
	 */
	public String getResultadoChasideFrecuente() {
		return resultadoChasideFrecuente;
	}

	/**
	 * @param resultadoChasideFrecuente the resultadoChasideFrecuente to set
	 */
	public void setResultadoChasideFrecuente(String resultadoChasideFrecuente) {
		this.resultadoChasideFrecuente = resultadoChasideFrecuente;
	}

	/**
	 * @return the cantidadChasideResultado
	 */
	public String getCantidadChasideResultado() {
		return cantidadChasideResultado;
	}

	/**
	 * @param cantidadChasideResultado the cantidadChasideResultado to set
	 */
	public void setCantidadChasideResultado(String cantidadChasideResultado) {
		this.cantidadChasideResultado = cantidadChasideResultado;
	}

	/**
	 * @return the resultadoHollandFrecuente
	 */
	public String getResultadoHollandFrecuente() {
		return resultadoHollandFrecuente;
	}

	/**
	 * @param resultadoHollandFrecuente the resultadoHollandFrecuente to set
	 */
	public void setResultadoHollandFrecuente(String resultadoHollandFrecuente) {
		this.resultadoHollandFrecuente = resultadoHollandFrecuente;
	}

	/**
	 * @return the cantidadHollandResultado
	 */
	public String getCantidadHollandResultado() {
		return cantidadHollandResultado;
	}

	/**
	 * @param cantidadHollandResultado the cantidadHollandResultado to set
	 */
	public void setCantidadHollandResultado(String cantidadHollandResultado) {
		this.cantidadHollandResultado = cantidadHollandResultado;
	}
}
