package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.User;

public class Dao_Login {

	public ResultSet login(User data) {
		ResultSet rs = null;
		Connection con=null;
		PreparedStatement ps =null;
		try {
			
			con = new Database().db();
			String query="SELECT * FROM userinfo u  LEFT JOIN account acc ON u.User_ID=acc.account_id WHERE acc.username=? AND acc.password=? AND acc.role=?";
			ps=con.prepareStatement(query);
			ps.setString(1, data.getEmail());				//data.getusername  constructor use here are User(username,password,role);
			ps.setString(2, data.getPassword());			//data.getpassword
			ps.setString(3,data.getSalt());					//data.getrole
			
			rs=ps.executeQuery();
		} catch (SQLException e) {
			
			System.out.println("Login SQL Exception Fail" + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
	
	public String getSalt(String username) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String data = "";

		try {
			con = new Database().db();
			String query = "SELECT username,salt FROM account WHERE username=? ";
			ps = con.prepareStatement(query);
			ps.setString(1, username);

			rs = ps.executeQuery();
			while (rs.next()) {

				data = rs.getString("salt");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + "get the salt from database problem");
			e.printStackTrace();
		}

		return data;
	}

}
