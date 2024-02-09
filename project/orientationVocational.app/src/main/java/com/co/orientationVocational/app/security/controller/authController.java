package com.co.orientationVocational.app.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.security.dto.jwtDto;
import com.co.orientationVocational.app.security.dto.loginUsuario;
import com.co.orientationVocational.app.security.dto.nuevoUsuario;
import com.co.orientationVocational.app.security.entity.rol;
import com.co.orientationVocational.app.security.entity.usuario;
import com.co.orientationVocational.app.security.enums.rolNombre;
import com.co.orientationVocational.app.security.jwt.JwtProvider;
import com.co.orientationVocational.app.security.service.rolService;
import com.co.orientationVocational.app.security.service.usuarioService;

import com.co.orientationVocational.app.business.Mensajes;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class authController {
	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    usuarioService usuarioservice;
    
    @Autowired
    rolService rolservice;
    
    @Autowired
    JwtProvider jwtprovider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody nuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensajes("Email Inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioservice.existsByIdentificacion(nuevoUsuario.getIdentificacion()))
            return new ResponseEntity(new Mensajes("Identificacion ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioservice.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensajes("Email ya existe"), HttpStatus.BAD_REQUEST);
        usuario usuarioNew =
                new usuario(nuevoUsuario.getIdentificacion(), nuevoUsuario.getNombres(), nuevoUsuario.getApellidos(),
                        nuevoUsuario.getTelefono(), nuevoUsuario.getDireccion(), nuevoUsuario.getCiudad(),
                        nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<rol> roles = new HashSet<>();
        roles.add(rolservice.getByNombre(rolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolservice.getByNombre(rolNombre.ROLE_ADMIN).get());
        usuarioNew.setRoles(roles);
        usuarioservice.save(usuarioNew);
        return new ResponseEntity(new Mensajes("Usuario guardado"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<jwtDto> login(@Valid @RequestBody loginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensajes("Datos invalidos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getIdentificacion(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtprovider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        jwtDto jwtDto = new jwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
    
    @PostMapping("/obtain/{id}")
    public ResponseEntity<usuario> getByIdentificacion(@PathVariable("id") String identificacion){
    	if(!usuarioservice.existsByIdentificacion(identificacion))
			return new ResponseEntity(new Mensajes("Usuario no existe"), HttpStatus.NOT_FOUND);
    	
    	usuario usuarioEncontrado = usuarioservice.getByIdentificacion(identificacion).get();
    	
    	return new ResponseEntity(usuarioEncontrado, HttpStatus.OK);
    }
}
