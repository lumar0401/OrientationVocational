package com.co.orientationVocational.app.services.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class usuarioIcfes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String identificacionUsuario;

	@NotNull
	private String apellidosNombres;

	@NotNull
	private String RegistroIcfes;

	@NotNull
	private String fechaExamen;

	@NotNull
	private String lecturaPrueba;

	@NotNull
	private int lecturaPuntaje;
	
	@NotNull
	private String lecturaDecil;
	
	@NotNull
	private String matematicasPrueba;
	
	@NotNull
	private int matematicasPuntaje;
	
	@NotNull
	private String matematicasDecil;
	
	@NotNull
	private String socialesPrueba;
	
	@NotNull
	private int socialesPuntaje;
	
	@NotNull
	private String socialesDecil;
	
	@NotNull
	private String cienciasPrueba;
	
	@NotNull
	private int cienciasPuntaje;
	
	@NotNull
	private String cienciasDecil;
	
	@NotNull
	private String inglesPrueba;
	
	@NotNull
	private int inglesPuntaje;
	
	@NotNull
	private String inglesNivel;
	
	@NotNull
	private String inglesDecil;
	
	@NotNull
	private String razonamientoPrueba;
	
	@NotNull
	private int razonamientoPuntaje;
	
	@NotNull
	private String razonamientoDecil;
	
	@NotNull
	private String competenciasPrueba;
	
	@NotNull
	private int competenciasPuntaje;
	
	@NotNull
	private String competenciasDecil;
	
	@NotNull
	private int puntajeGlobal;
	
	public usuarioIcfes() {}

	public usuarioIcfes(int id, @NotNull String identificacionUsuario, @NotNull String apellidosNombres,
			@NotNull String registroIcfes, @NotNull String fechaExamen, @NotNull String lecturaPrueba,
			@NotNull int lecturaPuntaje, @NotNull String lecturaDecil, @NotNull String matematicasPrueba,
			@NotNull int matematicasPuntaje, @NotNull String matematicasDecil, @NotNull String socialesPrueba,
			@NotNull int socialesPuntaje, @NotNull String socialesDecil, @NotNull String cienciasPrueba,
			@NotNull int cienciasPuntaje, @NotNull String cienciasDecil, @NotNull String inglesPrueba,
			@NotNull int inglesPuntaje, @NotNull String inglesNivel, @NotNull String inglesDecil,
			@NotNull String razonamientoPrueba, @NotNull int razonamientoPuntaje, @NotNull String razonamientoDecil,
			@NotNull String competenciasPrueba, @NotNull int competenciasPuntaje, @NotNull String competenciasDecil,
			@NotNull int puntajeGlobal) {
		this.id = id;
		this.identificacionUsuario = identificacionUsuario;
		this.apellidosNombres = apellidosNombres;
		RegistroIcfes = registroIcfes;
		this.fechaExamen = fechaExamen;
		this.lecturaPrueba = lecturaPrueba;
		this.lecturaPuntaje = lecturaPuntaje;
		this.lecturaDecil = lecturaDecil;
		this.matematicasPrueba = matematicasPrueba;
		this.matematicasPuntaje = matematicasPuntaje;
		this.matematicasDecil = matematicasDecil;
		this.socialesPrueba = socialesPrueba;
		this.socialesPuntaje = socialesPuntaje;
		this.socialesDecil = socialesDecil;
		this.cienciasPrueba = cienciasPrueba;
		this.cienciasPuntaje = cienciasPuntaje;
		this.cienciasDecil = cienciasDecil;
		this.inglesPrueba = inglesPrueba;
		this.inglesPuntaje = inglesPuntaje;
		this.inglesNivel = inglesNivel;
		this.inglesDecil = inglesDecil;
		this.razonamientoPrueba = razonamientoPrueba;
		this.razonamientoPuntaje = razonamientoPuntaje;
		this.razonamientoDecil = razonamientoDecil;
		this.competenciasPrueba = competenciasPrueba;
		this.competenciasPuntaje = competenciasPuntaje;
		this.competenciasDecil = competenciasDecil;
		this.puntajeGlobal = puntajeGlobal;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the apellidosNombres
	 */
	public String getApellidosNombres() {
		return apellidosNombres;
	}

	/**
	 * @param apellidosNombres the apellidosNombres to set
	 */
	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	/**
	 * @return the registroIcfes
	 */
	public String getRegistroIcfes() {
		return RegistroIcfes;
	}

	/**
	 * @param registroIcfes the registroIcfes to set
	 */
	public void setRegistroIcfes(String registroIcfes) {
		RegistroIcfes = registroIcfes;
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
	 * @return the lecturaPrueba
	 */
	public String getLecturaPrueba() {
		return lecturaPrueba;
	}

	/**
	 * @param lecturaPrueba the lecturaPrueba to set
	 */
	public void setLecturaPrueba(String lecturaPrueba) {
		this.lecturaPrueba = lecturaPrueba;
	}

	/**
	 * @return the lecturaPuntaje
	 */
	public int getLecturaPuntaje() {
		return lecturaPuntaje;
	}

	/**
	 * @param lecturaPuntaje the lecturaPuntaje to set
	 */
	public void setLecturaPuntaje(int lecturaPuntaje) {
		this.lecturaPuntaje = lecturaPuntaje;
	}

	/**
	 * @return the lecturaDecil
	 */
	public String getLecturaDecil() {
		return lecturaDecil;
	}

	/**
	 * @param lecturaDecil the lecturaDecil to set
	 */
	public void setLecturaDecil(String lecturaDecil) {
		this.lecturaDecil = lecturaDecil;
	}

	/**
	 * @return the matematicasPrueba
	 */
	public String getMatematicasPrueba() {
		return matematicasPrueba;
	}

	/**
	 * @param matematicasPrueba the matematicasPrueba to set
	 */
	public void setMatematicasPrueba(String matematicasPrueba) {
		this.matematicasPrueba = matematicasPrueba;
	}

	/**
	 * @return the matematicasPuntaje
	 */
	public int getMatematicasPuntaje() {
		return matematicasPuntaje;
	}

	/**
	 * @param matematicasPuntaje the matematicasPuntaje to set
	 */
	public void setMatematicasPuntaje(int matematicasPuntaje) {
		this.matematicasPuntaje = matematicasPuntaje;
	}

	/**
	 * @return the matematicasDecil
	 */
	public String getMatematicasDecil() {
		return matematicasDecil;
	}

	/**
	 * @param matematicasDecil the matematicasDecil to set
	 */
	public void setMatematicasDecil(String matematicasDecil) {
		this.matematicasDecil = matematicasDecil;
	}

	/**
	 * @return the socialesPrueba
	 */
	public String getSocialesPrueba() {
		return socialesPrueba;
	}

	/**
	 * @param socialesPrueba the socialesPrueba to set
	 */
	public void setSocialesPrueba(String socialesPrueba) {
		this.socialesPrueba = socialesPrueba;
	}

	/**
	 * @return the socialesPuntaje
	 */
	public int getSocialesPuntaje() {
		return socialesPuntaje;
	}

	/**
	 * @param socialesPuntaje the socialesPuntaje to set
	 */
	public void setSocialesPuntaje(int socialesPuntaje) {
		this.socialesPuntaje = socialesPuntaje;
	}

	/**
	 * @return the socialesDecil
	 */
	public String getSocialesDecil() {
		return socialesDecil;
	}

	/**
	 * @param socialesDecil the socialesDecil to set
	 */
	public void setSocialesDecil(String socialesDecil) {
		this.socialesDecil = socialesDecil;
	}

	/**
	 * @return the cienciasPrueba
	 */
	public String getCienciasPrueba() {
		return cienciasPrueba;
	}

	/**
	 * @param cienciasPrueba the cienciasPrueba to set
	 */
	public void setCienciasPrueba(String cienciasPrueba) {
		this.cienciasPrueba = cienciasPrueba;
	}

	/**
	 * @return the cienciasPuntaje
	 */
	public int getCienciasPuntaje() {
		return cienciasPuntaje;
	}

	/**
	 * @param cienciasPuntaje the cienciasPuntaje to set
	 */
	public void setCienciasPuntaje(int cienciasPuntaje) {
		this.cienciasPuntaje = cienciasPuntaje;
	}

	/**
	 * @return the cienciasDecil
	 */
	public String getCienciasDecil() {
		return cienciasDecil;
	}

	/**
	 * @param cienciasDecil the cienciasDecil to set
	 */
	public void setCienciasDecil(String cienciasDecil) {
		this.cienciasDecil = cienciasDecil;
	}

	/**
	 * @return the inglesPrueba
	 */
	public String getInglesPrueba() {
		return inglesPrueba;
	}

	/**
	 * @param inglesPrueba the inglesPrueba to set
	 */
	public void setInglesPrueba(String inglesPrueba) {
		this.inglesPrueba = inglesPrueba;
	}

	/**
	 * @return the inglesPuntaje
	 */
	public int getInglesPuntaje() {
		return inglesPuntaje;
	}

	/**
	 * @param inglesPuntaje the inglesPuntaje to set
	 */
	public void setInglesPuntaje(int inglesPuntaje) {
		this.inglesPuntaje = inglesPuntaje;
	}

	/**
	 * @return the inglesNivel
	 */
	public String getInglesNivel() {
		return inglesNivel;
	}

	/**
	 * @param inglesNivel the inglesNivel to set
	 */
	public void setInglesNivel(String inglesNivel) {
		this.inglesNivel = inglesNivel;
	}

	/**
	 * @return the inglesDecil
	 */
	public String getInglesDecil() {
		return inglesDecil;
	}

	/**
	 * @param inglesDecil the inglesDecil to set
	 */
	public void setInglesDecil(String inglesDecil) {
		this.inglesDecil = inglesDecil;
	}

	/**
	 * @return the razonamientoPrueba
	 */
	public String getRazonamientoPrueba() {
		return razonamientoPrueba;
	}

	/**
	 * @param razonamientoPrueba the razonamientoPrueba to set
	 */
	public void setRazonamientoPrueba(String razonamientoPrueba) {
		this.razonamientoPrueba = razonamientoPrueba;
	}

	/**
	 * @return the razonamientoPuntaje
	 */
	public int getRazonamientoPuntaje() {
		return razonamientoPuntaje;
	}

	/**
	 * @param razonamientoPuntaje the razonamientoPuntaje to set
	 */
	public void setRazonamientoPuntaje(int razonamientoPuntaje) {
		this.razonamientoPuntaje = razonamientoPuntaje;
	}

	/**
	 * @return the razonamientoDecil
	 */
	public String getRazonamientoDecil() {
		return razonamientoDecil;
	}

	/**
	 * @param razonamientoDecil the razonamientoDecil to set
	 */
	public void setRazonamientoDecil(String razonamientoDecil) {
		this.razonamientoDecil = razonamientoDecil;
	}

	/**
	 * @return the competenciasPrueba
	 */
	public String getCompetenciasPrueba() {
		return competenciasPrueba;
	}

	/**
	 * @param competenciasPrueba the competenciasPrueba to set
	 */
	public void setCompetenciasPrueba(String competenciasPrueba) {
		this.competenciasPrueba = competenciasPrueba;
	}

	/**
	 * @return the competenciasPuntaje
	 */
	public int getCompetenciasPuntaje() {
		return competenciasPuntaje;
	}

	/**
	 * @param competenciasPuntaje the competenciasPuntaje to set
	 */
	public void setCompetenciasPuntaje(int competenciasPuntaje) {
		this.competenciasPuntaje = competenciasPuntaje;
	}

	/**
	 * @return the competenciasDecil
	 */
	public String getCompetenciasDecil() {
		return competenciasDecil;
	}

	/**
	 * @param competenciasDecil the competenciasDecil to set
	 */
	public void setCompetenciasDecil(String competenciasDecil) {
		this.competenciasDecil = competenciasDecil;
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
