package com.co.orientationVocational.app.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.co.orientationVocational.app.security.entity.rol;
import com.co.orientationVocational.app.security.enums.rolNombre;

import com.co.orientationVocational.app.security.service.rolService;

@Component
public class createRoles implements CommandLineRunner {
	@Autowired
    rolService rolservice;

    @Override
    public void run(String... args) throws Exception {
        rol rolAdmin = new rol(rolNombre.ROLE_ADMIN);
        rol rolUser = new rol(rolNombre.ROLE_USER);
        
        //Solo se registran la primera vez, mantener comentarios ah no ser de que se elimine la tabla o la base de datos
        //rolservice.save(rolAdmin);
        //rolservice.save(rolUser);
    }
}
