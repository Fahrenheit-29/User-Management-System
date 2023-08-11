package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Aws_Operation;
import com.caller.Email;
import com.caller.Operation;
import com.database.Dao_ApprovalRequest;


@WebServlet("/Controller_Reject")
public class Controller_Reject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String bucketName="theclientele";
	 final String subdirectory="images/";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		String email=request.getParameter("email");
		String file=request.getParameter("file");		//  get the file use to delete 
		
		Aws_Operation aws=new Aws_Operation(); // operation for Amazon s3 Storage
		
		Operation operation=new Operation();   // operation use here is the file deleted
		
		//String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\File_Folder\\"+file; //concatenate it with the directory
		
		Dao_ApprovalRequest execution=new Dao_ApprovalRequest();	//for databse approval of account
		
		Email mail=new Email();		//java mail api method 
		String subject = "ADMIN";	//subject for Email Api
		String message = " YOUR  ACCOUNT REJECT  BY ADMINISTRATOR OF THE SYSTEM"; //message for Email api
		
		int i=execution.reject(id);
			
		if (i!=0) {
			aws.deleteObject(bucketName, file);
		//operation.deleteFile(uploadpath);	//delete also the file and the users data 
		mail.sendEmail(email, subject, message);
		request.getSession().setAttribute("msg", "<script> alert('Request Process'); </script>");
		response.sendRedirect("Approval");
			
		} else {
			request.getSession().setAttribute("msg", " <script> alert('Admin FAIl'); </script>");
			response.sendRedirect("Approval");
		}
	
		
	}

	

}
