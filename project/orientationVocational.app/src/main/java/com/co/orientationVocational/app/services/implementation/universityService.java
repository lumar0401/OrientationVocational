package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.business.utils;
import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.data.modelUniversityPage;
import com.co.orientationVocational.app.data.responseCardUniversity;
import com.co.orientationVocational.app.services.service.universityRepository;

@Service
public class universityService extends utils implements universityRepository {
	Connection connection;
	
	public universityService() throws SQLException {
		connection = dataBase.getConnection();
	}

	@Override
	public modelUniversityPage pageUniversity(String datos, String intereses, String test) {
		modelUniversityPage result = new modelUniversityPage();
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		String format = "";
		
		String[] datosTemp = datos.split(" ");
		
		String[] interesestemp = (!esVacio(intereses)) ? intereses.split(",") : new String[0];
		
		
		try {
			sSql.append(" SELECT id_universidad, nombre_universidad, url_pagina, direccion, requisitos, puntuacion, posicion, fecha_registro, programa ")
			.append(" FROM universidades ")
			.append(" WHERE nombre_universidad like ? ");
		
			for (int j = 1; j < datosTemp.length; j++) {
				sSql.append(" AND nombre_universidad like ? ");
			}
			
			if(intereses.length() > 1) {
				String columnName = (test.toString().toLowerCase().equals("chaside")) ? "caracteristicas_chaside" : "caracteristicas_holland";
				
				sSql.append(" AND (");
				
				for (int j = 0; j < interesestemp.length; j++) {
					if(j == 0) {
						sSql.append(columnName + " = '" + interesestemp[j] + "'");
					}else {
						sSql.append(" OR " + columnName + " = '" + interesestemp[j] + "' ");
					}
					
				}
				
				sSql.append(")");
			}
												
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			format = "%" + datosTemp[0].toString() + "%";
			
			pStm.setObject(i++, format.toString());
		
			for (int j = 1; j < datosTemp.length; j++) {
				format = "%" + datosTemp[j].toString() + "%";
				pStm.setObject(i++, format.toString());
			}
						
			rSet = pStm.executeQuery();
			
			while(rSet.next()) {
				result.setId(rSet.getString("id_universidad").toString());
				result.setPaginaUrl(rSet.getString("url_pagina").toString());
				result.setDirecciones(rSet.getString("direccion").toString());
				result.setRequisitos(rSet.getString("requisitos").toString());
				result.setPuntuacion(rSet.getString("puntuacion").toString());
				result.setPosicion(rSet.getString("posicion").toString());
				result.setFechaRegistro(rSet.getString("fecha_registro").toString());
				result.setPrograma(rSet.getString("programa").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String obtainMetadatos(String idUniversidad) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		String pDatos = "";
		
		try {
			sSql.append(" SELECT metadatos_web ")
			.append(" FROM metadatos_html ")
			.append(" WHERE id_universidad = ? ");		
												
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
						
			pStm.setObject(i++, idUniversidad.toString());
						
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				pDatos = rSet.getString("metadatos_web").toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pDatos;
	}

	@Override
	public LinkedList<responseCardUniversity> informacionUniversidad(String idUniversidad, String programa) {
		LinkedList<responseCardUniversity> university = new LinkedList<responseCardUniversity>();
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		try {
			sSql.append(" SELECT titulo, semestres, valor_semestre, modalidad, director_programa ")
			.append(" FROM informacion_universidades ")
			.append(" WHERE id_universidad = ? ")
			.append(" AND programa = ? ");
												
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
						
			pStm.setObject(i++, idUniversidad.toString());
			pStm.setObject(i++, programa.toString());
						
			rSet = pStm.executeQuery();
			
			while(rSet.next()) {
				responseCardUniversity universidadNew = new responseCardUniversity();
				
				universidadNew.setTitulo(rSet.getString("titulo").toString());
				universidadNew.setSemestres(rSet.getString("semestres").toString());
				universidadNew.setValorSemestre(rSet.getString("valor_semestre").toString());
				universidadNew.setModalidad(rSet.getString("modalidad").toString());
				universidadNew.setDirectorPrograma(rSet.getString("director_programa").toString());
				
				university.add(universidadNew);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return university;
	}
}