package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;

public class Dao_View {

	public List<User> view() {			// for user view page

		var list = new ArrayList<User>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=new Database().db();
			String query="CALL VIEW()";   // use stored procedure here also security to avoid the sql injection if ever happen
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				User data=new User(Integer.parseInt(rs.getString("User_ID")), rs.getString("First_name"),rs.getString("Last_name"),
						rs.getString("Middle_initial"),rs.getString("Email"),rs.getString("Address"),rs.getString("Phone"),
						rs.getString("Job_Title"),rs.getString("Image_Name"),rs.getString("FullName"));
				
				
				list.add(data);			
			
		}		
			
			
		} catch (Exception e) {

			System.out.println(e.getMessage() + " Dao_View Problem");
			e.printStackTrace();
		}

		return list;

	}
	
	public List<User> adminview() {			//for admin view page

		var list = new ArrayList<User>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=new Database().db();
			String query="CALL AdminView()";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				User data=new User(Integer.parseInt(rs.getString("User_ID")), rs.getString("First_name"),rs.getString("Last_name"),
						rs.getString("Middle_initial"),rs.getString("Email"),rs.getString("Address"),rs.getString("Phone"),
						rs.getString("Job_Title"),rs.getString("Image_Name"),rs.getString("Active"),rs.getString("FullName"),rs.getString("role"));
				
				
				list.add(data);			
			
		}		
			
			
		} catch (Exception e) {

			System.out.println(e.getMessage() + " Dao_AdminView Problem");
			e.printStackTrace();
		}

		return list;

	}
	
	
	
	
	
	
	
	public List<User> approval_request() {				//for approval request view in admin side

		var list = new ArrayList<User>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=new Database().db();
			String query="CALL APPROVAL()";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				User data=new User(Integer.parseInt(rs.getString("User_ID")), rs.getString("First_name"),rs.getString("Last_name"),
						rs.getString("Middle_initial"),rs.getString("Email"),rs.getString("Address"),rs.getString("Phone"),
						rs.getString("Job_Title"),rs.getString("Image_Name"),rs.getString("Active"),rs.getString("FullName"));
				
				
				list.add(data);			
			
		}		
			
			
		} catch (Exception e) {

			System.out.println(e.getMessage() + " Dao_View Problem");
			e.printStackTrace();
		}

		return list;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	public int total_user() {				//total number of user active or inactive in admin side
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int total=0;
		
		try {
			con=new Database().db();
			
			String query="SELECT count(active) AS TotalUser FROM  userinfo u LEFT JOIN account acc ON u.User_ID=account_id WHERE NOT acc.role='Admin'";
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			
			while (rs.next()){
				

				total=rs.getInt(1);
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " Dao_View Problem");
			e.printStackTrace();
		}
		
		
		return total;
	}
	public int total_active() {				//total # of active user in admin side
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int total=0;
		
		try {
			con=new Database().db();
			
			String query="SELECT count(active) AS Total_Active FROM userinfo u LEFT JOIN account acc ON u.User_ID=account_id WHERE u.Active='Yes' AND NOT acc.role='admin'";

			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			
			while (rs.next()){
				

				total=rs.getInt(1);
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " Dao_View Problem");
			e.printStackTrace();
		}
		
		
		return total;
	}
	public int total_pending() {				//total # of pending user in admin side
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int total=0;
		
		try {
			con=new Database().db();
			
			String query="SELECT count(active) AS Total_Pending FROM userinfo u LEFT JOIN account acc ON u.User_ID=account_id WHERE u.Active='No' AND NOT acc.role='admin'";

			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			
			while (rs.next()){
				

				total=rs.getInt(1);
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " Dao_View Problem");
			e.printStackTrace();
		}
		
		
		return total;
	}
	
	
	
}
