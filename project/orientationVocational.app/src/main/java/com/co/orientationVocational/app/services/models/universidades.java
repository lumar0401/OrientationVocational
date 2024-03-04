package com.co.orientationVocational.app.services.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class universidades {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUniversidad;
	
	private String nombreUniversidad;
	private String urlPagina;
	private String ciudad; 
	
	public universidades() {}

	public universidades(int idUniversidad, String nombreUniversidad, String urlPagina, String ciudad) {
		this.idUniversidad = idUniversidad;
		this.nombreUniversidad = nombreUniversidad;
		this.urlPagina = urlPagina;
		this.ciudad = ciudad;
	}

	/**
	 * @return the idUniversidad
	 */
	public int getIdUniversidad() {
		return idUniversidad;
	}

	/**
	 * @param idUniversidad the idUniversidad to set
	 */
	public void setIdUniversidad(int idUniversidad) {
		this.idUniversidad = idUniversidad;
	}

	/**
	 * @return the nombreUniversidad
	 */
	public String getNombreUniversidad() {
		return nombreUniversidad;
	}

	/**
	 * @param nombreUniversidad the nombreUniversidad to set
	 */
	public void setNombreUniversidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
	}

	/**
	 * @return the urlPagina
	 */
	public String getUrlPagina() {
		return urlPagina;
	}

	/**
	 * @param urlPagina the urlPagina to set
	 */
	public void setUrlPagina(String urlPagina) {
		this.urlPagina = urlPagina;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}
