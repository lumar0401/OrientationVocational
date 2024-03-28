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
	private String categoria;
	private String direccion;
	private String carrera;
	private String ciudad; 
	
	public universidades() {}

	public universidades(int idUniversidad, String nombreUniversidad, String urlPagina, String ciudad, String categoria, String direccion,
			String carrera) {
		this.idUniversidad = idUniversidad;
		this.nombreUniversidad = nombreUniversidad;
		this.urlPagina = urlPagina;
		this.categoria = categoria;
		this.direccion = direccion;
		this.carrera = carrera;
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

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	 * @return the carrera
	 */
	public String getCarrera() {
		return carrera;
	}

	/**
	 * @param carrera the carrera to set
	 */
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
}
