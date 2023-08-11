package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Dao_Delete {

	
	public int delete(int id) {
		
		int i=0;
		PreparedStatement ps=null;
		Connection con=null;
		try {
			con= new Database().db();	
			String query="DELETE FROM userinfo WHERE User_ID=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			i=ps.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+ "Delete method Problem");
			e.printStackTrace();
		}
		
		
		return i;
	}
}
