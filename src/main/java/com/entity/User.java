package com.entity;

import java.sql.Timestamp;

public class User {

	private String username;
	private String password;
	private String salt;  //salt  & token value
	private String role;
	private String first_name;
	private String last_name;
	private String middle_initial;
	private String email;
	private String address;
	private String  phone;
	private String job_title;
	private String file;
	private String active;
	private int id;

	private Timestamp intime;		//in time of forgot password
	private Timestamp exptime;		//expire time of forgot password
	
	
	private  String fullname; // for concat in data table
	
	
	
	public User(String first_name, String last_name, String middle_initial, String email, String address, String phone,
			String job_title, String file) {          //registration attributes  in parent table
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.middle_initial = middle_initial;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.job_title = job_title;
		this.file = file;
	}
	
	public User(String first_name, String last_name, String middle_initial, String email, String address, String phone,
			String job_title, String file,String active) {          //create account attributes  in parent table
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.middle_initial = middle_initial;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.job_title = job_title;
		this.file = file;
		this.active=active;
	}


	
	
	
	


	public User(int id, String first_name, String last_name, String middle_initial, String email, String address,
			String phone, String job_title, String file, String active, String fullname, String role) {
		super();																				//for admin view attributes
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.middle_initial = middle_initial;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.job_title = job_title;
		this.file = file;
		this.active = active;
		this.fullname = fullname;
		this.role = role;
	}

	public User(String username, String password, String salt, String role) {
		super();									//registration attribute in child table  Login form process 
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.role = role;
	}
	
	




	public User(String email, String salt, Timestamp exptime, Timestamp intime) {
		super(); // for expiration time in forgot password and token

		this.email = email;
		this.salt = salt;
		this.exptime = exptime;
		this.intime = intime;
	}
	
	
	

	public User(String email,String password, String salt) {   // attributes for reset/Change password process  &  can be use as User for login (String username,String password, String role)
		
		super();				
		this.email = email;
		this.password = password;
		this.salt = salt;
	}

	

	
	public User(int id, String first_name, String last_name, String middle_initial, String email, String address,
			String phone, String job_title, String file, String username, String password) {   // attribute for userpage in userprofile data 
		super();																	// attribute for approval request in admin approval page
		this.id = id;				
		this.first_name = first_name;												//attribute for admin update controller
		this.last_name = last_name;
		this.middle_initial = middle_initial;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.job_title = job_title;
		this.file = file;
		this.username = username;
		this.password = password;
	}

	public User(int id, String first_name, String last_name, String middle_initial, String email, String address,
			String phone, String job_title, String file) {   // attribute for userpage in userprofile to update the data
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.middle_initial = middle_initial;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.job_title = job_title;
		this.file = file;
	
	}

	
	
	
	
	public User(int id, String password, String salt) {
		super();
		this.id = id;
		this.password = password;
		this.salt = salt;
	}

	
	
	
	

	public User(int id, String first_name, String last_name, String middle_initial, String email, String address,
			String phone, String job_title, String file, String fullname) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.middle_initial = middle_initial;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.job_title = job_title;
		
		this.file = file;
		this.fullname = fullname;
	}


	public int getId() {
		return id;
	}



	public Timestamp getExptime() {
		return exptime;
	}


	

	public Timestamp getIntime() {
		return intime;
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
  
	 
	public String getSalt() {
		return salt;
	}
	
	
	public String getRole() {
		return role;
	}
	

	
	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getMiddle_initial() {
		return middle_initial;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getJob_title() {
		return job_title;
	}

	public String getFile() {
		return file;
	}

	public String getActive() {
		return active;
	}


	public String getFullname() {
		return fullname;
	}


	
	
	
	
}
