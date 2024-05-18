package com.co.orientationVocational.app.services.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class metadatosHtml {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMetadatos;
	
	private String idUniversidad;
	
	@Column(columnDefinition = "LONGTEXT")
	private String metadatosWeb;
	
	public metadatosHtml() {}
	
	public metadatosHtml(int idMetadatos, String idUniversidad, String metadatosWeb) {
		this.idMetadatos = idMetadatos;
		this.idUniversidad = idUniversidad;
		this.metadatosWeb = metadatosWeb;
	}

	/**
	 * @return the idMetadatos
	 */
	public int getIdMetadatos() {
		return idMetadatos;
	}

	/**
	 * @param idMetadatos the idMetadatos to set
	 */
	public void setIdMetadatos(int idMetadatos) {
		this.idMetadatos = idMetadatos;
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
	 * @return the metadatosWeb
	 */
	public String getMetadatosWeb() {
		return metadatosWeb;
	}

	/**
	 * @param metadatosWeb the metadatosWeb to set
	 */
	public void setMetadatosWeb(String metadatosWeb) {
		this.metadatosWeb = metadatosWeb;
	}
}
