package com.controller;


import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.caller.Aws_Operation;
import com.caller.Operation;
import com.database.Dao_Register;
import com.entity.User;

@WebServlet("/Controller_Registration")
@MultipartConfig
public class Controller_Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String bucketName="theclientele";
	 final String subdirectory="images/";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Aws_Operation aws=new Aws_Operation(); // operation for Amazon s3 Storage
		
			Operation operation=new Operation();  // all method that can be use in this program
			HttpSession session=request.getSession();	
			
			String username = request.getParameter("user");
			String password = request.getParameter("pass");
			String role = request.getParameter("role");

			String first_name = request.getParameter("f_name");
			String last_name = request.getParameter("l_name");
			String mi = request.getParameter("mi");
			String email = request.getParameter("email");
			String address = request.getParameter("addr");
			String phone = request.getParameter("phone");
			String job = request.getParameter("job");
		
			Part filepart=request.getPart("file");
		
			String file=filepart.getSubmittedFileName();			// get the file name of the file that upload
			
			
			
			 InputStream fileInputStream = filepart.getInputStream();
			 
			 aws.aws3Upload(bucketName, file, subdirectory, fileInputStream); // upload the image file in amazon s3 Storage
			 
				/*
				 * String
				 * uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\File_Folder\\"
				 * +file; // use uploadpath
				 * 
				 * 
				 * try {
				 * 
				 * FileOutputStream fos= new FileOutputStream(uploadpath); InputStream
				 * stream=filepart.getInputStream();
				 * 
				 * byte[]data=new byte[stream.available()]; stream.read(data); fos.write(data);
				 * fos.close();
				 * 
				 * } catch (Exception e) { System.out.println("Problem fail in file upload \n"
				 * +e.getMessage()); e.printStackTrace(); }
				 */
			 
		//	VALIDATION 
			
		if (operation.checkInput(username)|| operation.checkInput(password)|| operation.checkInput(first_name) || operation.checkInput(last_name)
				|| operation.checkInput(email) || operation.checkInput(address) ||operation.checkInput(phone)||
				operation.checkInput(job) || filepart==null) {
			
			aws.deleteObject(bucketName, file);
			//operation.deleteFile(uploadpath);
			request.getSession().setAttribute("msg_reg", "All field are required");
			response.sendRedirect("RegistrationPage");
			return;
				
			
		} else if (password.length()<8) {
			aws.deleteObject(bucketName, file);
		//	operation.deleteFile(uploadpath);
			request.getSession().setAttribute("msg_reg", "8 Characters or More");
			response.sendRedirect("RegistrationPage");
			return;
			
		
	
	
		}else if (!operation.pattern(operation.regex_pass, password)) {  // password pattern regex
			aws.deleteObject(bucketName, file);
			//operation.deleteFile(uploadpath);
			request.getSession().setAttribute("msg_reg", "Need to contain special characters and Letters and numbers");
			response.sendRedirect("RegistrationPage");
			return;
			
		} else if (!operation.pattern(operation.owasp_email, email)) {   	//email patten ""
			aws.deleteObject(bucketName, file);
			//operation.deleteFile(uploadpath);
			request.getSession().setAttribute("msg_reg", "Need a valid email");
			response.sendRedirect("RegistrationPage");
			return;
		}else if (!operation.pattern(operation.regex_phNumber, phone)) {     // ph phone number pattern ""
			aws.deleteObject(bucketName, file);
			//operation.deleteFile(uploadpath);
			request.getSession().setAttribute("msg_reg", "Invalid Phone Number");
			response.sendRedirect("RegistrationPage");
		}else {
				
			try {
				
			
			
			byte[]salt=operation.salt();
			String salt_value=operation.bytesTostringhex(salt);
			String hash_password=operation.hashvalue(password, salt_value);
			
			
			
			User data = new User(first_name, last_name, mi, email, address, phone, job, file);
			User data2= new User(username,hash_password,salt_value,role);
			Dao_Register execution  = new Dao_Register();   //registration operation in database 
			
			String validate=execution.register(email, username, data, data2);
			
			
			if (validate.equals("Email")) {
				aws.deleteObject(bucketName, file);
			//	operation.deleteFile(uploadpath);	
				request.getSession().setAttribute("msg_reg",  " <script> alert('Email Already Exist')  </script>");
				response.sendRedirect("RegistrationPage");
				
			}else if (validate.equals("Username")) {
				aws.deleteObject(bucketName, file);
			//	operation.deleteFile(uploadpath);
				request.getSession().setAttribute("msg_reg",  " <script> alert('Username Already Exist')  </script>");
				response.sendRedirect("RegistrationPage");
				
			}
			else if (validate.equals("Success")) {
				request.getSession().setAttribute("msg",  " <script> alert('Succesful Registration')  </script>");
				session.setAttribute("reg_success", "registration Success");
				response.sendRedirect("LoginPage");
				
			}
			else {
				aws.deleteObject(bucketName, file);
				//operation.deleteFile(uploadpath);
				request.getSession().setAttribute("msg_reg", "ERROR");
				response.sendRedirect("RegistrationPage");
				
			}
			
		
			
			
			}  catch (NoSuchAlgorithmException e) {
				System.out.println("NO ALGORITHM \t" +e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("SQL EXCEPTION IN REGISTRATION \t "+ e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception error" +e.getLocalizedMessage());
			}
			
		}
			
			
			
			
			



	}

}
