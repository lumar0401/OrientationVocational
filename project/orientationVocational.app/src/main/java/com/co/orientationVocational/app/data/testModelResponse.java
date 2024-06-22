package com.co.orientationVocational.app.data;

public class testModelResponse {
	private String llave;
	private int resultado;
	private String areaInteres;
	private String caracteristicas;
	private String mensaje;
	
	public testModelResponse() {}
	
	public testModelResponse(String llave, int resultado, String areaInteres, String caracteristicas, String mensaje) {
		this.llave = llave;
		this.resultado = resultado;
		this.areaInteres = areaInteres;
		this.caracteristicas = caracteristicas;
		this.mensaje = mensaje;
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

	/**
	 * @return the caracteristicas
	 */
	public String getCaracteristicas() {
		return caracteristicas;
	}

	/**
	 * @param caracteristicas the caracteristicas to set
	 */
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}