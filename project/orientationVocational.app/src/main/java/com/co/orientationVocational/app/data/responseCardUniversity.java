package com.co.orientationVocational.app.data;

import java.util.List;

public class responseCardUniversity {
	private String titulo;
	private String semestres;
	private String valorSemestre;
	private String modalidad;
	private String directorPrograma;
	private List<String> requisitos;
	private List<String> direcciones;
	
	public responseCardUniversity() {}

	public responseCardUniversity(String titulo, String semestres, String valorSemestre, String modalidad, 
			String directorPrograma, List<String> requisitos, List<String> direcciones) {
		this.titulo = titulo;
		this.semestres = semestres;
		this.valorSemestre = valorSemestre;
		this.modalidad = modalidad;
		this.directorPrograma = directorPrograma;
		this.requisitos = requisitos;
		this.direcciones = direcciones;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the semestres
	 */
	public String getSemestres() {
		return semestres;
	}

	/**
	 * @param semestres the semestres to set
	 */
	public void setSemestres(String semestres) {
		this.semestres = semestres;
	}

	/**
	 * @return the valorSemestre
	 */
	public String getValorSemestre() {
		return valorSemestre;
	}

	/**
	 * @param valorSemestre the valorSemestre to set
	 */
	public void setValorSemestre(String valorSemestre) {
		this.valorSemestre = valorSemestre;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * @return the directorPrograma
	 */
	public String getDirectorPrograma() {
		return directorPrograma;
	}

	/**
	 * @param directorPrograma the directorPrograma to set
	 */
	public void setDirectorPrograma(String directorPrograma) {
		this.directorPrograma = directorPrograma;
	}

	/**
	 * @return the requisitos
	 */
	public List<String> getRequisitos() {
		return requisitos;
	}

	/**
	 * @param requisitos the requisitos to set
	 */
	public void setRequisitos(List<String> requisitos) {
		this.requisitos = requisitos;
	}

	/**
	 * @return the direcciones
	 */
	public List<String> getDirecciones() {
		return direcciones;
	}

	/**
	 * @param direcciones the direcciones to set
	 */
	public void setDirecciones(List<String> direcciones) {
		this.direcciones = direcciones;
	}
}
