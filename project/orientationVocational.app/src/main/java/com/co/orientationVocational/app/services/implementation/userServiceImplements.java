package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.data.infoTest;
import com.co.orientationVocational.app.services.service.userService;

@Service
public class userServiceImplements implements userService{

	Connection connection;
	
	public userServiceImplements() throws SQLException {
		connection = dataBase.getConnection();
	}
	
	@Override
	public int loginValidation(String identificacion) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		int ingreso = 0;
		
		try {
			sSql.append(" SELECT * ")
			.append(" FROM usuario ")
			.append(" WHERE IDENTIFICACION = ? ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, identificacion.toString()); 
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				if(rSet.getString("identificacion").equals(identificacion)) {
					ingreso = 1;
				}else {
					ingreso = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ingreso;
	}
	
	@Override
	public String ciudadValidation(String identificacion) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		String response = "";
		
		try {
			sSql.append(" SELECT direccion, ciudad, identificacion ")
			.append(" FROM usuario ")
			.append(" WHERE IDENTIFICACION = ? ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, identificacion.toString()); 
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				response = rSet.getString("ciudad");
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public List<String> findByTest(String identificacion, String test){
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		List<String> testUsuarios = new LinkedList<String>();
		
		try {
			if(test.equals("chaside")) {
				sSql.append(" SELECT detalle1, COUNT(detalle1) AS cantidad ")
			    .append(" FROM test ")
			    .append(" WHERE identificacion_usuario = ? ")
			    .append(" AND tipo_test = 'Test Chaside' ")
			    .append(" AND observacion_test like '%El test fue realizado correctamente%' ")
				.append(" GROUP BY detalle1 ")
				.append(" ORDER BY cantidad DESC ")
				.append(" LIMIT 1 ");
				
				pStm = connection.prepareStatement(sSql.toString());
				
				int i = 1;
				
				pStm.setObject(i++, identificacion.toString());
				
				rSet = pStm.executeQuery();
				
				while (rSet.next()) {
					String temp = rSet.getString("detalle1") + "," + rSet.getString("cantidad"); 
					testUsuarios.add(temp);
				}
				
				sSql = new StringBuilder();
				
				sSql.append(" SELECT detalle2, COUNT(detalle2) AS cantidad2 ")
			    .append(" FROM test ")
			    .append(" WHERE identificacion_usuario = ? ")
			    .append(" AND tipo_test = 'Test Chaside' ")
			    .append(" AND observacion_test like '%El test fue realizado correctamente%' ")
				.append(" GROUP BY detalle2 ")
				.append(" ORDER BY cantidad2 DESC ")
				.append(" LIMIT 1 ");
				
				pStm = connection.prepareStatement(sSql.toString());
				
				i = 1;
				
				pStm.setObject(i++, identificacion.toString());
				
				rSet = pStm.executeQuery();
				
				while (rSet.next()) {
					String temp = rSet.getString("detalle2") + "," + rSet.getString("cantidad2"); 
					testUsuarios.add(temp);
				}
			}else if(test.equals("holland")) {
				sSql.append(" SELECT detalle1, COUNT(detalle1) AS cantidad ")
			    .append(" FROM test ")
			    .append(" WHERE identificacion_usuario = ? ")
			    .append(" AND tipo_test = 'Test Holland' ")
			    .append(" AND observacion_test like '%El test fue realizado correctamente%' ")
				.append(" GROUP BY detalle1 ")
				.append(" ORDER BY cantidad DESC ")
				.append(" LIMIT 1 ");
				
				pStm = connection.prepareStatement(sSql.toString());
				
				int i = 1;
				
				pStm.setObject(i++, identificacion.toString());
				
				rSet = pStm.executeQuery();
				
				while (rSet.next()) {
					String temp = rSet.getString("detalle1") + "," + rSet.getString("cantidad"); 
					testUsuarios.add(temp);
				}
				
				sSql = new StringBuilder();
				
				sSql.append(" SELECT detalle2, COUNT(detalle2) AS cantidad2 ")
			    .append(" FROM test ")
			    .append(" WHERE identificacion_usuario = ? ")
			    .append(" AND tipo_test = 'Test Holland' ")
			    .append(" AND observacion_test like '%El test fue realizado correctamente%' ")
				.append(" GROUP BY detalle2 ")
				.append(" ORDER BY cantidad2 DESC ")
				.append(" LIMIT 1 ");
				
				pStm = connection.prepareStatement(sSql.toString());
				
				i = 1;
				
				pStm.setObject(i++, identificacion.toString());
				
				rSet = pStm.executeQuery();
				
				while (rSet.next()) {
					String temp = rSet.getString("detalle2") + "," + rSet.getString("cantidad2"); 
					testUsuarios.add(temp);
				}
				
				sSql = new StringBuilder();
				
				sSql.append(" SELECT detalle3, COUNT(detalle3) AS cantidad3 ")
			    .append(" FROM test ")
			    .append(" WHERE identificacion_usuario = ? ")
			    .append(" AND tipo_test = 'Test Holland' ")
			    .append(" AND observacion_test like '%El test fue realizado correctamente%' ")
				.append(" GROUP BY detalle3 ")
				.append(" ORDER BY cantidad3 DESC ")
				.append(" LIMIT 1 ");
				
				pStm = connection.prepareStatement(sSql.toString());
				
				i = 1;
				
				pStm.setObject(i++, identificacion.toString());
				
				rSet = pStm.executeQuery();
				
				while (rSet.next()) {
					String temp = rSet.getString("detalle3") + "," + rSet.getString("cantidad3"); 
					testUsuarios.add(temp);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return testUsuarios;		
	}
	
	@Override
	public int loginValidationCoordenadas(String identificacion) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		int ingreso = 0;
		
		try {
			sSql.append(" SELECT * ")
			.append(" FROM ubicacion_usuario ")
			.append(" WHERE identificacion_usuario = ? ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, identificacion.toString()); 
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				if(rSet.getString("identificacion").equals(identificacion)) {
					ingreso = 1;
				}else {
					ingreso = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ingreso;
	}
}
