package com.co.orientationVocational.app.services.models;

import javax.persistence.Column;
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
	
	@Column(columnDefinition = "LONGTEXT")
	private String direccion;
	
	@Column(columnDefinition = "LONGTEXT")
	private String requisitos;
	
	private String ciudad;
	private String programa;
	private String caracteristicasChaside;
	private String caracteristicasHolland;
	private String puntuacion;
	private String posicion;
	private String fechaRegistro;
	
	public universidades() {}

	public universidades(int idUniversidad, String nombreUniversidad, String urlPagina, String direccion, String requisitos, 
			String ciudad, String programa, String caracteristicasChaside, String caracteristicasHolland, String puntuacion,
			String posicion, String fechaRegistro) {
		this.idUniversidad = idUniversidad;
		this.nombreUniversidad = nombreUniversidad;
		this.urlPagina = urlPagina;
		this.direccion = direccion;
		this.requisitos= requisitos;
		this.ciudad = ciudad;
		this.programa = programa;
		this.caracteristicasChaside = caracteristicasChaside;
		this.caracteristicasHolland = caracteristicasHolland;
		this.puntuacion = puntuacion;
		this.posicion = posicion;
		this.fechaRegistro = fechaRegistro;
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
	 * @return the requisitos
	 */
	public String getRequisitos() {
		return requisitos;
	}

	/**
	 * @param requisitos the requisitos to set
	 */
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
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

	/**
	 * @return the puntuacion
	 */
	public String getPuntuacion() {
		return puntuacion;
	}

	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * @return the posicion
	 */
	public String getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
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
