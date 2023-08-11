package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao_Search {

	public ResultSet search(String name) {
		
		ResultSet rs=null;
	Connection con=null;
	PreparedStatement ps=null;
		try {
			con=new Database().db();
			String query="SELECT *, concat_ws(' ',First_name,Last_Name) AS FullName FROM userinfo u LEFT JOIN account acc ON u.User_ID=acc.account_id WHERE u.First_name LIKE '%"+name+"%'  AND NOT role='Admin'";
			ps=con.prepareStatement(query);
			
			 rs=ps.executeQuery();
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " Problem in Search Dao");
			e.printStackTrace();
		}
		return rs;
		
	}
}
