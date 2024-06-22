package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.data.testModelResponse;
import com.co.orientationVocational.app.services.service.testRepository;

@Service
public class testService implements testRepository{
	
	Connection connection;
	
	public testService() throws SQLException {
		connection = dataBase.getConnection();
	}

	@Override
	public int inserRegistre(Map<String, Object> mapaDatos) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		int rSet = 0;
		
		int ingreso = 0;
		
		try {
			sSql.append(" INSERT INTO test (identificacion_usuario, resultado_test, tipo_test, ")
			.append(" fecha_test, observacion_test, detalle1, detalle2, detalle3, respuestas) ")
			.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, mapaDatos.get("stIdentificacionTest"));
			pStm.setObject(i++, mapaDatos.get("stResultado"));
			pStm.setObject(i++, mapaDatos.get("stTipoTest"));
			pStm.setObject(i++, mapaDatos.get("stFechaTest"));
			pStm.setObject(i++, mapaDatos.get("stObservacionTest"));
			pStm.setObject(i++, mapaDatos.get("stDetalle1"));
			pStm.setObject(i++, mapaDatos.get("stDetalle2"));
			pStm.setObject(i++, mapaDatos.get("stDetalle3"));
			pStm.setObject(i++, mapaDatos.get("respuestas"));
			
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
	public LinkedList<testModelResponse> consultarUltimoRegistro(String fecha) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet;
		
		LinkedList<testModelResponse> resultadoTest = new LinkedList<testModelResponse>();
		testModelResponse modelBackUp = new testModelResponse();
				
		try {
			sSql.append(" SELECT resultado_test, identificacion_usuario FROM test ")
			    .append(" WHERE fecha_test = (select max(?) from test) ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, fecha.toString());
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				modelBackUp.setAreaInteres(rSet.getString("resultado_test").toString());
			}
			
			resultadoTest.add(modelBackUp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultadoTest;
	}

	@Override
	public LinkedList<String> testPreviosUsuario(String identificacion) {
		LinkedList<String> historicoTest = new LinkedList<String>();		
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet;
		
		try {
			sSql.append(" SELECT respuestas FROM test ")
		    .append(" WHERE identificacion_usuario = ? ")
		    .append(" ORDER BY fecha_test DESC ")
		    .append(" LIMIT 5 ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, identificacion.toString());
			
			rSet = pStm.executeQuery();
			
			while (rSet.next()) {
				historicoTest.add(rSet.getString("respuestas"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return historicoTest;
	}
}
