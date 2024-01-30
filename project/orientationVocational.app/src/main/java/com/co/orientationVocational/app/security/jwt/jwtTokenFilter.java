package com.co.orientationVocational.app.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.co.orientationVocational.app.security.service.userDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class jwtTokenFilter extends OncePerRequestFilter {
	
	private final static Logger logger = LoggerFactory.getLogger(jwtTokenFilter.class);

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	userDetailsServiceImpl userdetailsImp;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			String token = getToken(request);
			
			if(token != null && jwtProvider.validateToken(token)) {
				String identificacion = jwtProvider.getIdentificacionFromToken(token);
				UserDetails userdetails = userdetailsImp.loadUserByUsername(identificacion);
				UsernamePasswordAuthenticationToken auth = 
						new UsernamePasswordAuthenticationToken(userdetails,null, userdetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			}
		} catch (Exception e) {
			logger.error("fallo el metodo doFilter");
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer"))
			return header.replace("Bearer ", "");
		
		return null;
	}

}
