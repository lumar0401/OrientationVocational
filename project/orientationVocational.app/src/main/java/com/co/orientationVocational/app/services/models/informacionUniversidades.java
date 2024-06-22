package com.co.orientationVocational.app.services.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class informacionUniversidades {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInfoUniversidades;
	
	private String idUniversidad;
	private String programa;
	private String titulo;
	private String semestres;
	private String valorSemestre;
	private String modalidad;
	private String directorPrograma;
	
	public informacionUniversidades() {}

	public informacionUniversidades(int idInfoUniversidades, String idUniversidad, String programa, String titulo, String semestres, String valorSemestre,
			String modalidad, String directorPrograma) {
		this.idInfoUniversidades = idInfoUniversidades;
		this.idUniversidad = idUniversidad;
		this.programa = programa;
		this.titulo = titulo;
		this.semestres = semestres;
		this.valorSemestre = valorSemestre;
		this.modalidad = modalidad;
		this.directorPrograma = directorPrograma;
	}

	/**
	 * @return the idInfoUniversidades
	 */
	public int getIdInfoUniversidades() {
		return idInfoUniversidades;
	}

	/**
	 * @param idInfoUniversidades the idInfoUniversidades to set
	 */
	public void setIdInfoUniversidades(int idInfoUniversidades) {
		this.idInfoUniversidades = idInfoUniversidades;
	}

	/**
	 * @return the idUniversidad
	 */
	public String getIdUniversidad() {
		return idUniversidad;
	}

	/**
	 * @param idUniversidad the idUniversidad to set
	 */
	public void setIdUniversidad(String idUniversidad) {
		this.idUniversidad = idUniversidad;
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
}
