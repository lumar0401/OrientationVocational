package com.co.orientationVocational.app.data;

import java.util.LinkedList;

public class respuestaUniversidades {
	private LinkedList<responseCardUniversity> cardsUniversidades;
	private LinkedList<testModelResponse> respuestaBackUp;
	private String categorias;
	private String test;
	private int cantidadUniversidades;
	private boolean status;
	
	public respuestaUniversidades() {}

	public respuestaUniversidades(LinkedList<responseCardUniversity> cardsUniversidades, LinkedList<testModelResponse> respuestaBackUp, String categorias, String test,
			int cantidadUniversidades, boolean status) {
		this.cardsUniversidades = cardsUniversidades;
		this.respuestaBackUp = respuestaBackUp;
		this.categorias = categorias;
		this.test = test;
		this.cantidadUniversidades = cantidadUniversidades;
		this.status = status;
	}

	/**
	 * @return the cardsUniversidades
	 */
	public LinkedList<responseCardUniversity> getCardsUniversidades() {
		return cardsUniversidades;
	}

	/**
	 * @param cardsUniversidades the cardsUniversidades to set
	 */
	public void setCardsUniversidades(LinkedList<responseCardUniversity> cardsUniversidades) {
		this.cardsUniversidades = cardsUniversidades;
	}

	/**
	 * @return the respuestaBackUp
	 */
	public LinkedList<testModelResponse> getRespuestaBackUp() {
		return respuestaBackUp;
	}

	/**
	 * @param respuestaBackUp the respuestaBackUp to set
	 */
	public void setRespuestaBackUp(LinkedList<testModelResponse> respuestaBackUp) {
		this.respuestaBackUp = respuestaBackUp;
	}

	/**
	 * @return the categorias
	 */
	public String getCategorias() {
		return categorias;
	}

	/**
	 * @param categorias the categorias to set
	 */
	public void setCategorias(String categorias) {
		this.categorias = categorias;
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
	 * @return the cantidadUniversidades
	 */
	public int getCantidadUniversidades() {
		return cantidadUniversidades;
	}

	/**
	 * @param cantidadUniversidades the cantidadUniversidades to set
	 */
	public void setCantidadUniversidades(int cantidadUniversidades) {
		this.cantidadUniversidades = cantidadUniversidades;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
