package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.User;

public class Dao_ResetPass {

	public ResultSet checkToken(String token) {        // use to check the token first if valid or not in resetpassword controller to proceed on the next steps

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = new Database().db();
			String query = "SELECT email, token, exp_time  FROM reset_passwordtable WHERE token=?";

			ps = con.prepareStatement(query);
			ps.setString(1, token);
			rs = ps.executeQuery();

		} catch (Exception e) {
			System.out.println(" check token method fail \t" + e.getMessage());

			e.printStackTrace();
		}

		return rs;
	}
	public String  updateData (User data, String email) {        // use to update the data of the resetpassword operation
		
		PreparedStatement ps = null;
		try {
			Connection con=new Database().db();
			
			
			String query="UPDATE account acc JOIN userinfo u  ON acc.account_id=u.User_ID SET acc.password=?, acc.salt=? WHERE u.email=?";
			ps=con.prepareStatement(query);
			ps.setString(1, data.getPassword());
			ps.setString(2, data.getSalt());
			ps.setString(3, data.getEmail());
			
			int saved=ps.executeUpdate();
			if (saved!=0) {
				
				String query2="DELETE FROM reset_passwordtable WHERE email=?";
				ps=con.prepareStatement(query2);
				ps.setString(1, email);
				ps.executeUpdate();
				
				
				return "Success";
			}
			
			
			
			
			// use join statment to update the data in database for reset password 
			// next todo list
			
			
			
		} catch (SQLException e) {
			
			System.out.println("Sql Exception in Dao_ResetPass \t"+ e.getMessage());
			e.printStackTrace();
		}
		return "Fail" ;
		
		
	}
	
	
	public void deleteData(String email) {      // use to delete the data in reset password table if the condition is met under the controller forgot password 
		
		
		PreparedStatement ps = null;
		try {
			Connection con=new Database().db();
			
			String query="DELETE FROM reset_passwordtable WHERE email=?";
			ps=con.prepareStatement(query);
			ps.setString(1,email);
			
		ps.executeUpdate();
		
			System.out.println("delete data");
			
			
	
		
			
		} catch (SQLException e) {
			
			System.out.println("Sql Exception in Dao_ResetPass \t"+ e.getMessage());
			e.printStackTrace();
		}
		
		

		
	}
	
	public boolean validateToken(String key) {   //this method use in resetpassword page that will check if token is valid or not
		Connection con=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			con=new Database().db();
			String query="SELECT 1 FROM  reset_passwordtable WHERE token=? ";
			ps=con.prepareStatement(query);
			ps.setString(1, key);
			rs=ps.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Sql Exception in Dao_ResetPass validating the token  \t"+ e.getMessage());
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	
}
