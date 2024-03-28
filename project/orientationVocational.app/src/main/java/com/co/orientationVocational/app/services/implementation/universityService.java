package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.services.service.universityRepository;

@Service
public class universityService implements universityRepository{
	Connection connection;
	
	public universityService() throws SQLException {
		connection = dataBase.getConnection();
	}

	@Override
	public List<String> pageUniversity(Map<String, String> mapDatos) {
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
			
			pStm.setObject(i++, mapDatos.toString()); 
			
			rSet = pStm.executeQuery();
			
			if(rSet.next()) {
				if(rSet.getString("identificacion").equals(mapDatos)) {
					ingreso = 1;
				}else {
					ingreso = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
