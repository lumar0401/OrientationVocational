package com.co.orientationVocational.app.services.implementation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.co.orientationVocational.app.business.informes.modelDataExcel;
import com.co.orientationVocational.app.data.dataBase;
import com.co.orientationVocational.app.security.entity.usuario;
import com.co.orientationVocational.app.services.service.logUsuarioRepository;

@Service
public class logUsuarioService implements logUsuarioRepository, modelDataExcel{
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

	@Override
	public ByteArrayInputStream exportAllData(String tipoInforme) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		try {
			String[] columns = {"Identificacion", "Nombres", "Apellidos", "Direccion", "Telefono", "Ciudad", "Email"};
			
			Workbook workbook = new HSSFWorkbook();
			
			Sheet sheet = workbook.createSheet("Usuarios");
			Row row = sheet.createRow(0);
			
			CellStyle headerCellStyle = workbook.createCellStyle();
			
			//Background
		    headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		    headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		    
		    //Borders
		    headerCellStyle.setBorderBottom(BorderStyle.THIN);
		    headerCellStyle.setBorderTop(BorderStyle.THIN);
		    headerCellStyle.setBorderLeft(BorderStyle.THIN);
		    headerCellStyle.setBorderRight(BorderStyle.THIN);
		    
		    //Fonts
		    Font headerFont = workbook.createFont();
		    
		    headerFont.setColor(IndexedColors.BLACK.getIndex());
		    headerFont.setFontName("Calibri");
		    headerFont.setBold(true);
		    
		    headerCellStyle.setFont(headerFont);
		    
			for (int i = 0; i < columns.length; i++) {
				Cell cell = row.createCell(i);
				
			    row.setHeight((short) 0);
			    
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
				cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
				cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);			
			}
			
			CellStyle dataCellStyle = workbook.createCellStyle();
						
			List<usuario> usuarios = this.listaUsuarios();
			
			int initRow = 1;
			
			for (usuario usuario : usuarios) {
				row = sheet.createRow(initRow);
				
				row.createCell(0).setCellValue(usuario.getIdentificacion());
				row.createCell(1).setCellValue(usuario.getNombres());
				row.createCell(2).setCellValue(usuario.getApellidos());
				row.createCell(3).setCellValue(usuario.getDireccion());
				row.createCell(4).setCellValue(usuario.getTelefono());
				row.createCell(5).setCellValue(usuario.getCiudad());
				row.createCell(6).setCellValue(usuario.getEmail());
							
				row.setRowStyle(dataCellStyle);
				
				row.setHeight((short) 0);
				
				initRow++;
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			
			workbook.write(stream);
			workbook.close();
		} catch (Exception e) {
			String[] columns = {"Identificacion", "Nombres", "Apellidos", "Direccion", "Telefono", "Ciudad", "Email"};
			
			try (Workbook workbook = new HSSFWorkbook()) {
				Sheet sheet = workbook.createSheet("Usuarios");
				Row row = sheet.createRow(0);
				
				CellStyle headerCellStyle = workbook.createCellStyle();
				
				//Background
				headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				//Borders
				headerCellStyle.setBorderBottom(BorderStyle.THIN);
				headerCellStyle.setBorderTop(BorderStyle.THIN);
				headerCellStyle.setBorderLeft(BorderStyle.THIN);
				headerCellStyle.setBorderRight(BorderStyle.THIN);
				
				//Fonts
				Font headerFont = workbook.createFont();
				
				headerFont.setColor(IndexedColors.BLACK.getIndex());
				headerFont.setFontName("Calibri");
				headerFont.setBold(true);
				
				headerCellStyle.setFont(headerFont);
				
				for (int i = 0; i < columns.length; i++) {
					Cell cell = row.createCell(i);
					
				    row.setHeight((short) 0);
				    
					cell.setCellValue(columns[i]);
					cell.setCellStyle(headerCellStyle);
					cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
					cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);			
				}
			}
		}
		
		return new ByteArrayInputStream(stream.toByteArray());
	}

	@Override
	public List<usuario> listaUsuarios() {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		List<usuario> lista = new LinkedList<usuario>();
		
		try {
			sSql.append(" SELECT identificacion, nombres, apellidos, direccion, telefono, ciudad, email ")
			.append(" FROM usuario ")
			.append(" ORDER BY identificacion ");
			
			pStm = connection.prepareStatement(sSql.toString());
						
			rSet = pStm.executeQuery();
			
			while(rSet.next()) {
				usuario usuarioBusqueda = new usuario();
				
				usuarioBusqueda.setIdentificacion(rSet.getString("identificacion"));
				usuarioBusqueda.setNombres(rSet.getString("nombres"));
				usuarioBusqueda.setApellidos(rSet.getString("apellidos"));
				usuarioBusqueda.setDireccion(rSet.getString("direccion"));
				usuarioBusqueda.setTelefono(rSet.getString("telefono"));
				usuarioBusqueda.setCiudad(rSet.getString("ciudad"));
				usuarioBusqueda.setEmail(rSet.getString("email"));
				
				lista.add(usuarioBusqueda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return lista;
	}
}
