package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.caller.Aws_Operation;
import com.caller.Operation;
import com.database.Dao_Update;
import com.entity.User;


@WebServlet("/Controller_AdminUpdate")
@MultipartConfig
public class Controller_AdminUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String bucketName="theclientele";
	 final String subdirectory="images/";
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Aws_Operation aws=new Aws_Operation(); // operation for Amazon s3 Storage
		
		Operation operation = new Operation();  // all method that can be use in this program
		try {
			
			int id=Integer.parseInt(request.getParameter("id"));
			String first_name = request.getParameter("fname");
			String last_name = request.getParameter("lname");
			String mi = request.getParameter("mi");
			String email = request.getParameter("email");
			String address = request.getParameter("addr");
			String phone = request.getParameter("phone");
			String job = request.getParameter("job");
			String status=request.getParameter("status");
			String role=request.getParameter("role");

			
			Part filepart=request.getPart("file");			//getting the file upload in form based
	
		
			
			
			
			String file=filepart.getSubmittedFileName();				//get the exact name of the file
			 InputStream fileInputStream = filepart.getInputStream();
			 
			 aws.aws3Upload(bucketName, file, subdirectory, fileInputStream);
//			String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\File_Folder\\"+file; //concatenate it with the directory
//		
//			try {
//				
//				FileOutputStream fos= new FileOutputStream(uploadpath);	
//				InputStream stream=filepart.getInputStream();
//				byte[]data=new byte[stream.available()];
//				stream.read(data);
//				fos.write(data);
//				fos.close();
//				
//			} catch (Exception e) {
//				System.out.println("Problem fail in file upload \n" +e.getMessage());
//				e.printStackTrace();
//			}
//			
//			
			
			if(operation.checkInput(first_name)|| operation.checkInput(last_name) || operation.checkInput(mi)||operation.checkInput(email)
					|| operation.checkInput(address)|| operation.checkInput(phone) ||operation.checkInput(job)) {
				aws.deleteObject(bucketName,file);
			//	operation.deleteFile(uploadpath);
				request.getSession().setAttribute("msg", "All field are required");
				System.out.println("TRUE");
				response.sendRedirect("Update?id="+id);
				return;
			}
			
			
			else if (!operation.pattern(operation.owasp_email, email)) {   	//email patten ""
				
				aws.deleteObject(bucketName, file);
				//operation.deleteFile(uploadpath);
				request.getSession().setAttribute("msg", "Need a valid email");
				System.out.println("1");
				response.sendRedirect("Update?id="+id);
				
				return;
			}else if (!operation.pattern(operation.regex_phNumber, phone)) {     // ph phone number pattern ""
				aws.deleteObject(bucketName, file);
				//operation.deleteFile(uploadpath);
				request.getSession().setAttribute("msg", "Invalid Phone Number");
				System.out.println("2");
				response.sendRedirect("Update?id="+id);
				
			}else {
				String oldfile=request.getParameter("oldfile");	//old path coming from the update jsp
				String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\File_Folder\\"+oldfile; // old path and file

				Dao_Update execution= new Dao_Update();
				User data=new User(id,first_name,last_name,mi,email,address,phone,job,file,status,role);
				
				if(!operation.checkInput(file)) {				// check if file is not null
					
					
					if(!file.equals("default.png")) {			//check if file is not equals to default file
						
							if(!oldfile.equals("default.png")) {		// if oldpath is not equals to  default.png process yung pagpalit ng file na buburain yung old file
					
								aws.deleteObject(bucketName, oldfile);
					
						int update=execution.adminUpdate(data);
						if (update!=0) {
							
							request.getSession().setAttribute("msg_update", "<script> alert('Update Succesful') </script>");
							response.sendRedirect("AdminView");
						}
							
							
							
						else {
							aws.deleteObject(bucketName, file);
					//		operation.deleteFile(uploadpath);
							request.getSession().setAttribute("msg", "Update Fail and Check if your File Name is Not default.png");
							System.out.println("3");
							response.sendRedirect("Update?id="+id);

						}
						
							}else {
									
							//same process mag u update pa din  pero hindi mag de delete ng old files sa path nya 
								int update=execution.adminUpdate(data);
								if (update!=0) {
									
									request.getSession().setAttribute("msg_update", "<script> alert('Update Succesful') </script>");
									response.sendRedirect("AdminView");
								}
									
									
									
								else {
									aws.deleteObject(bucketName, file);
								//	operation.deleteFile(uploadpath);
									request.getSession().setAttribute("msg", "Update Fail and Check if your File Name is Not default.png");
									System.out.println("3");
									response.sendRedirect("Update?id="+id);

								}
								
								
								
							
							}
						
					}
					else {
						int updates=execution.adminUpdate(data);
						
						if (updates!=0) {
							
							request.getSession().setAttribute("msg_update", "Update Successful");
							response.sendRedirect("AdminView");
						}
						else {
							aws.deleteObject(bucketName, file);
						//	operation.deleteFile(uploadpath);
							request.getSession().setAttribute("msg", "Update Fail");
							System.out.println("4");
							response.sendRedirect("Update?id="+id);

						}
						
						
					}
				}
				else {
					
					User data2=new User(id,first_name,last_name,mi,email,address,phone,job,oldfile,status,role);
					int update=execution.adminUpdate(data2);
					
					if (update!=0) {
						
						request.getSession().setAttribute("msg_update", "Update Successful");
						response.sendRedirect("AdminView");
					}
					else {
						
						request.getSession().setAttribute("msg", "Update Fail and Check if your File Name is Not default.png");
						response.sendRedirect("Update?id="+id);

					}
					
					
					
					
					
					
				}
				
			
			}
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Controller edit profile problem" + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
