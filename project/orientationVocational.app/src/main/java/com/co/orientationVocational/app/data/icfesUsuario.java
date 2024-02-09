package com.co.orientationVocational.app.data;

import java.util.LinkedList;

public class icfesUsuario {
	private String nombreEstudiante;
	private String identificacionUsuario;
	private String registroExamen;
	private String fechaExamen;
	private LinkedList<evidenceModel> listaMaterias;
	private int puntajeGlobal;
	
	public icfesUsuario() {}
	
	public icfesUsuario(String nombreEstudiante, String identificacionUsuario, String registroExamen,
			String fechaExamen, LinkedList<evidenceModel> listaMaterias, int puntajeGlobal) {
		this.nombreEstudiante = nombreEstudiante;
		this.identificacionUsuario = identificacionUsuario;
		this.registroExamen = registroExamen;
		this.fechaExamen = fechaExamen;
		this.listaMaterias = listaMaterias;
		this.puntajeGlobal = puntajeGlobal;
	}

	/**
	 * @return the nombreEstudiante
	 */
	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	/**
	 * @param nombreEstudiante the nombreEstudiante to set
	 */
	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
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
	 * @return the registroExamen
	 */
	public String getRegistroExamen() {
		return registroExamen;
	}

	/**
	 * @param registroExamen the registroExamen to set
	 */
	public void setRegistroExamen(String registroExamen) {
		this.registroExamen = registroExamen;
	}

	/**
	 * @return the fechaExamen
	 */
	public String getFechaExamen() {
		return fechaExamen;
	}

	/**
	 * @param fechaExamen the fechaExamen to set
	 */
	public void setFechaExamen(String fechaExamen) {
		this.fechaExamen = fechaExamen;
	}

	/**
	 * @return the listaMaterias
	 */
	public LinkedList<evidenceModel> getListaMaterias() {
		return listaMaterias;
	}

	/**
	 * @param listaMaterias the listaMaterias to set
	 */
	public void setListaMaterias(LinkedList<evidenceModel> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}

	/**
	 * @return the puntajeGlobal
	 */
	public int getPuntajeGlobal() {
		return puntajeGlobal;
	}

	/**
	 * @param puntajeGlobal the puntajeGlobal to set
	 */
	public void setPuntajeGlobal(int puntajeGlobal) {
		this.puntajeGlobal = puntajeGlobal;
	}
}
