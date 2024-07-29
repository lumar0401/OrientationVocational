package com.co.orientationVocational.app.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class jwtDto {
	private String token;
    private String bearer = "Bearer";
    private String identificacion;
    private String expirationToken;
    private Collection<? extends GrantedAuthority> authorities;
    
	public jwtDto(String token, String identificacion, String expirationToken, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.identificacion = identificacion;
		this.expirationToken = expirationToken;
		this.authorities = authorities;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the bearer
	 */
	public String getBearer() {
		return bearer;
	}

	/**
	 * @param bearer the bearer to set
	 */
	public void setBearer(String bearer) {
		this.bearer = bearer;
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
	 * @return the expirationToken
	 */
	public String getExpirationToken() {
		return expirationToken;
	}

	/**
	 * @param expirationToken the expirationToken to set
	 */
	public void setExpirationToken(String expirationToken) {
		this.expirationToken = expirationToken;
	}

	/**
	 * @return the authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
