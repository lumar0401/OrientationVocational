package com.co.orientationVocational.app.data;

public class modelUniversityPage {
	private String id;
	private String paginaUrl;
	private String direcciones;
	private String requisitos;
	
	public modelUniversityPage() {}
	
	public modelUniversityPage(String id, String paginaUrl, String direcciones, String requisitos) {
		this.id = id;
		this.paginaUrl = paginaUrl;
		this.direcciones = direcciones;
		this.requisitos = requisitos;
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
}
