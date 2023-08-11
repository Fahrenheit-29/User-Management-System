package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_View;


@WebServlet("/Controller_View")
public class Controller_View extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Dao_View execution= new Dao_View();
		List list=execution.view();
		
		
		if(list==null && list.size()==0) {
			
			request.getSession().setAttribute("msg", "No Record Found");
			response.sendRedirect("View");
			
	}
		request.getSession().setAttribute("data", list);
		response.sendRedirect("View");
	//	request.getRequestDispatcher("View").forward(request, response);
		 
	  
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
