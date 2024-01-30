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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.security.dto.jwtDto;
import com.co.orientationVocational.app.security.dto.loginUsuario;
import com.co.orientationVocational.app.security.jwt.JwtProvider;
import com.co.orientationVocational.app.security.service.rolService;
import com.co.orientationVocational.app.security.service.usuarioService;

import com.co.orientationVocational.app.business.Mensajes;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
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
}
