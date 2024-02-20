package com.co.orientationVocational.app.services.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class usuarioDto {
	
    @NotBlank
	private String telefono;
	 
	@NotBlank
	@Email
	private String email;
	 
	@NotBlank 
	private String nombres; 
	
	@NotBlank
	private String apellidos;
	
	@NotBlank
	private String direccion; 
	
	@NotBlank
	private String ciudad;

	@NotBlank
	private String genero;
	
	public usuarioDto() {}

	public usuarioDto(@NotBlank String telefono, @NotBlank @Email String email, @NotBlank String nombres, 
			@NotBlank String apellidos, @NotBlank String direccion, @NotBlank String ciudad, @NotBlank String genero) {
		this.telefono = telefono;
		this.email = email;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.genero = genero;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

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
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}
}
