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
	private String direccion;
	private String ciudad;
	private String programa;
	private String caracteristicasChaside;
	private String caracteristicasHolland;
	
	public universidades() {}

	public universidades(int idUniversidad, String nombreUniversidad, String urlPagina, String direccion, String ciudad,
			String programa, String caracteristicasChaside, String caracteristicasHolland) {
		this.idUniversidad = idUniversidad;
		this.nombreUniversidad = nombreUniversidad;
		this.urlPagina = urlPagina;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.programa = programa;
		this.caracteristicasChaside = caracteristicasChaside;
		this.caracteristicasHolland = caracteristicasHolland;
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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	/**
	 * @return the programa
	 */
	public String getPrograma() {
		return programa;
	}

	/**
	 * @param programa the programa to set
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	/**
	 * @return the caracteristicasChaside
	 */
	public String getCaracteristicasChaside() {
		return caracteristicasChaside;
	}

	/**
	 * @param caracteristicasChaside the caracteristicasChaside to set
	 */
	public void setCaracteristicasChaside(String caracteristicasChaside) {
		this.caracteristicasChaside = caracteristicasChaside;
	}

	/**
	 * @return the caracteristicasHolland
	 */
	public String getCaracteristicasHolland() {
		return caracteristicasHolland;
	}

	/**
	 * @param caracteristicasHolland the caracteristicasHolland to set
	 */
	public void setCaracteristicasHolland(String caracteristicasHolland) {
		this.caracteristicasHolland = caracteristicasHolland;
	}
}
