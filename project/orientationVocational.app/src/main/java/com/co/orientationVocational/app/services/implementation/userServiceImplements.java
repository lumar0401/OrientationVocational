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
	public int loginValidation(String identificacion, String password) {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		int ingreso = 0;
		
		try {
			sSql.append(" SELECT * ")
			.append(" FROM usuario ")
			.append(" WHERE IDENTIFICACIONUSUARIO = ? ");
			
			pStm = connection.prepareStatement(sSql.toString());
			
			int i = 1;
			
			pStm.setObject(i++, identificacion.toString()); 
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				if(rSet.getString("identificacionUsuario").equals(identificacion) && rSet.getString("contrasena").equals(password)) {
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
