package com.co.orientationVocational.app.data;

public class testModel {
	private String llave;
	private int resultado;
	
	public testModel() {}
	
	public testModel(String llave, int resultado) {
		this.llave = llave;
		this.resultado = resultado;
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
}
