package com.co.orientationVocational.app.business.files;

import java.awt.Color;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.orientationVocational.app.business.test.testChaside;
import com.co.orientationVocational.app.data.infoTest;
import com.co.orientationVocational.app.services.implementation.userServiceImplements;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class resultUserPdf {
	private final static Logger logger = LoggerFactory.getLogger(testChaside.class);
	
	public void pintarBordesPDF(Document documento, PdfWriter writer) {
		PdfContentByte canvas = writer.getDirectContent();
        canvas.setLineWidth(1); // Ancho del borde
        canvas.setColorStroke(new BaseColor(0, 0, 0)); // Color negro
        canvas.rectangle(50, 50, documento.getPageSize().getWidth() - 100, documento.getPageSize().getHeight() - 100);
        canvas.stroke();
	}
	
	public void crearDiagramaCircular(Document documento, PdfWriter writer, String identificacion) throws IOException, DocumentException, SQLException {
		userServiceImplements userServices = new userServiceImplements();
		
		Paragraph title = new Paragraph("Documento Analisis Usuario " + identificacion);
	    title.setAlignment(Element.ALIGN_CENTER);
	    Font font = new Font(FontFamily.HELVETICA, 72, Font.BOLDITALIC, new BaseColor(0, 0, 255));

	    title.setFont(font);
	    documento.add(title);
		
	    List<String> testHolland = userServices.findByTest(identificacion, "holland");
	    List<String> testChaside = userServices.findByTest(identificacion, "chaside");
		
	    Image chartPdfImage1 = null;
	    Image chartPdfImage2 = null;
	    
	    if(!testHolland.isEmpty()) {
	    	List<infoTest> response = organizarRespuesta(testHolland, "holland"); 
	    	 chartPdfImage1 = grafico(response, documento, "Holland");	    
	    }
	    
	    if(!testChaside.isEmpty()) {
	    	List<infoTest> response = organizarRespuesta(testChaside, "chaside"); 
	    	chartPdfImage2 = grafico(response, documento, "Chaside");
	    	//documento.add(chartPdfImage);
	    }
	    
        PdfPTable table = new PdfPTable(2);
        
        table.setWidthPercentage(100); 
        
        table.addCell(chartPdfImage1);
        table.addCell(chartPdfImage2);
        
        if(chartPdfImage1.isContent() || chartPdfImage2.isContent()) {
        	documento.add(table);
        }
        
	}
	
	public Image grafico(List<infoTest> datosGrafico, Document documento, String test) throws IOException, BadElementException {
		
		try {
			DefaultPieDataset dataset = new DefaultPieDataset();
			
			for (infoTest infoTest : datosGrafico) {
				dataset.setValue(infoTest.getName(), (int) infoTest.getValue());
			}
			
			JFreeChart chart = ChartFactory.createPieChart("Test " + test, dataset, true, true, false);
	        
			PiePlot plot = (PiePlot) chart.getPlot();
	        
	        plot.setLabelGenerator(null);
	        
	        BufferedImage chartImage = chart.createBufferedImage(250, 250);
	        ByteArrayOutputStream chartStream = new ByteArrayOutputStream();
	        ImageIO.write(chartImage, "png", chartStream);
	        Image chartPdfImage = Image.getInstance(chartStream.toByteArray());
	        
	        chart.setBackgroundPaint(new Color(0, 0, 0, 0));
	        
	        chartPdfImage.scaleToFit(documento.getPageSize().getWidth() - 
	        		documento.leftMargin() - 
	        		documento.rightMargin(), 
	                documento.getPageSize().getHeight() - 
	                documento.topMargin() - 
	                documento.bottomMargin());
			
	        return chartPdfImage;  
		} catch (Exception e) {
			logger.error("Error generando graficos circulares");
		}
		
		return null;      
	}
	
	private List<infoTest> organizarRespuesta(List<String> resulTest, String test){
    	List<infoTest> response = new LinkedList<infoTest>();
    	    	
    	for (String string : resulTest) {
			String[] temp = string.split(",");
			
			infoTest info  = new infoTest();
			
			String[] aux = temp[0].split("-");
			
			info.setValue(Double.valueOf(temp[1]));
			
			if(test.equalsIgnoreCase("chaside")) {
				info.setName(equivalencia(aux[0],"Chaside"));
			}else {
				info.setName(aux[0]);
			}
			
			response.add(info);
		}
    	
    	return response;
    }
    
    private String equivalencia(String valor,String test) {
    	if(test.contentEquals("Chaside")) {
    		switch (valor) {
			case "C":
				return "Area Administrativa";
			case "H":
				return "Area Humanidades y Ciencias Sociales y Jurídicas";
			case "A":
				return "Area Artística";
			case "S":
				return "Area de Ciencias de la Salud";
			case "I":
				return "Area Enseñanzas Técnicas";
			case "D":
				return "Area de Defensa y Seguridad";
			case "E":
				return "Area de Ciencias Experimentales";
			default:
				return "";
			}
    	}
    	
    	return "";
    }
}


