package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Aws_Operation;
import com.caller.Operation;
import com.database.Dao_Delete;

/**
 * Servlet implementation class Controller_Delete
 */
@WebServlet("/Controller_Delete")
public class Controller_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String bucketName="theclientele";
	 final String subdirectory="images/";
 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Dao_Delete execution = new Dao_Delete();
		Operation operation=new Operation();
		Aws_Operation aws= new Aws_Operation();
				
		int id=Integer.parseInt(request.getParameter("id"));
		String file=request.getParameter("file");
		
		int delete=execution.delete(id);
		
	//	String uploadpath="http://theclientele.s3.amazonaws.com/images/"+file; //concatenate it with the directory
	
		
		if (file.equals("default.png")) {
		
			if (delete!=0) {
			//	aws.deleteObject(bucketName, file);
			//	operation.deleteFile(uploadpath);
				request.getSession().setAttribute("msg_del", "<script> alert('Delete Succesful'); </script>");
			
				response.sendRedirect("AdminView");
			} else {
				request.getSession().setAttribute("msg", "<script> alert('Delete Fail'); </script>");
				
				response.sendRedirect("Update?id="+id);
			}
			
		} else {
			
			if (delete!=0) {
					aws.deleteObject(bucketName, file);
				//	operation.deleteFile(uploadpath);
					request.getSession().setAttribute("msg_del", "<script> alert('Delete Succesful'); </script>");
				
					response.sendRedirect("AdminView");
				} else {
					request.getSession().setAttribute("msg", "<script> alert('Delete Fail'); </script>");
					
					response.sendRedirect("Update?id="+id);
				}
		}
		
		
		
		
		
		
	}

	

}
