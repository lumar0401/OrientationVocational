package com.co.orientationVocational.app.services.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ubicacionUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUbicacionUsuario;
	
	private String identificacionUsuario;
	private String latitud;
	private String longitud;
	
	public ubicacionUsuario() {}

	public ubicacionUsuario(int idUbicacionUsuario, String identificacionUsuario, String latitud, String longitud) {
		this.idUbicacionUsuario = idUbicacionUsuario;
		this.identificacionUsuario = identificacionUsuario;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	/**
	 * @return the idUbicacionUsuario
	 */
	public int getIdUbicacionUsuario() {
		return idUbicacionUsuario;
	}

	/**
	 * @param idUbicacionUsuario the idUbicacionUsuario to set
	 */
	public void setIdUbicacionUsuario(int idUbicacionUsuario) {
		this.idUbicacionUsuario = idUbicacionUsuario;
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
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
}
