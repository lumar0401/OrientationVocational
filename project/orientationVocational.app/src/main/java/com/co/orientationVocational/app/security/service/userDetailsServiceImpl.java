package com.co.orientationVocational.app.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.security.entity.usuario;
import com.co.orientationVocational.app.security.entity.usuarioPrincipal;

@Service
public class userDetailsServiceImpl implements UserDetailsService {

	@Autowired
	usuarioService usuarioservice;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		usuario usuarioNew = usuarioservice.getByIdentificacion(username).get();
		return usuarioPrincipal.build(usuarioNew);
	}

}
