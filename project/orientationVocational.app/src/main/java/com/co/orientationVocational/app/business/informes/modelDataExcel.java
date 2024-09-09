package com.co.orientationVocational.app.business.informes;

import java.io.ByteArrayInputStream;

public interface modelDataExcel  {
	ByteArrayInputStream exportAllData(String tipoInforme) throws Exception;	
	ByteArrayInputStream exportAllDataPDF(String identificacion) throws Exception;	
}
