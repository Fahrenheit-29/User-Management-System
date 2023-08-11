package com.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.caller.Operation;
import com.database.Dao_Login;
import com.entity.User;

@WebServlet("/Controller_Login")
public class Controller_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hash_password;
		Operation operation = new Operation(); // all method in a program are here
		Dao_Login execution = new Dao_Login(); // database login
		HttpSession session =request.getSession();
		ResultSet rs = null;
		try {
			
			
			String username = request.getParameter("user");
			String password = request.getParameter("pass");
			String role = request.getParameter("role");

			if (operation.checkInput(username) || operation.checkInput(password) || operation.checkInput(role)) {
				
				request.getSession().setAttribute("msg", "All field are required");
				
				response.sendRedirect("LoginPage");

			} else {

				String salt = execution.getSalt(username);  //get the salt in database through using the paramater of username
				System.out.println(salt);

				hash_password = operation.hashvalue(password, salt);
				System.out.println(hash_password);
				User data = new User(username, hash_password, role);
				rs = execution.login(data);

				if (rs.next()) {					//check if  match the account in Database if true proceed to other validation
					
					
					if (role.equals("Admin")) {				// proceed to  administrator side 
						session.setAttribute("administrator", username);
						session.setAttribute("password", hash_password);
						session.setAttribute("salt", rs.getString("salt"));
						response.sendRedirect("Admin");
						
					
					}
			
					
					else  {     										// if role is equals to User  proceed to UserPage
						if (rs.getString("Active").equals("Yes")) {		// check if the approval of admin is Yes if acctive is Yes it can proceed to the System else approval of admin need
							
							User userprofile= new User(Integer.parseInt(rs.getString("User_ID")),rs.getString("First_name"),rs.getString("Last_name"),
									rs.getString("Middle_initial"),rs.getString("Email"),rs.getString("Address"),rs.getString("Phone"), 
									rs.getString("Job_Title"), rs.getString("Image_Name"));
							session.setAttribute("userprofile", userprofile);
						
							session.setAttribute("username", username);
							session.setAttribute("password", hash_password);
							session.setAttribute("salt", rs.getString("salt"));
						
							response.sendRedirect("UserPage");
							
						} else {
							request.getSession().setAttribute("msg", "Need an Admin Approval of the Administrator");
							session.setAttribute("username1", username);
							response.sendRedirect("Validate");
						}
						
					}

				} else {  							// if input account doesnt match in database
					request.getSession().setAttribute("msg", "Username and Password is Incorrect");
					session.setAttribute("username1", username);
					response.sendRedirect("Validate");

				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + "NO algorithm available");
			e.printStackTrace();
		}

	}
 
}
