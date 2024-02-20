package com.co.orientationVocational.app.services.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class logUsuarioDto {
	@NotNull
	private String identificacion;
	
	@Column(columnDefinition = "LONGTEXT")
	private String Registro;
	
	@Column(columnDefinition = "LONGTEXT")
	private String camposViejos;
	
	@Column(columnDefinition = "LONGTEXT")
	private String camposNuevos;
	private String operacion;
	private String fechaRegistro;
	
	public logUsuarioDto() {}

	public logUsuarioDto(@NotNull String identificacion, String registro, String camposViejos, String camposNuevos,
			String operacion, String fechaRegistro) {
		this.identificacion = identificacion;
		Registro = registro;
		this.camposViejos = camposViejos;
		this.camposNuevos = camposNuevos;
		this.operacion = operacion;
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the registro
	 */
	public String getRegistro() {
		return Registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(String registro) {
		Registro = registro;
	}

	/**
	 * @return the camposViejos
	 */
	public String getCamposViejos() {
		return camposViejos;
	}

	/**
	 * @param camposViejos the camposViejos to set
	 */
	public void setCamposViejos(String camposViejos) {
		this.camposViejos = camposViejos;
	}

	/**
	 * @return the camposNuevos
	 */
	public String getCamposNuevos() {
		return camposNuevos;
	}

	/**
	 * @param camposNuevos the camposNuevos to set
	 */
	public void setCamposNuevos(String camposNuevos) {
		this.camposNuevos = camposNuevos;
	}

	/**
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return the fechaRegistro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}
