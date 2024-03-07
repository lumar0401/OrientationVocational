package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.data.dataBase;
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
			sSql.append(" SELECT ciudad, identificacion ")
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
}
