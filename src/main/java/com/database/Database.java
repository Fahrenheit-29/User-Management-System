package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {


	
	
	public Connection   db() throws SQLException {
		Connection con = null;
		
		try {
			
			
		//	String username="root";
			String username="admin";
			String pass="jerome1234!";
			//String driver="com.mysql.jdbc.Driver";
			String driver="com.mysql.cj.jdbc.Driver";
			//String url="jdbc:mysql://localhost:3306/usermanagement_db";
			String awsURL="jdbc:mysql://dbinstances.cf0plppbi7oq.us-east-1.rds.amazonaws.com:3306/usermanagement_db";
			String awsurl="jdbc:mysql://database-2.cf0plppbi7oq.us-east-1.rds.amazonaws.com:3306/DATABASEAWS";
			Class.forName(driver);
			con=DriverManager.getConnection(awsURL, username, pass);
		
		
			
			
			
		} catch (Exception e) {
			System.out.println("\t  Exception Occur " +e.getMessage());
			e.printStackTrace();
		} 
		
		
			
	
		
	
		return con;
	}
}
