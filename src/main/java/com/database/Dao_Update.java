package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;


public class Dao_Update {

	
	public int updateProfile(User data) {			//  database process  that update the profile of user in user side of the system
		
		int i=0;
		PreparedStatement ps=null;
		Connection con=null;
		try {
			con= new Database().db();	
			String query="UPDATE userinfo u JOIN account acc ON u.User_ID=acc.account_id SET u.First_name=?, u.Last_name=?, u.Middle_initial=?,u.Email=?,u.Address=?,u.Phone=?,u.Job_Title=?,u.Image_Name=?  WHERE u.User_ID=?";
			ps=con.prepareStatement(query);
			ps.setString(1, data.getFirst_name());
			ps.setString(2, data.getLast_name());
			ps.setString(3, data.getMiddle_initial());
			ps.setString(4, data.getEmail());
			ps.setString(5, data.getAddress());
			ps.setString(6, data.getPhone());
			ps.setString(7, data.getJob_title());
			ps.setString(8, data.getFile());
			ps.setInt(9, data.getId());
			
			i=ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+ "Update Profile Problem");
			e.printStackTrace();
			
			}
	
		return i;
		
		
		
	}
	
	public int adminUpdate(User data) {			//  database process  that update the profile of user in user side of the system
		
		int i=0;
		PreparedStatement ps=null;
		Connection con=null;
		try {
			con= new Database().db();	
			String query="UPDATE userinfo u JOIN account acc ON u.User_ID=acc.account_id SET u.First_name=?, u.Last_name=?, u.Middle_initial=?,u.Email=?,u.Address=?,u.Phone=?,u.Job_Title=?,u.Image_Name=?,u.Active=?, acc.role=?  WHERE u.User_ID=?";
			ps=con.prepareStatement(query);
			ps.setString(1, data.getFirst_name());
			ps.setString(2, data.getLast_name());
			ps.setString(3, data.getMiddle_initial());
			ps.setString(4, data.getEmail());
			ps.setString(5, data.getAddress());
			ps.setString(6, data.getPhone());
			ps.setString(7, data.getJob_title());
			ps.setString(8, data.getFile());
			ps.setString(9, data.getUsername());		//data.getActive
			ps.setString(10, data.getPassword());		//data.getRole
			ps.setInt(11, data.getId());
			i=ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+ "Update Profile Problem");
			e.printStackTrace();
			
			}
	
		return i;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public User get_id(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		User user=null;
	
		try {
			 con=new Database().db();
			String query="SELECT u.User_ID, u.First_name, u.Last_name, u.Middle_initial, u.Email, u.Address, u.Phone, u.Job_Title,u.Image_Name, u.Active,\r\n"
					+ "concat_ws(' ',u.First_name, u.Last_name ) AS FullName, acc.role\r\n"
					+ " FROM userinfo u LEFT JOIN account acc ON\r\n"
					+ " u.User_ID=acc.account_id WHERE u.User_ID=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				 user=new User(Integer.parseInt(rs.getString("User_ID")), rs.getString("First_name"),rs.getString("Last_name"),
						rs.getString("Middle_initial"),rs.getString("Email"),rs.getString("Address"),rs.getString("Phone"),
						rs.getString("Job_Title"),rs.getString("Image_Name"),rs.getString("Active"),rs.getString("FullName"),rs.getString("role"));
				
				
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update method fail" + e.getMessage());
		}
		
		return user;
	}

	
	
}
	
	
	
