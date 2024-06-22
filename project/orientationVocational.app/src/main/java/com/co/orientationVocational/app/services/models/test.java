package com.co.orientationVocational.app.services.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTest;
	
	@NotNull
	private String identificacionUsuario;
	
	@NotNull
	@Column(columnDefinition = "LONGTEXT")
	private String resultadoTest;
	
	@NotNull
	private String tipoTest;
	
	@NotNull
	private String fechaTest;
	
	@NotNull
	@Column(columnDefinition = "LONGTEXT")
	private String observacionTest;
	
	@NotNull
	private String detalle1;
	
	@NotNull
	private String detalle2;
	
	@NotNull
	private String detalle3;

	@NotNull
	private String respuestas;
	
	public test() {}
	
	public test(int idTest, @NotNull String identificacionUsuario, @NotNull String resultadoTest,
			@NotNull String tipoTest, @NotNull String fechaTest, @NotNull String observacionTest,
			@NotNull String detalle1, @NotNull String detalle2, @NotNull String detalle3, @NotNull String respuestas) {
		this.idTest = idTest;
		this.identificacionUsuario = identificacionUsuario;
		this.resultadoTest = resultadoTest;
		this.tipoTest = tipoTest;
		this.fechaTest = fechaTest;
		this.observacionTest = observacionTest;
		this.detalle1 = detalle1;
		this.detalle2 = detalle2;
		this.detalle3 = detalle3;
		this.respuestas = respuestas;
	}

	/**
	 * @return the idTest
	 */
	public int getIdTest() {
		return idTest;
	}

	/**
	 * @param idTest the idTest to set
	 */
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	/**
	 * @return the identificacionUsuario
	 */
	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}

	/**
	 * @param identificacionUsuario the identificacionUsuario to set
	 */
	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}

	/**
	 * @return the resultadoTest
	 */
	public String getResultadoTest() {
		return resultadoTest;
	}

	/**
	 * @param resultadoTest the resultadoTest to set
	 */
	public void setResultadoTest(String resultadoTest) {
		this.resultadoTest = resultadoTest;
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

	/**
	 * @return the fechaTest
	 */
	public String getFechaTest() {
		return fechaTest;
	}

	/**
	 * @param fechaTest the fechaTest to set
	 */
	public void setFechaTest(String fechaTest) {
		this.fechaTest = fechaTest;
	}

	/**
	 * @return the observacionTest
	 */
	public String getObservacionTest() {
		return observacionTest;
	}

	/**
	 * @param observacionTest the observacionTest to set
	 */
	public void setObservacionTest(String observacionTest) {
		this.observacionTest = observacionTest;
	}

	/**
	 * @return the detalle1
	 */
	public String getDetalle1() {
		return detalle1;
	}

	/**
	 * @param detalle1 the detalle1 to set
	 */
	public void setDetalle1(String detalle1) {
		this.detalle1 = detalle1;
	}

	/**
	 * @return the detalle2
	 */
	public String getDetalle2() {
		return detalle2;
	}

	/**
	 * @param detalle2 the detalle2 to set
	 */
	public void setDetalle2(String detalle2) {
		this.detalle2 = detalle2;
	}

	/**
	 * @return the detalle3
	 */
	public String getDetalle3() {
		return detalle3;
	}

	/**
	 * @param detalle3 the detalle3 to set
	 */
	public void setDetalle3(String detalle3) {
		this.detalle3 = detalle3;
	}

	/**
	 * @return the respuestas
	 */
	public String getRespuestas() {
		return respuestas;
	}

	/**
	 * @param respuestas the respuestas to set
	 */
	public void setRespuestas(String respuestas) {
		this.respuestas = respuestas;
	}
}
