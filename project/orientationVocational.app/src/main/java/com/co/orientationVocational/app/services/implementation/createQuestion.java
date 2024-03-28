package com.co.orientationVocational.app.services.implementation;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.co.orientationVocational.app.services.models.pregunta;

@Component
public class createQuestion implements CommandLineRunner {
	private final static Logger logger = LoggerFactory.getLogger(createQuestion.class);
	
	@Autowired
	private consultasDataBase preguntaS;
	
	@Override
	public void run(String... args) throws Exception {
		String testChaside = "QuestionTestChaside.txt";
		File rutaArchivo = new File(testChaside);

        String rutaAbsoluta = rutaArchivo.getAbsolutePath();
        
        boolean archivo = existFile(rutaAbsoluta);
        
        boolean cantidad = preguntaS.cantidadDatos("pregunta");
        
        if(archivo == true && cantidad == false) {
        	obtenerInfoArchivo(rutaAbsoluta.toString());
        }
        
        String testHolland = "QuestionTestHolland.txt";
		File rutaArchivoHolland = new File(testHolland);

        String rutaAbsolutaHolland = rutaArchivoHolland.getAbsolutePath();
        
        boolean archivoHolland = existFile(rutaAbsolutaHolland);
        
        boolean cantidadHolland = preguntaS.cantidadDatosHolland("pregunta");
        
        if(archivoHolland == true && cantidadHolland == false) {
        	obtenerInfoArchivoHolland(rutaAbsolutaHolland.toString());
        }
	}
	
	private boolean existFile(String rutarelativa) {
		File archivoPDF = new File(rutarelativa);

		if (archivoPDF.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	private void obtenerInfoArchivo(String ruta) throws Exception {
		try {		
			pregunta[] question = new pregunta[98];
			List<pregunta> listaPreguntasTestChaside = new LinkedList<pregunta>();

			try {
	            File archivo = new File(ruta);
	            Scanner scanner = new Scanner(archivo);
	            
	            int contador = 1;
                int temp = 0;
	            
	            while (scanner.hasNextLine()) {
	                String linea = scanner.nextLine();
	                
	                question[temp] = new pregunta();
	                
	                question[temp].setDescripcionPregunta(linea);
	                question[temp].setOpcion1("Si");
	                question[temp].setOpcion2("No");
	                question[temp].setOpcion3(" ");
	                question[temp].setOpcion4(" ");
	                question[temp].setRespuesta1(" ");
	                question[temp].setRespuesta2(" ");
	                question[temp].setTipoTest("Chaside");
	                question[temp].setOrdenPregunta(contador);
	                
	                listaPreguntasTestChaside.add(question[temp]);
	                
	                contador++;
	                temp++;
	            }
	            
	            for (pregunta pregunta : listaPreguntasTestChaside) {
	            	preguntaS.insertRegistre(pregunta);
				}
	            
	            scanner.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		} catch (Exception e) {
			logger.error("Error al descifrar archivo TestChaside.txt");		
		}
	}
	
	private void obtenerInfoArchivoHolland(String ruta) throws Exception {
		try {		
			pregunta[] question = new pregunta[54];
			List<pregunta> listaPreguntasTestHolland = new LinkedList<pregunta>();

			try {
	            File archivo = new File(ruta);
	            Scanner scanner = new Scanner(archivo);
	            
	            int contador = 1;
                int temp = 0;
	            
	            while (scanner.hasNextLine()) {
	                String linea = scanner.nextLine();
	                
	                question[temp] = new pregunta();
	                
	                question[temp].setDescripcionPregunta(linea);
	                question[temp].setOpcion1("Si");
	                question[temp].setOpcion2("No");
	                question[temp].setOpcion3(" ");
	                question[temp].setOpcion4(" ");
	                question[temp].setRespuesta1(" ");
	                question[temp].setRespuesta2(" ");
	                question[temp].setTipoTest("Holland");
	                question[temp].setOrdenPregunta(contador);
	                
	                listaPreguntasTestHolland.add(question[temp]);
	                
	                contador++;
	                temp++;
	            }
	            
	            for (pregunta pregunta : listaPreguntasTestHolland) {
	            	preguntaS.insertRegistre(pregunta);
				}
	            
	            scanner.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		} catch (Exception e) {
			logger.error("Error al descifrar archivo TestChaside.txt");		
		}
	}
}