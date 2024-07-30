package com.co.orientationVocational.app.services.dto;

public class icfesDto {
	private String identificacion;
	private String registro;
	private String puntajeCiencias;
	private String puntajeCompetencias;
	private String puntajeIngles;
	private String puntajeLectura;
	private String puntajeMatematicas;
	private String puntajeRazonamiento;
	private String puntajeSociales;
	
	public icfesDto() {}

	public icfesDto(String identificacion, String registro, String puntajeCiencias, String puntajeCompetencias,
			String puntajeIngles, String puntajeLectura, String puntajeMatematicas, String puntajeRazonamiento,
			String puntajeSociales) {
		this.identificacion = identificacion;
		this.registro = registro;
		this.puntajeCiencias = puntajeCiencias;
		this.puntajeCompetencias = puntajeCompetencias;
		this.puntajeIngles = puntajeIngles;
		this.puntajeLectura = puntajeLectura;
		this.puntajeMatematicas = puntajeMatematicas;
		this.puntajeRazonamiento = puntajeRazonamiento;
		this.puntajeSociales = puntajeSociales;
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
	 * @return the puntajeCiencias
	 */
	public String getPuntajeCiencias() {
		return puntajeCiencias;
	}

	/**
	 * @param puntajeCiencias the puntajeCiencias to set
	 */
	public void setPuntajeCiencias(String puntajeCiencias) {
		this.puntajeCiencias = puntajeCiencias;
	}

	/**
	 * @return the puntajeCompetencias
	 */
	public String getPuntajeCompetencias() {
		return puntajeCompetencias;
	}

	/**
	 * @param puntajeCompetencias the puntajeCompetencias to set
	 */
	public void setPuntajeCompetencias(String puntajeCompetencias) {
		this.puntajeCompetencias = puntajeCompetencias;
	}

	/**
	 * @return the puntajeIngles
	 */
	public String getPuntajeIngles() {
		return puntajeIngles;
	}

	/**
	 * @param puntajeIngles the puntajeIngles to set
	 */
	public void setPuntajeIngles(String puntajeIngles) {
		this.puntajeIngles = puntajeIngles;
	}

	/**
	 * @return the puntajeLectura
	 */
	public String getPuntajeLectura() {
		return puntajeLectura;
	}

	/**
	 * @param puntajeLectura the puntajeLectura to set
	 */
	public void setPuntajeLectura(String puntajeLectura) {
		this.puntajeLectura = puntajeLectura;
	}

	/**
	 * @return the puntajeMatematicas
	 */
	public String getPuntajeMatematicas() {
		return puntajeMatematicas;
	}

	/**
	 * @param puntajeMatematicas the puntajeMatematicas to set
	 */
	public void setPuntajeMatematicas(String puntajeMatematicas) {
		this.puntajeMatematicas = puntajeMatematicas;
	}

	/**
	 * @return the puntajeRazonamiento
	 */
	public String getPuntajeRazonamiento() {
		return puntajeRazonamiento;
	}

	/**
	 * @param puntajeRazonamiento the puntajeRazonamiento to set
	 */
	public void setPuntajeRazonamiento(String puntajeRazonamiento) {
		this.puntajeRazonamiento = puntajeRazonamiento;
	}

	/**
	 * @return the puntajeSociales
	 */
	public String getPuntajeSociales() {
		return puntajeSociales;
	}

	/**
	 * @param puntajeSociales the puntajeSociales to set
	 */
	public void setPuntajeSociales(String puntajeSociales) {
		this.puntajeSociales = puntajeSociales;
	}
}
