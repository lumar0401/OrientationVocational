package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.services.service.logUsuarioRepository;

@Service
public class logUsuarioService implements logUsuarioRepository{
	Connection connection;
	
	public logUsuarioService() throws SQLException {
		connection = dataBase.getConnection();
	}
	
	@Override
	public int insertRegistre(Map<String, Object> newRegistroLog) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		int rSet = 0;
		
		int ingreso = 0;
		
		try {
			if(newRegistroLog.get("operacion").equals("insert") || newRegistroLog.get("operacion").equals("delete")) {
				sSql.append(" INSERT INTO log_usuario (registro, fecha_registro, identificacion, ")
				.append(" operacion) ")
				.append(" VALUES (?,?,?,?) ");
				
				pStm = connection.prepareStatement(sSql.toString());
				
				int i = 1;
				
				pStm.setObject(i++, newRegistroLog.get("usuario").toString());
				pStm.setObject(i++, newRegistroLog.get("fechaRegistro").toString());
				pStm.setObject(i++, newRegistroLog.get("identificacion"));
				pStm.setObject(i++, newRegistroLog.get("operacion"));
				
				rSet = pStm.executeUpdate();
				
				if(rSet != 0) {
					ingreso = 1;
				}
			}else if(newRegistroLog.get("operacion").equals("update")) {
				sSql.append(" INSERT INTO log_usuario (campos_nuevos, campos_viejos, fecha_registro, ")
				.append(" identificacion, operacion) ")
				.append(" VALUES (?,?,?,?,?) ");
				
				pStm = connection.prepareStatement(sSql.toString());
				
				int i = 1;
				
				pStm.setObject(i++, newRegistroLog.get("camposNuevos").toString());
				pStm.setObject(i++, newRegistroLog.get("camposViejos").toString());
				pStm.setObject(i++, newRegistroLog.get("fechaRegistro").toString());
				pStm.setObject(i++, newRegistroLog.get("identificacion"));
				pStm.setObject(i++, newRegistroLog.get("operacion"));
				
				rSet = pStm.executeUpdate();
				
				if(rSet != 0) {
					ingreso = 1;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingreso;
	}

	@Override
	public boolean existsByIdentificacion(String identificacion) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		boolean ingreso = false;
		
		try {
			sSql.append(" SELECT * ")
			.append(" FROM usuario ")
			.append(" WHERE IDENTIFICACION = ? ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, identificacion.toString()); 
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				ingreso = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return ingreso;
	}
}
