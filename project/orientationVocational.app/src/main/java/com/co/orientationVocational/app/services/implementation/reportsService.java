package com.co.orientationVocational.app.services.implementation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
import com.co.orientationVocational.app.services.models.logUsuario;
import com.co.orientationVocational.app.services.models.pregunta;
import com.co.orientationVocational.app.services.models.universidades;
import com.co.orientationVocational.app.services.service.reportsRepository;

@Service
public class reportsService implements reportsRepository, modelDataExcel{
	Connection connection;
	
	public reportsService() throws SQLException {
		connection = dataBase.getConnection();
	}
	
	@Override
	public ByteArrayInputStream exportAllData(String tipoInforme) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		if(tipoInforme.equalsIgnoreCase("preguntas")) {
			try {
				String[] columns = {"Id", "Descripcion Pregunta", "Tipo Test"};
				
				Workbook workbook = new HSSFWorkbook();
				
				Sheet sheet = workbook.createSheet("Preguntas");
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
							
				List<pregunta> preguntas = this.listaPreguntas();
				
				int initRow = 1;
				
				for (pregunta pregunt : preguntas) {
					row = sheet.createRow(initRow);
					
					row.createCell(0).setCellValue(pregunt.getId());
					row.createCell(1).setCellValue(pregunt.getDescripcionPregunta());
					row.createCell(2).setCellValue(pregunt.getTipoTest());
								
					row.setRowStyle(dataCellStyle);
					
					row.setHeight((short) 0);
					
					initRow++;
				}
				
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				
				workbook.write(stream);
				workbook.close();
			} catch (Exception e) {
				String[] columns = {"Id", "Descripcion Pregunta", "Tipo Test"};
				
				try (Workbook workbook = new HSSFWorkbook()) {
					Sheet sheet = workbook.createSheet("Preguntas");
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
		}else if(tipoInforme.equalsIgnoreCase("universidades")) {
			try {
				String[] columns = {"Nombre Universidad", "Direccion", "Ciudad", "Url Pagina", "Posicion", "Puntuacion"};
				
				Workbook workbook = new HSSFWorkbook();
				
				Sheet sheet = workbook.createSheet("Universidades");
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
							
				List<universidades> listaUniversidades = this.listaUniversidades();
				
				int initRow = 1;
				
				for (universidades university : listaUniversidades) {
					row = sheet.createRow(initRow);
					
					row.createCell(0).setCellValue(university.getNombreUniversidad());
					row.createCell(1).setCellValue(university.getDireccion());
					row.createCell(2).setCellValue(university.getCiudad());
					row.createCell(3).setCellValue(university.getUrlPagina());
					row.createCell(4).setCellValue(university.getPosicion());
					row.createCell(5).setCellValue(university.getPuntuacion());
								
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
				String[] columns = {"Nombre Universidad", "Direccion", "Ciudad", "Url Pagina", "Posicion", "Puntuacion"};
				
				try (Workbook workbook = new HSSFWorkbook()) {
					Sheet sheet = workbook.createSheet("Universidades");
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
		}else if(tipoInforme.equalsIgnoreCase("logs")) {
			try {
				String[] columns = {"Identificacion", "Operacion", "Fecha Registro", "Campos Nuevos", "Campos Viejos", "Registro"};
				
				Workbook workbook = new HSSFWorkbook();
				
				Sheet sheet = workbook.createSheet("Logs");
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
							
				List<logUsuario> listaLogs = this.listaLogsUsuarios();
				
				int initRow = 1;
				
				for (logUsuario log : listaLogs) {
					row = sheet.createRow(initRow);
					
					row.createCell(0).setCellValue(log.getIdentificacion());
					row.createCell(1).setCellValue(log.getOperacion());
					row.createCell(2).setCellValue(log.getFechaRegistro());
					row.createCell(3).setCellValue(log.getCamposNuevos());
					row.createCell(4).setCellValue(log.getCamposViejos());
					row.createCell(5).setCellValue(log.getRegistro());
								
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
				String[] columns = {"Identificacion", "Operacion", "Fecha Registro", "Campos Nuevos", "Campos Viejos", "Registro"};
				
				try (Workbook workbook = new HSSFWorkbook()) {
					Sheet sheet = workbook.createSheet("Logs");
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
		}
		
		return new ByteArrayInputStream(stream.toByteArray());
	}
	
	@Override
	public List<pregunta> listaPreguntas() {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		List<pregunta> lista = new LinkedList<pregunta>();
		
		try {
			sSql.append(" SELECT id, descripcion_pregunta, tipo_test ")
			.append(" FROM pregunta ")
			.append(" ORDER BY tipo_test, orden_pregunta ");
			
			pStm = connection.prepareStatement(sSql.toString());
						
			rSet = pStm.executeQuery();
			
			while(rSet.next()) {
				pregunta preguntasBusqueda = new pregunta();
				
				preguntasBusqueda.setId(rSet.getInt("id"));
				preguntasBusqueda.setDescripcionPregunta(rSet.getString("descripcion_pregunta"));
				preguntasBusqueda.setTipoTest(rSet.getString("tipo_test"));
				
				lista.add(preguntasBusqueda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return lista;
	}

	@Override
	public List<universidades> listaUniversidades() {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		List<universidades> lista = new LinkedList<universidades>();
		
		try {
			sSql.append(" SELECT nombre_universidad, direccion, ciudad, url_pagina, posicion, puntuacion ")
			.append(" FROM universidades ")
			.append(" ORDER BY nombre_universidad ");
			
			pStm = connection.prepareStatement(sSql.toString());
						
			rSet = pStm.executeQuery();
			
			while(rSet.next()) {
				universidades universidadesBusqueda = new universidades();
				
				universidadesBusqueda.setNombreUniversidad(rSet.getString("nombre_universidad"));
				universidadesBusqueda.setCiudad(rSet.getString("ciudad"));
				universidadesBusqueda.setDireccion(rSet.getString("direccion"));
				universidadesBusqueda.setPosicion(rSet.getString("posicion"));
				universidadesBusqueda.setPuntuacion(rSet.getString("puntuacion"));
				universidadesBusqueda.setUrlPagina(rSet.getString("url_pagina"));
				
				lista.add(universidadesBusqueda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return lista;
	}

	@Override
	public List<logUsuario> listaLogsUsuarios() {
		StringBuilder sSql = new StringBuilder();
		PreparedStatement pStm = null;
		ResultSet rSet = null;
		
		List<logUsuario> lista = new LinkedList<logUsuario>();
		
		try {
			sSql.append(" SELECT identificacion, operacion, fecha_registro, campos_nuevos, campos_viejos, registro ")
			.append(" FROM log_usuario ")
			.append(" ORDER BY identificacion ");
			
			pStm = connection.prepareStatement(sSql.toString());
						
			rSet = pStm.executeQuery();
			
			while(rSet.next()) {
				logUsuario logBusqueda = new logUsuario();
				
				logBusqueda.setIdentificacion(rSet.getString("identificacion"));
				logBusqueda.setOperacion(rSet.getString("operacion"));
				logBusqueda.setFechaRegistro(rSet.getString("fecha_registro"));
				logBusqueda.setCamposNuevos(rSet.getString("campos_nuevos"));
				logBusqueda.setCamposViejos(rSet.getString("campos_viejos"));
				logBusqueda.setRegistro(rSet.getString("registro"));
				
				lista.add(logBusqueda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return lista;
	}
}
