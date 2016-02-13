package com.peligro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	static Connection connection;
	public static Connection getconnection(){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","users","users");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		System.out.println(getconnection());
	}
	
}

