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
import com.database.Dao_ResetPass;
import com.entity.User;


@WebServlet("/Controller_ResetPass")
public class Controller_ResetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private String email;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Operation operation =new Operation();     // all method in a program are here
		Dao_ResetPass execution=new Dao_ResetPass();		//Database process for reset pass	
		HttpSession session=request.getSession();
	
		ResultSet rs=null;
		try {
			
			String token=request.getParameter("key");
			
			String newpass=request.getParameter("new_pass");
			String confirmpass=request.getParameter("confirm_pass");
			
			
			java.sql.Timestamp current_time=new java.sql.Timestamp(new java.util.Date().getTime());  //current time 
			rs=execution.checkToken(token);															//get the token from db
			
			if (rs.next()) {																		//check if token is valid or not
				 email = rs.getString("email");
				java.sql.Timestamp exptime = rs.getTimestamp("exp_time");

				if (current_time.before(exptime)) {													//check if the time is not expired
					
					
					if (operation.checkInput(newpass)|| operation.checkInput(confirmpass)) {		//check if null or not
						
						System.out.println("All field are required" + rs.getString("token"));
						request.getSession().setAttribute("msg", "All field are required");
						response.sendRedirect("ResetPassword?key="+rs.getString("token"));
						
						return;
						
					} else if (!newpass.equals(confirmpass)) {											//check if password is equals to confirm pass
						System.out.println("Passwrod doesnt match");
						request.getSession().setAttribute("msg", "Password doesnt match");
						response.sendRedirect("ResetPassword?key="+rs.getString("token"));
						
						return;
					}
					else if (!operation.pattern(operation.regex_pass, newpass)) {						//check the password using regex
						request.getSession().setAttribute("msg", "Need to contain special characters and Letters and numbers");
						response.sendRedirect("ResetPassword?key="+rs.getString("token"));
						
						return;
					}
					else {
						byte[]salt=operation.salt();													// get random salt 
						
						String salt_value=operation.bytesTostringhex(salt);								//convert random byte salt to string hex
						String hash_password=operation.hashvalue(newpass, salt_value);					//hash passwrod including the salt value
						
						User data= new User(email,hash_password,salt_value);
						
						String validate=execution.updateData(data, email);										//validate if true
						
						if (validate.equals("Success")) {
							
							session.removeAttribute("username");
							session.removeAttribute("administrator");
							session.setAttribute("success", "success");
							request.getSession().setAttribute("msg_reset", "<script> alert('Reset Password Succesful'); </script>");
							response.sendRedirect("LoginPage");
						} else {
							request.getSession().setAttribute("msg", "<script> alert('Fail to Change Pass'); </script>");
							response.sendRedirect("ResetPassword?key="+rs.getString("token"));
							
						}
						
					}
					
					
					 
  
				} 
				
				else {
					System.out.println("SET TIME OF LINK EXPIRE");
					execution.deleteData(email);
					request.getSession().setAttribute("msg_reset", "<script> alert('SET TIME OF LINK EXPIRE TRY AGAIN TO RESET YOUR PASSWORD'); </script>");
				//	session.setAttribute("success", "success");
				session.removeAttribute("success");
					session.removeAttribute("username");
					session.removeAttribute("administrator");
			
					response.sendRedirect("LoginPage");

				}
			}
			else {
				response.getWriter().print("TOKEN DOESnT MATCH TRY TO REACH THE LINK IN THE EMAIL PROVIDED");
				request.getSession().setAttribute("msg_reset", "<script> alert('Token Doesnt Match Try to Reach the Link on the Email your provide'); </script>");
			//	session.setAttribute("success", "success");
		//		execution.deleteData(email); //kaya hindi gumagana yung sa email ta false na  yung sa pag validat ng token gagana lang yun kapg true yung sa token 
				session.removeAttribute("username");
				session.removeAttribute("administrator");
			
				response.sendRedirect("LoginPage");
			}
		
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("controller_resetpass fail" + e.getMessage());
			e.printStackTrace();
		}
		
	 
		
		
		
	}

}
