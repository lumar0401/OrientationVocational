package com.co.orientationVocational.app.security.entity;

import com.co.orientationVocational.app.security.enums.rolNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identificador;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private rolNombre descripcion;
	
	public rol() {}

	public rol(@NotNull rolNombre descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the identificador
	 */
	public int getIdentificador() {
		return identificador;
	}

	/**
	 * @param idRol the identificador to set
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the descripcionRol
	 */
	public rolNombre getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcionRol the descripcionRol to set
	 */
	public void setDescripcionRol(rolNombre descripcion) {
		this.descripcion = descripcion;
	}
}
