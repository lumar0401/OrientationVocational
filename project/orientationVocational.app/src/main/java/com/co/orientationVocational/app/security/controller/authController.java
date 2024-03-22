package com.co.orientationVocational.app.security.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.orientationVocational.app.business.Mensajes;
import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.security.dto.jwtDto;
import com.co.orientationVocational.app.security.dto.loginUsuario;
import com.co.orientationVocational.app.security.dto.nuevoUsuario;
import com.co.orientationVocational.app.security.entity.rol;
import com.co.orientationVocational.app.security.entity.usuario;
import com.co.orientationVocational.app.security.enums.rolNombre;
import com.co.orientationVocational.app.security.jwt.JwtProvider;
import com.co.orientationVocational.app.security.service.rolService;
import com.co.orientationVocational.app.security.service.usuarioService;
import com.co.orientationVocational.app.services.dto.usuarioDto;
import com.co.orientationVocational.app.services.implementation.logUsuarioService;
import com.google.maps.errors.ApiException;

@RestController
@RequestMapping("/auth")
public class authController extends utils {
	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    usuarioService usuarioservice;
    
    @Autowired
    logUsuarioService logUsuario;
    
    @Autowired
    rolService rolservice;
    
    @Autowired
    JwtProvider jwtprovider;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
	public ResponseEntity<List<usuario>> list() {
		List<usuario> list = usuarioservice.List();
		
		return new ResponseEntity(list, HttpStatus.OK);
	}
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody nuevoUsuario nuevoUsuario, BindingResult bindingResult){
        Map<String, Object> usuarioLog = new HashMap<String, Object>();
    	
    	if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensajes("Email Inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioservice.existsByIdentificacion(nuevoUsuario.getIdentificacion()))
            return new ResponseEntity(new Mensajes("Identificacion ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioservice.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensajes("Email ya existe"), HttpStatus.BAD_REQUEST);
        usuario usuarioNew =
                new usuario(nuevoUsuario.getIdentificacion(), nuevoUsuario.getNombres(), nuevoUsuario.getApellidos(),
                        nuevoUsuario.getTelefono(), nuevoUsuario.getDireccion(), nuevoUsuario.getCiudad(),
                        nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()),
                        nuevoUsuario.getGenero());
        Set<rol> roles = new HashSet<>();
        roles.add(rolservice.getByNombre(rolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolservice.getByNombre(rolNombre.ROLE_ADMIN).get());
        usuarioNew.setRoles(roles);
        usuarioservice.save(usuarioNew);
        
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraSQL = fechaHoraActual.format(formatoSQL);
        
        usuarioLog.put("operacion", "insert");
        usuarioLog.put("fechaRegistro", fechaHoraSQL);
        usuarioLog.put("identificacion", usuarioNew.getIdentificacion());
        usuarioLog.put("usuario", usuarioNew.toString());
        
        if(logUsuario.existsByIdentificacion(nuevoUsuario.getIdentificacion())) {
        	logUsuario.insertRegistre(usuarioLog);
        }
        
        return new ResponseEntity(new Mensajes("Usuario guardado"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<jwtDto> login(@Valid @RequestBody loginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensajes("Datos invalidos"), HttpStatus.BAD_REQUEST);
        
        boolean identificacionTemp = usuarioservice.existsByIdentificacion(loginUsuario.getIdentificacion());
        
        if(identificacionTemp == false)
        	return new ResponseEntity(new Mensajes("Identificacion No Encontrada"), HttpStatus.NOT_FOUND);
        
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getIdentificacion(), loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtprovider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            jwtDto jwtDto = new jwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
            return new ResponseEntity(jwtDto, HttpStatus.OK);
        } catch (BadCredentialsException e) {
        	return new ResponseEntity(new Mensajes("Contraseña Incorrecta"), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/obtain/{id}")
    public ResponseEntity<usuario> getByIdentificacion(@PathVariable("id") String identificacion) throws ApiException, InterruptedException, IOException{
    	if(!usuarioservice.existsByIdentificacion(identificacion))
			return new ResponseEntity(new Mensajes("Usuario no existe"), HttpStatus.NOT_FOUND);
    	
    	usuario usuarioEncontrado = usuarioservice.getByIdentificacion(identificacion).get();
    	
    	return new ResponseEntity(usuarioEncontrado, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{identificacion}")
    public ResponseEntity<usuario> delete(@PathVariable("identificacion") String identificacion){
        Map<String, Object> usuarioLog = new HashMap<String, Object>();

    	if(!usuarioservice.existsByIdentificacion(identificacion)) {
    		return new ResponseEntity(new Mensajes("Usuario no Encontrado"), HttpStatus.NOT_FOUND);
    	}
    	
    	usuario usuarioEliminado = usuarioservice.getByIdentificacion(identificacion).get();
    	
    	LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraSQL = fechaHoraActual.format(formatoSQL);
        
        usuarioLog.put("operacion", "delete");
        usuarioLog.put("fechaRegistro", fechaHoraSQL);
        usuarioLog.put("identificacion", usuarioEliminado.getIdentificacion());
        usuarioLog.put("usuario", usuarioEliminado.toString());
        
        logUsuario.insertRegistre(usuarioLog);        
    	
    	usuarioservice.delete(identificacion);
    	
    	return new ResponseEntity(new Mensajes("Usuario Eliminado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<usuario> update(@PathVariable("id") String identificacion, @RequestBody usuarioDto updateUsaurio){
        Map<String, Object> usuarioLog = new HashMap<String, Object>();
        String camposViejos = "";
        String camposNuevos = "";
               
    	if(!usuarioservice.existsByIdentificacion(identificacion)) {
    		return new ResponseEntity(new Mensajes("Usuario no Encontrado"), HttpStatus.NOT_FOUND);
    	}
    	
    	usuario usuarioActualizado = usuarioservice.getOne(identificacion).get();
    	
    	if(!esVacio(updateUsaurio.getApellidos()) && !updateUsaurio.getApellidos().equals(usuarioActualizado.getApellidos())) {
    		camposViejos = "Apellidos: " + camposViejos + usuarioActualizado.getApellidos().toString();
    		usuarioActualizado.setApellidos(updateUsaurio.getApellidos());
    		camposNuevos = "Apellidos: " + camposNuevos + updateUsaurio.getApellidos().toString();
    	}
    	
    	if(!esVacio(updateUsaurio.getNombres()) && !updateUsaurio.getNombres().equals(usuarioActualizado.getNombres())) {
    		camposViejos = camposViejos + " " + "Nombres: " + usuarioActualizado.getNombres().toString();
    		usuarioActualizado.setNombres(updateUsaurio.getNombres());
    		camposNuevos = camposNuevos + " " + "Nombres: " + updateUsaurio.getNombres().toString();
    	}
    	
    	if(!esVacio(updateUsaurio.getCiudad()) && !updateUsaurio.getCiudad().equals(usuarioActualizado.getCiudad())) {
    		camposViejos = camposViejos + " " + "Ciudad: " + usuarioActualizado.getCiudad().toString();
    		usuarioActualizado.setCiudad(updateUsaurio.getCiudad());
    		camposNuevos = camposNuevos + " " + "Ciudad: " + updateUsaurio.getCiudad().toString();
    	}
    	
    	if(!esVacio(updateUsaurio.getDireccion()) && !updateUsaurio.getDireccion().equals(usuarioActualizado.getDireccion())) {
    		camposViejos = camposViejos + " " + "Direccion: " + usuarioActualizado.getDireccion().toString();
    		usuarioActualizado.setDireccion(updateUsaurio.getDireccion());
    		camposNuevos = camposNuevos + " " + "Direccion: " + updateUsaurio.getDireccion().toString();
    	}
    	
    	if(!esVacio(updateUsaurio.getEmail()) && !updateUsaurio.getEmail().equals(usuarioActualizado.getEmail())) {
    		camposViejos = camposViejos + " " + "Email: " + usuarioActualizado.getEmail().toString();
    		usuarioActualizado.setEmail(updateUsaurio.getEmail());
    		camposNuevos = camposNuevos + " " + "Email: " + updateUsaurio.getEmail().toString();
    	}
    	
    	if(!esVacio(updateUsaurio.getTelefono()) && !updateUsaurio.getTelefono().equals(usuarioActualizado.getTelefono())) {
    		camposViejos = camposViejos + " " + "Telefono: " + usuarioActualizado.getTelefono().toString();
    		usuarioActualizado.setTelefono(updateUsaurio.getTelefono());
    		camposNuevos = camposNuevos + " " + "Telefono: " + updateUsaurio.getTelefono().toString();
    	}
    	
    	if(!esVacio(updateUsaurio.getPassword()) && !updateUsaurio.getPassword().equals(usuarioActualizado.getPassword())) {
    		usuario usuarioEncontrado = usuarioservice.getByIdentificacion(identificacion).get();
    		if(passwordEncoder.matches(updateUsaurio.getPassword(), usuarioEncontrado.getPassword())) {
    			camposViejos = camposViejos + " " + "Password: " + usuarioEncontrado.getPassword().toString();
    			String passNew = passwordEncoder.encode(updateUsaurio.getPasswordNew());
    			usuarioActualizado.setPassword(passNew);
    			camposNuevos = camposNuevos + " " + "Password: " + passNew.toString();
    		}else {
    			return new ResponseEntity(new Mensajes("Contraseña incorrecta"), HttpStatus.NOT_FOUND);
    		}
    	}
    	
    	usuarioservice.save(usuarioActualizado);
    	
    	LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraSQL = fechaHoraActual.format(formatoSQL);
        
        usuarioLog.put("operacion", "update");
        usuarioLog.put("fechaRegistro", fechaHoraSQL);
        usuarioLog.put("identificacion", identificacion);
        usuarioLog.put("camposViejos", camposViejos.toString());
        usuarioLog.put("camposNuevos", camposNuevos.toString());
        
        logUsuario.insertRegistre(usuarioLog);        
    	
    	return new ResponseEntity(new Mensajes("Usuario Actualizado"), HttpStatus.OK);
    }
}