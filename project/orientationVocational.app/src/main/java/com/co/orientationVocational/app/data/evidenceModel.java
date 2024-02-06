package com.co.orientationVocational.app.data;

public class evidenceModel {
	private String prueba;
	private String puntaje;
	private String nivel;
	private String decil;
	
	public evidenceModel() {}

	public evidenceModel(String prueba, String puntaje, String nivel, String decil) {
		this.prueba = prueba;
		this.puntaje = puntaje;
		this.nivel = nivel;
		this.decil = decil;
	}

	/**
	 * @return the prueba
	 */
	public String getPrueba() {
		return prueba;
	}

	/**
	 * @param prueba the prueba to set
	 */
	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}

	/**
	 * @return the puntaje
	 */
	public String getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje the puntaje to set
	 */
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the decil
	 */
	public String getDecil() {
		return decil;
	}

	/**
	 * @param decil the decil to set
	 */
	public void setDecil(String decil) {
		this.decil = decil;
	}
}
