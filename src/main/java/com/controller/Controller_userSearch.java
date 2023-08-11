package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_Search;
import com.entity.User;


@WebServlet("/Controller_userSearch")
public class Controller_userSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fname=request.getParameter("fname");
		
		

		Dao_Search execution = new Dao_Search();
		List<User>list= new ArrayList<>(); 
		ResultSet rs=execution.search(fname);
		
		try {
		while (rs.next()){
			
			
			User data=new User(Integer.parseInt(rs.getString("User_ID")), rs.getString("First_name"),rs.getString("Last_name"),
					rs.getString("Middle_initial"),rs.getString("Email"),rs.getString("Address"),rs.getString("Phone"),
					rs.getString("Job_Title"),rs.getString("Image_Name"),rs.getString("FullName"));
			
			list.add(data);
		}
		
		request.setAttribute("userSearch", list);
		request.getRequestDispatcher("UserSearch").forward(request, response);
		
			
			
			
			
			
			
		} catch (SQLException e) {
			System.out.println("Controller Search" + e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

	


}
