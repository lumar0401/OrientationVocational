package com.co.orientationVocational.app.services.models;

public class usuario {
	String nombres;
	String identificacion;
	String contrasena;
	
	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}
	
	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}
	
	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}
	
	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public usuario(String nombres, String identificacion, String contrasena) {
		super();
		this.nombres = nombres;
		this.identificacion = identificacion;
		this.contrasena = contrasena;
	}

	public usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "usuario [nombres=" + nombres + ", identificacion=" + identificacion + ", contrasena=" + contrasena
				+ "]";
	}
}
