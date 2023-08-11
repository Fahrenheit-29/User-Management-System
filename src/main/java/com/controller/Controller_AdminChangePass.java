package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.caller.Operation;
import com.database.Dao_ChangePassword;
import com.entity.User;


@WebServlet("/Controller_AdminChangePass")
public class Controller_AdminChangePass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession(false);		//get the session coming from login page which username and oldpassword
		
		Operation operation= new Operation();				//all method are store here which use for operation
		Dao_ChangePassword execution= new Dao_ChangePassword(); // execution of database logic for change passsword
		
		
		try {
			String currentpass=(String)session.getAttribute("password");		 //Session of administrator,password, salt;

			String salt=(String)session.getAttribute("salt");
			String username=(String)session.getAttribute("administrator");	
				
			String oldpass=request.getParameter("oldpass");
			String newpass=request.getParameter("newpass");
			String confirmpass=request.getParameter("confirmpass");
			
			
			String old_hash=operation.hashvalue(oldpass, salt);		//hash yung old pass + salt na galing sa login page na session
			
			
			if(operation.checkInput(oldpass)|| operation.checkInput(newpass) ||operation.checkInput(confirmpass)) {
				
				request.getSession().setAttribute("msg", "All fields are required");
				response.sendRedirect("AdminChangePass");
				return;
			}else if (newpass.length()<8) {
				request.getSession().setAttribute("msg", "8 Characters or More");
				response.sendRedirect("AdminChangePass");
				return;
				
			}else if (!operation.pattern(operation.regex_pass, newpass)) {  // password pattern regex
				
				
				request.getSession().setAttribute("msg", "Need to contain special characters and Letters and numbers");
				response.sendRedirect("AdminChangePass");
				return;
				
			}
			
			
			else if (!old_hash.equals(currentpass)) {
				request.getSession().setAttribute("msg", "Old password doesnt match to the current password");
				response.sendRedirect("AdminChangePass");
				return;
			}
			else if (!newpass.equals(confirmpass)) {
				request.getSession().setAttribute("msg", "Password doesnt match");
				response.sendRedirect("AdminChangePass");
				return;
			}

			else {
				 String salt_value =operation.bytesTostringhex(operation.salt());		//use added salt to make the hash password unique
					String hash_password=operation.hashvalue(newpass, salt_value);  //hash the new password and store it in database
					
					User data= new User(username,hash_password,salt_value);			//hold the data or username,hashpassword and salt
					
					
					int changepass= execution.changepass(data);						//execute the changepassword process
					
					if(changepass!=0) {
						
						request.getSession().setAttribute("msg_change","<script> alert('Change Password Success') </script>");
						response.sendRedirect("Admin");
						return;
						
					}
					else {
						request.getSession().setAttribute("msg", "Change Password Fail");
						response.sendRedirect("AdminChangePass");
						return;
						
					}
					
			}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " Problem In Change Password Controller ");
			e.printStackTrace();
		}
	}

}
