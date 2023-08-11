package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Email;
import com.database.Dao_ApprovalRequest;


@WebServlet("/Controller_Status")
public class Controller_Status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String email=request.getParameter("email");
		
		Dao_ApprovalRequest execution=new Dao_ApprovalRequest();	//for databse approval of account
		
		Email mail=new Email();		//java mail api method 
		String subject = "ADMIN";	//subject for Email Api
		String message = " YOUR  ACCOUNT APPROVED BY ADMINISTRATOR OF THE SYSTEM"; //message for Email api
		int i=execution.approve(id);
			
		if (i!=0) {
			
			
		mail.sendEmail(email, subject, message);
		request.getSession().setAttribute("msg", "<script> alert('Request Process'); </script>");
		response.sendRedirect("Approval");
			
		} else {
			request.getSession().setAttribute("msg", " <script> alert('Admin FAIl'); </script>");
			response.sendRedirect("Approval");
		}
	
		
		
		
		
		
		
	
	}

}
