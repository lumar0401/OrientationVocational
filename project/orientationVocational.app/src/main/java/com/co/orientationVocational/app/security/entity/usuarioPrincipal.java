package com.co.orientationVocational.app.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class usuarioPrincipal implements UserDetails {
	private String identificacion;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String direccion;
	private String ciudad;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public usuarioPrincipal() {}
	
	public usuarioPrincipal(String identificacion, String nombres, String apellidos, String telefono,
			String direccion, String ciudad, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.identificacion = identificacion;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static usuarioPrincipal build(usuario usuario) {
		List<GrantedAuthority> autorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
				.getDescripcion().name())).collect(Collectors.toList());
		
		return new usuarioPrincipal(usuario.getIdentificacion(), usuario.getNombres(), usuario.getApellidos(), usuario.getTelefono(), usuario.getDireccion(), usuario.getCiudad(), usuario.getEmail(), usuario.getPassword(), autorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return identificacion;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
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
}
