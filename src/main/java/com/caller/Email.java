package com.caller;

import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.database.Dao_ForgotPass;

public class Email {

	private String username;
	private final String app_Password="xutserlbdlccuylq";
	public void sendEmail(String to, String subject, String message) {
		
		
	
		try {
			Dao_ForgotPass execution = new Dao_ForgotPass();
			ResultSet rs=null;
			rs=execution.smtp_account();
			
			while(rs.next()) {
				
				username=rs.getString("username");
				
				
			}
			rs.close();
			
			
			
	
			
			Properties props= new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); 
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.socketFactory.port", 465);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(username, app_Password);
				}
			});
			
		MimeMessage msg=new MimeMessage(session);
		msg.setFrom(new InternetAddress(username));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		msg.setContent(message,"text/html");
		
		Transport.send(msg);
		
		//System.out.println("Success message \t " + msg);
			
			
			
			
	
		} catch (Exception e) {
			System.out.println("Exception in Email method \t "+ e.getMessage());
		e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
