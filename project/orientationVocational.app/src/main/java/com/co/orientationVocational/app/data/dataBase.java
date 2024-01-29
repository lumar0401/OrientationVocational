package com.co.orientationVocational.app.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataBase {
	private static String userBaseData = "root";
	private static String passwordBaseData = "root";
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if(connection != null) {
			return connection;
		}else {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url= "jdbc:mysql://localhost:3306/orientationVocational?useSSL=false";
			String user = userBaseData;
			String passwordd = passwordBaseData;
			
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url,user,passwordd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}

}
