package com.co.orientationVocational.app.services.models;

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
	private String resultadoTest;
	
	@NotNull
	private String tipoTest;
	
	@NotNull
	private String fechaTest;
	
	@NotNull
	private String observacionTest;
	
	public test() {}

	public test(int idTest, @NotNull String identificacionUsuario, @NotNull String resultadoTest,
			@NotNull String tipoTest, @NotNull String fechaTest, @NotNull String observacionTest) {
		this.idTest = idTest;
		this.identificacionUsuario = identificacionUsuario;
		this.resultadoTest = resultadoTest;
		this.tipoTest = tipoTest;
		this.fechaTest = fechaTest;
		this.observacionTest = observacionTest;
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
}
