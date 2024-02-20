package com.co.orientationVocational.app.services.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class pregunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String descripcionPregunta;
	private String opcion1;
	private String opcion2;
	private String opcion3;
	private String opcion4;
	private String respuesta1;
	private String respuesta2;
	private String tipoTest;
	
	public pregunta() {}
	
	public pregunta(String descripcionPregunta, String opcion1, String opcion2, String opcion3, String opcion4,
			String respuesta1, String respuesta2, String tipoTest) {
		this.descripcionPregunta = descripcionPregunta;
		this.opcion1 = opcion1;
		this.opcion2 = opcion2;
		this.opcion3 = opcion3;
		this.opcion4 = opcion4;
		this.respuesta1 = respuesta1;
		this.respuesta2 = respuesta2;
		this.tipoTest = tipoTest;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the descripcionPregunta
	 */
	public String getDescripcionPregunta() {
		return descripcionPregunta;
	}

	/**
	 * @param descripcionPregunta the descripcionPregunta to set
	 */
	public void setDescripcionPregunta(String descripcionPregunta) {
		this.descripcionPregunta = descripcionPregunta;
	}

	/**
	 * @return the opcion1
	 */
	public String getOpcion1() {
		return opcion1;
	}

	/**
	 * @param opcion1 the opcion1 to set
	 */
	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	/**
	 * @return the opcion2
	 */
	public String getOpcion2() {
		return opcion2;
	}

	/**
	 * @param opcion2 the opcion2 to set
	 */
	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	/**
	 * @return the opcion3
	 */
	public String getOpcion3() {
		return opcion3;
	}

	/**
	 * @param opcion3 the opcion3 to set
	 */
	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	/**
	 * @return the opcion4
	 */
	public String getOpcion4() {
		return opcion4;
	}

	/**
	 * @param opcion4 the opcion4 to set
	 */
	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}

	/**
	 * @return the respuesta1
	 */
	public String getRespuesta1() {
		return respuesta1;
	}

	/**
	 * @param respuesta1 the respuesta1 to set
	 */
	public void setRespuesta1(String respuesta1) {
		this.respuesta1 = respuesta1;
	}

	/**
	 * @return the respuesta2
	 */
	public String getRespuesta2() {
		return respuesta2;
	}

	/**
	 * @param respuesta2 the respuesta2 to set
	 */
	public void setRespuesta2(String respuesta2) {
		this.respuesta2 = respuesta2;
	}

	/**
	 * @return the tipoTest
	 */
	public String getTipoTest() {
		return tipoTest;
	}

	/**
	 * @param tipoTest the tipoTest to set
	 */
	public void setTipoTest(String tipoTest) {
		this.tipoTest = tipoTest;
	}	
}
