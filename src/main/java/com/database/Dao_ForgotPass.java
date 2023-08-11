package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.User;

public class Dao_ForgotPass {

	public boolean checkEmail(String email) { 				// use to check the email if valid or not to give the use an message that their password rest link is send to their email
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = new Database().db();

			String query = "SELECT 1 FROM userinfo WHERE email=?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {

				return true;
			}

			

		} catch (Exception e) {
			System.out.println("Problem in checking email in database \t " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ResultSet smtp_account() {  					// use in smtp account for java mail api  to send an email
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = new Database().db();
			String query = "SELECT username, password FROM smtp_account";
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

		} catch (Exception e) {
			System.out.println("Smtp Account Fail \t"+  e.getMessage());
			e.printStackTrace();
		}
		return rs;

	}
	
	public int insert_resetpass(User data) throws SQLException {         // insert the data in reset table in order to validate if token is valid in store in database and if time is not expired
		
		int insert=0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = new Database().db();
			
			String query="INSERT INTO reset_passwordtable()VALUES(?,?,?,?)";
			ps=con.prepareStatement(query);
			ps.setString(1, data.getEmail());
			ps.setString(2, data.getSalt());
			ps.setTimestamp(3, data.getExptime());
			ps.setTimestamp(4, data.getIntime());
			
			insert=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insert resetpass fail " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			if (rs!=null) {
				
				rs.close();
			}
			if (ps!=null) {
				ps.close();
			}
			if (con!=null) {
				con.close();
			}
		}
		
		
		
		return insert;
	}
	

	

}
