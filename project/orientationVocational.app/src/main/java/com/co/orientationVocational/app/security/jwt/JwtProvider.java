package com.co.orientationVocational.app.security.jwt;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.co.orientationVocational.app.security.entity.usuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication authentication) {
		usuarioPrincipal usuarioprincipal = (usuarioPrincipal) authentication.getPrincipal();
		
		return Jwts.builder().setSubject(usuarioprincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public String getIdentificacionFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Token con estructura incorrecta");
		} catch (UnsupportedJwtException e) {
			logger.error("Token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("Token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("Token vacio");
		} catch (SignatureException e) {
			logger.error("fallo en la firma");
		}
		
		return false;
	}
	
	public Date getExpirationDateToken(String token) {
	    return Jwts.parser()
	            .setSigningKey(secret)
	            .parseClaimsJws(token)
	            .getBody()
	            .getExpiration();
	}

	public String getExpirationDateTokenISO(String token) {
		Date expirationDate = getExpirationDateToken(token);
    	ZonedDateTime expirationZonedDateTime = expirationDate.toInstant().atZone(ZoneId.of("America/Bogota"));
    	return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(expirationZonedDateTime);
    }
}
