package com.co.orientationVocational.app.services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.data.dataBase;
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
			.append(" fecha_test, observacion_test, detalle1, detalle2, detalle3) ")
			.append(" VALUES (?,?,?,?,?,?,?,?) ");
			
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
			
			rSet = pStm.executeUpdate();
			
			if(rSet != 0) {
				ingreso = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ingreso;
	}
}
