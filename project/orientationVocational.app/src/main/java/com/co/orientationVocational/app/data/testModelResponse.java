package com.co.orientationVocational.app.data;

public class testModelResponse {
	private String llave;
	private int resultado;
	private String areaInteres;
	
	public testModelResponse() {}
	
	public testModelResponse(String llave, int resultado, String areaInteres) {
		this.llave = llave;
		this.resultado = resultado;
		this.areaInteres = areaInteres;
	}

	/**
	 * @return the llave
	 */
	public String getLlave() {
		return llave;
	}

	/**
	 * @param llave the llave to set
	 */
	public void setLlave(String llave) {
		this.llave = llave;
	}

	/**
	 * @return the resultado
	 */
	public int getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the areaInteres
	 */
	public String getAreaInteres() {
		return areaInteres;
	}

	/**
	 * @param areaInteres the areaInteres to set
	 */
	public void setAreaInteres(String areaInteres) {
		this.areaInteres = areaInteres;
	}
}
