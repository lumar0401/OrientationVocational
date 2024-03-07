package com.co.orientationVocational.app.services.dto;

import javax.persistence.Column;

import org.springframework.lang.NonNull;

public class universidadesDto {
	@NonNull
	private int idUniversidad;
	
	@Column(columnDefinition = "LONGTEXT")
	private String nombreUniversidad;
	
	@Column(columnDefinition = "LONGTEXT")
	private String urlPagina;
	
	@NonNull
	private String ciudad;

	public universidadesDto() {}
	
	public universidadesDto(int idUniversidad, String nombreUniversidad, String urlPagina, String ciudad) {
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
