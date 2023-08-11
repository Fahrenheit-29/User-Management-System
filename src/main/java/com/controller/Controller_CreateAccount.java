package com.controller;

import java.io.FileOutputStream;
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
import javax.servlet.http.Part;

import com.caller.Aws_Operation;
import com.caller.Operation;
import com.database.Dao_Register;
import com.entity.User;


@WebServlet("/Controller_CreateAccount")
@MultipartConfig
public class Controller_CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String bucketName="theclientele";
	 final String subdirectory="images/";
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Operation operation=new Operation();  // all method that can be use in this program
		Aws_Operation aws=new Aws_Operation(); // operation for Amazon s3 Storage
		
		
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
		String status=request.getParameter("status");
		
		Part filepart=request.getPart("file");
		
		String file=filepart.getSubmittedFileName();
		 InputStream fileInputStream = filepart.getInputStream();
		 
		 aws.aws3Upload(bucketName, file, subdirectory, fileInputStream);
		 
//		 
//		String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\File_Folder\\"+file;
//		
//
//		try {
//			
//			FileOutputStream fos= new FileOutputStream(uploadpath);	
//			InputStream stream=filepart.getInputStream();
//			byte[]data=new byte[stream.available()];
//			stream.read(data);
//			fos.write(data);
//			fos.close();
//			
//		} catch (Exception e) {
//			System.out.println("Problem fail in file upload \n" +e.getMessage());
//			e.printStackTrace();
//		}
	
		//VALIDATION 
		
	if (operation.checkInput(username)|| operation.checkInput(password)|| operation.checkInput(first_name) || operation.checkInput(last_name)
			||operation.checkInput(mi)|| operation.checkInput(email) || operation.checkInput(address) ||operation.checkInput(phone)||
			operation.checkInput(job) || filepart==null) {
		aws.deleteObject(bucketName, file);
	//	operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 
		request.getSession().setAttribute("msg_create", "All field are required");
		response.sendRedirect("Create_Account");
		return;
			
		
	} else if (password.length()<8) {
		aws.deleteObject(bucketName, file);
	//	operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 
		request.getSession().setAttribute("msg_create", "8 Characters or More");
		response.sendRedirect("Create_Account");
		return;
		
	


	}else if (!operation.pattern(operation.regex_pass, password)) {  // password pattern regex
		
		aws.deleteObject(bucketName, file);
		//operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 
		request.getSession().setAttribute("msg_create", "Need to contain special characters and Letters and numbers");
		response.sendRedirect("Create_Account");
		return;
		
	} else if (!operation.pattern(operation.owasp_email, email)) {   	//email patten ""
		aws.deleteObject(bucketName, file);
		//operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 
		request.getSession().setAttribute("msg_create", "Need a valid email");
		response.sendRedirect("Create_Account");
		return;
	}else if (!operation.pattern(operation.regex_phNumber, phone)) {     // ph phone number pattern ""
		aws.deleteObject(bucketName, file);
	//	operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 
		request.getSession().setAttribute("msg_create", "Invalid Phone Number");
		response.sendRedirect("Create_Account");
		
	}else {
			
		try {
			
			String default_file="default.png";
		
		byte[]salt=operation.salt();
		String salt_value=operation.bytesTostringhex(salt);
		String hash_password=operation.hashvalue(password, salt_value);
		
		if (!operation.checkInput(file)) {    							//check if file is not null
			
			
			
			User data = new User(first_name, last_name, mi, email, address, phone, job, file,status);
			User data2= new User(username,hash_password,salt_value,role);
			Dao_Register execution  = new Dao_Register();   //registration operation in database 
			
			
			
			
			
			String validate=execution.createAccount(email, username, data, data2);
			
			
			if (validate.equals("Email")) {
				aws.deleteObject(bucketName, file);
			//	operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 	
				request.getSession().setAttribute("msg_create",  " <script> alert('Email Already Exist')  </script>");
				response.sendRedirect("Create_Account");
				
			}else if (validate.equals("Username")) {
				aws.deleteObject(bucketName, file);
			//	operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 
				request.getSession().setAttribute("msg_create",  " <script> alert('Username Already Exist')  </script>");
				response.sendRedirect("Create_Account");
				
			}
			else if (validate.equals("Success")) {
				request.getSession().setAttribute("msg",  " <script> alert('Succesful Registration')  </script>");
				response.sendRedirect("AdminView");
				
			}
			else {
				aws.deleteObject(bucketName, file);
			//	operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 
				request.getSession().setAttribute("msg_create", "ERROR");
				response.sendRedirect("Create_Account");
				
			}
			
			
			
		} else {

			
			User data = new User(first_name, last_name, mi, email, address, phone, job, default_file,status);
			User data2= new User(username,hash_password,salt_value,role);
			Dao_Register execution  = new Dao_Register();   //registration operation in database 
			
			
			
			
			
			String validate=execution.createAccount(email, username, data, data2);
			
			
			if (validate.equals("Email")) {
				aws.deleteObject(bucketName, file);
			//	operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 	
				request.getSession().setAttribute("msg_create",  " <script> alert('Email Already Exist')  </script>");
				response.sendRedirect("Create_Account");
				
			}else if (validate.equals("Username")) {
				aws.deleteObject(bucketName, file);
				//operation.deleteFile(uploadpath); 				//delete the recent file or file upload if it is fail to process 
				request.getSession().setAttribute("msg_create",  " <script> alert('Username Already Exist')  </script>");
				response.sendRedirect("Create_Account");
				
			}
			else if (validate.equals("Success")) {
				request.getSession().setAttribute("msg",  " <script> alert('Succesful Registration')  </script>");
				response.sendRedirect("AdminView");
				
			}
			else {
				aws.deleteObject(bucketName, file);
						//delete the recent file or file upload if it is fail to process 
				request.getSession().setAttribute("msg_create", "ERROR");
				response.sendRedirect("Create_Account");
				
			}
			
			
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
	}}

}
