package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.services.models.pregunta;
import com.co.orientationVocational.app.services.service.consultasDataBaseRepository;

@Service
public class consultasDataBase implements consultasDataBaseRepository {
	Connection connection;
	
	public consultasDataBase() throws SQLException {
		connection = dataBase.getConnection();
	}

	@Override
	public boolean cantidadDatos(String tabla) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		String tablaConsulta = tabla;
		boolean response = false;
		
		try {
			sSql.append(" SELECT COUNT(*) FROM ")
			.append(tablaConsulta);
			
			pStm = connection.prepareStatement(sSql.toString());
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				if(rSet.getInt(1) > 0) {
					response = true;
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@Override
	public int insertRegistre(pregunta newsPregunta) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		int rSet = 0;
		
		int ingreso = 0;
		
		try {
			sSql.append(" INSERT INTO pregunta (descripcion_pregunta, opcion1, opcion2, ")
			.append(" opcion3, opcion4, orden_pregunta, respuesta1, respuesta2, tipo_test) ")
			.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, newsPregunta.getDescripcionPregunta().toString());
			pStm.setObject(i++, newsPregunta.getOpcion1().toString());
			pStm.setObject(i++, newsPregunta.getOpcion2().toString());
			pStm.setObject(i++, newsPregunta.getOpcion3().toString());
			pStm.setObject(i++, newsPregunta.getOpcion4().toString());
			pStm.setObject(i++, Integer.valueOf(newsPregunta.getOrdenPregunta()));
			pStm.setObject(i++, newsPregunta.getRespuesta1().toString());
			pStm.setObject(i++, newsPregunta.getRespuesta2().toString());
			pStm.setObject(i++, newsPregunta.getTipoTest().toString());
			
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
	public List<pregunta> ListaPreguntas(String tipoTest){
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		List<pregunta> listaPresuntastest = new LinkedList<pregunta>();
		
		try {
			sSql.append(" SELECT id, descripcion_pregunta, opcion1, opcion2, opcion3, opcion4, orden_pregunta, ")
			.append(" respuesta1, respuesta2, tipo_test ")
			.append(" FROM pregunta ")
			.append(" WHERE TIPO_TEST = ? ")
			.append(" ORDER BY ORDEN_PREGUNTA ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, tipoTest.toString()); 
			
			rSet = pStm.executeQuery();
			
			while(rSet.next()) {
				pregunta preguntaNew = new pregunta();
				
				preguntaNew.setId(Integer.valueOf(rSet.getObject("id").toString()));
				preguntaNew.setDescripcionPregunta(rSet.getObject("descripcion_pregunta").toString());
				preguntaNew.setOpcion1(rSet.getObject("opcion1").toString());
				preguntaNew.setOpcion2(rSet.getObject("opcion2").toString());
				preguntaNew.setOrdenPregunta(Integer.valueOf(rSet.getObject("orden_pregunta").toString()));
				preguntaNew.setTipoTest(rSet.getObject("tipo_test").toString());
				   
				listaPresuntastest.add(preguntaNew);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return listaPresuntastest;
	}
}
