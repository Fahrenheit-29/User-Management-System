package com.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.caller.Email;
import com.caller.Operation;
import com.database.Dao_ForgotPass;
import com.database.Dao_ResetPass;
import com.entity.User;

@WebServlet("/Controller_ForgotPassword")
public class Controller_ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			
			String email = request.getParameter("email");		  // getting the parameter of email in form field
			Dao_ResetPass execution2=new Dao_ResetPass();		//Database process for reset pass	
			Dao_ForgotPass execution = new Dao_ForgotPass(); 	 // database forgot pass
			Operation operation = new Operation(); 				// all method in a program are here
			Email mail= new Email();							//use email api to send the reset password in gmail of the user
			
			if (execution.checkEmail(email)) {						// if email is true or nandyan  proceed next step

				
				execution2.deleteData(email);   // delete the data in a table to avoid any duplication of data if possible then proceed to insert after delete;
				java.sql.Timestamp intime = new java.sql.Timestamp(new java.util.Date().getTime());
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(intime.getTime());
				cal.add(Calendar.MINUTE, 15);
				java.sql.Timestamp exptime = new Timestamp(cal.getTime().getTime());

				byte[] salt = operation.salt(); 					// convert the secure random to hex to transform it and use in token;
				String token = operation.bytesTostringhex(salt);	//convert to string token 
				
			

				User data=new User(email,token,exptime,intime);	 	 // use javabean or getter for the data to pass it in database
				Dao_ForgotPass execute = new Dao_ForgotPass();  	// method to insert the tokenn and expire time for resetting the link of forgot pass
				int insert =execute.insert_resetpass(data);

				String link = "http://theclientele.us-east-1.elasticbeanstalk.com/ResetPassword";		//link forgot password in email
				String message = "Click <a href=" + link + "?key=" + token + ">Here</a> To  Reset";
				String subject = " YOU RESET PASSWORD ";
			
				
				

				String mess="<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "</head>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "<body>\r\n"
						+ "    <section style=\" background-color: #ECEEF1; max-width: 700px; display: block; margin: 100px auto; padding: 20px 0px; text-align: center; border-radius: 5px; line-height: 1.5; box-sizing: border-box;\">\r\n"
						+ "\r\n"
						+ "        <div class=\"resetpassWrapper\">\r\n"
						+ "            <div class=\"resetpassbg\">\r\n"
						+ "\r\n"
						+ "                <div class=\"subjective-email\">\r\n"
						+ "                    <h1 class=\"title-logo\" style=\" margin-bottom: 30px;\r\n"
						+ "                    color: #0099ff; font-size: 2.5rem;\">[ U. M. S ]</h1>\r\n"
						+ "                    <h5 style=\" font-weight: bold;\">Heads Up</h5>\r\n"
						+ "\r\n"
						+ "                </div>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "                <h5>Hello,</h5>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "                <p style=\" font-size: .9rem;  padding: 15px 0px;\r\n"
						+ "                    word-spacing: 1.5px;\">We've received a request to reset the password for the <b>User Management System</b> <br> account associated with <b>"+ email +".</b> No changes <br> have been made to your account yet.</p>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "                <p style=\" font-size: 1rem\">You can reset your password by clicking the link below:</p>\r\n"
						+ "\r\n"
						+ "                <button class=\" btn btn-primary\" style=\" margin: 20px 0px;  background-color: #0099ff; padding: 10px 40px; border:none; border-radius: 5px; font-size: 1.2rem; \"><a href=" + link + "?key=" + token + " style=\" color: white;\r\n"
						+ "                        text-decoration: none;\">Reset Password</a> </button>\r\n"
						+ "\r\n"
						+ "                <section style=\" font-size: .9rem;  padding: 15px 0px;\r\n"
						+ "                word-spacing: 1.5px;\">\r\n"
						+ "                    <p>If your didnt make this request, please disregard this email</p>\r\n"
						+ "\r\n"
						+ "                    <p>Please note that this link will expire within 15 minutes.</p>\r\n"
						+ "                    <p>After 10 mins, you must submit a new password reset request</p>\r\n"
						+ "\r\n"
						+ "                </section>\r\n"
						+ "\r\n"
						+ "                <h5><b>Need further Assistance?</b></h5>\r\n"
						+ "                    <a href=\"\" style=\" color: #0099ff;\">Contact Us</a>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "            </div>\r\n"
						+ "        </div>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "    </section>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "</body>\r\n"
						+ "\r\n"
						+ "</html>";
					
				
		
				if (insert != 0) {							 	    // if success send forgot password link in email
					//session.setAttribute("forgotpass", "forgotpass");
					mail.sendEmail(email, subject, mess);		//method to send email 
				
					request.getSession().setAttribute("msg", "Please Check Your Email and Click On The Provided Link to Reset Your Password");
					response.sendRedirect("ForgotPassword");

				} else {
					
				
					execution2.deleteData(email);
					request.getSession().setAttribute("msg", "Fail to Insert Data!! Please Check Your Email If Link is Provided or Not, If Link Is Broken Try Again");
					response.sendRedirect("ForgotPassword");
				}

			} else {
			
				request.getSession().setAttribute("msg", "FAIL TO FIND");
				System.out.println("fail to find");
				response.sendRedirect("ForgotPassword");

			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("ALGORITHM EXCEPTION \t" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception \t" +e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL exception"+ e.getMessage());
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
