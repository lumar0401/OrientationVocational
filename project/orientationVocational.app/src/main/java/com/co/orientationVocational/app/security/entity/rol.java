package com.co.orientationVocational.app.security.entity;

import com.co.orientationVocational.app.security.enums.rolNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private rolNombre descripcion;
	
	public rol() {}

	public rol(@NotNull rolNombre descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the idRol
	 */
	public int getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(int idRol) {
		this.idRol = idRol;
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
