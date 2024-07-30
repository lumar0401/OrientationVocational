package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.data.icfesUsuario;
import com.co.orientationVocational.app.services.dto.icfesDto;
import com.co.orientationVocational.app.services.service.usuarioIcfesRepository;

@Service
public class usuarioIcfesService implements usuarioIcfesRepository {
	Connection connection;
	
	public usuarioIcfesService() throws SQLException {
		connection = dataBase.getConnection();
	}
	
	@Override
	public int insertRegistre(icfesUsuario newUsuarioIcfes) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		int rSet = 0;
		
		int ingreso = 0;
		
		try {
			sSql.append(" INSERT INTO usuario_icfes (registro_icfes, apellidos_nombres, ciencias_decil, ")
			.append(" ciencias_prueba, ciencias_puntaje, competencias_decil, competencias_prueba, competencias_puntaje, ")
			.append(" fecha_examen, identificacion_usuario, ingles_decil, ingles_nivel, ingles_prueba, ingles_puntaje, ")
			.append(" lectura_decil, lectura_prueba, lectura_puntaje, matematicas_decil, matematicas_prueba, ")
			.append(" matematicas_puntaje, puntaje_global, razonamiento_decil, razonamiento_prueba, razonamiento_puntaje, ")
			.append(" sociales_decil, sociales_prueba, sociales_puntaje) ")
			.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, newUsuarioIcfes.getRegistroExamen().toString());
			pStm.setObject(i++, newUsuarioIcfes.getNombreEstudiante().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(3).getDecil().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(3).getPrueba().toString());
			pStm.setObject(i++, Integer.valueOf(newUsuarioIcfes.getListaMaterias().get(3).getPuntaje()));
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(6).getDecil().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(6).getPrueba().toString());
			pStm.setObject(i++, Integer.valueOf(newUsuarioIcfes.getListaMaterias().get(6).getPuntaje()));
			pStm.setObject(i++, newUsuarioIcfes.getFechaExamen().toString());
			pStm.setObject(i++, newUsuarioIcfes.getIdentificacionUsuario().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(4).getDecil().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(4).getNivel().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(4).getPrueba().toString());
			pStm.setObject(i++, Integer.valueOf(newUsuarioIcfes.getListaMaterias().get(4).getPuntaje()));
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(0).getDecil().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(0).getPrueba().toString());
			pStm.setObject(i++, Integer.valueOf(newUsuarioIcfes.getListaMaterias().get(0).getPuntaje()));
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(1).getDecil().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(1).getPrueba().toString());
			pStm.setObject(i++, Integer.valueOf(newUsuarioIcfes.getListaMaterias().get(1).getPuntaje()));
			pStm.setObject(i++, Integer.valueOf(newUsuarioIcfes.getPuntajeGlobal()));
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(5).getDecil().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(5).getPrueba().toString());
			pStm.setObject(i++, Integer.valueOf(newUsuarioIcfes.getListaMaterias().get(5).getPuntaje()));
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(2).getDecil().toString());
			pStm.setObject(i++, newUsuarioIcfes.getListaMaterias().get(2).getPrueba().toString());
			pStm.setObject(i++, Integer.valueOf(newUsuarioIcfes.getListaMaterias().get(2).getPuntaje()));
			
			rSet = pStm.executeUpdate();
			
			if(rSet != 0) {
				ingreso = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingreso;
	}

	@Override
	public boolean RegistroIcfes(String registro) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		boolean ingreso = false;
		
		try {
			sSql.append(" SELECT * ")
			.append(" FROM usuario_icfes ")
			.append(" WHERE Registro_icfes = ? ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, registro.toString()); 
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				ingreso = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return ingreso;
	}	
	
	@Override
	public List<icfesDto> icfesxUsuario(String identificacion) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		List<icfesDto> response = new LinkedList<icfesDto>();
		
		try {
			sSql.append(" SELECT * ")
			.append(" FROM usuario_icfes ")
			.append(" WHERE identificacion_usuario = ? ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, identificacion.toString()); 
			
			rSet = pStm.executeQuery();
			
			while(rSet.next()) {
				icfesDto icfes = new icfesDto();
				
				icfes.setIdentificacion(rSet.getString("identificacion_usuario").toString());
				icfes.setRegistro(rSet.getString("registro_icfes").toString());
				icfes.setCiencias_naturales(rSet.getString("ciencias_puntaje").toString());
				icfes.setCompetencias(rSet.getString("competencias_puntaje").toString());
				icfes.setIngles(rSet.getString("ingles_puntaje").toString());
				icfes.setLectura_critica(rSet.getString("lectura_puntaje").toString());
				icfes.setMatematicas(rSet.getString("matematicas_puntaje").toString());
				icfes.setRazonamiento(rSet.getString("razonamiento_puntaje").toString());
				icfes.setSociales(rSet.getString("sociales_puntaje").toString());
				
				response.add(icfes);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return response;
	}	
}
