package com.co.orientationVocational.app.services.dto;

public class icfesDto {
	private String identificacion;
	private String registro;
	private String lectura_critica;
	private String matematicas;
	private String sociales;
	private String ciencias_naturales;
	private String ingles;
	private String razonamiento;
	private String competencias;
	
	public icfesDto() {}

	public icfesDto(String identificacion, String registro, String lectura_critica, String matematicas, String sociales,
			String ciencias_naturales, String ingles, String razonamiento, String competencias) {
		this.identificacion = identificacion;
		this.registro = registro;
		this.lectura_critica = lectura_critica;
		this.matematicas = matematicas;
		this.sociales = sociales;
		this.ciencias_naturales = ciencias_naturales;
		this.ingles = ingles;
		this.razonamiento = razonamiento;
		this.competencias = competencias;
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
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * @return the lectura_critica
	 */
	public String getLectura_critica() {
		return lectura_critica;
	}

	/**
	 * @param lectura_critica the lectura_critica to set
	 */
	public void setLectura_critica(String lectura_critica) {
		this.lectura_critica = lectura_critica;
	}

	/**
	 * @return the matematicas
	 */
	public String getMatematicas() {
		return matematicas;
	}

	/**
	 * @param matematicas the matematicas to set
	 */
	public void setMatematicas(String matematicas) {
		this.matematicas = matematicas;
	}

	/**
	 * @return the sociales
	 */
	public String getSociales() {
		return sociales;
	}

	/**
	 * @param sociales the sociales to set
	 */
	public void setSociales(String sociales) {
		this.sociales = sociales;
	}

	/**
	 * @return the ciencias_naturales
	 */
	public String getCiencias_naturales() {
		return ciencias_naturales;
	}

	/**
	 * @param ciencias_naturales the ciencias_naturales to set
	 */
	public void setCiencias_naturales(String ciencias_naturales) {
		this.ciencias_naturales = ciencias_naturales;
	}

	/**
	 * @return the ingles
	 */
	public String getIngles() {
		return ingles;
	}

	/**
	 * @param ingles the ingles to set
	 */
	public void setIngles(String ingles) {
		this.ingles = ingles;
	}

	/**
	 * @return the razonamiento
	 */
	public String getRazonamiento() {
		return razonamiento;
	}

	/**
	 * @param razonamiento the razonamiento to set
	 */
	public void setRazonamiento(String razonamiento) {
		this.razonamiento = razonamiento;
	}

	/**
	 * @return the competencias
	 */
	public String getCompetencias() {
		return competencias;
	}

	/**
	 * @param competencias the competencias to set
	 */
	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}
}
