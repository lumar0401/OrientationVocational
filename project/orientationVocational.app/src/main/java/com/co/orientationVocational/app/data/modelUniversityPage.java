package com.co.orientationVocational.app.data;

public class modelUniversityPage {
	private String id;
	private String nombreUniversidad;
	private String paginaUrl;
	private String direcciones;
	private String requisitos;
	private String puntuacion;
	private String posicion;
	private String fechaRegistro;
	private String programa;
	
	public modelUniversityPage() {}
	
	public modelUniversityPage(String id, String nombreUniversidad, String paginaUrl, String direcciones, String requisitos, String puntuacion, String posicion, String fechaRegistro,
			String programa) {
		this.id = id;
		this.nombreUniversidad = nombreUniversidad;
		this.paginaUrl = paginaUrl;
		this.direcciones = direcciones;
		this.requisitos = requisitos;
		this.puntuacion = puntuacion;
		this.posicion = posicion;
		this.fechaRegistro = fechaRegistro;
		this.programa = programa;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the paginaUrl
	 */
	public String getPaginaUrl() {
		return paginaUrl;
	}

	/**
	 * @param paginaUrl the paginaUrl to set
	 */
	public void setPaginaUrl(String paginaUrl) {
		this.paginaUrl = paginaUrl;
	}

	/**
	 * @return the direcciones
	 */
	public String getDirecciones() {
		return direcciones;
	}

	/**
	 * @param direcciones the direcciones to set
	 */
	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
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
}
