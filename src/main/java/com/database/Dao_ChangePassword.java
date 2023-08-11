package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.entity.User;

public class Dao_ChangePassword {

	public int changepass(User data) {

		int i = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = new Database().db();
		
			String query2 = "UPDATE account SET password=?, salt=? WHERE username=?";
			ps = con.prepareStatement(query2);
			ps.setString(1, data.getPassword()); // data.getpassword
			ps.setString(2, data.getSalt()); // data.getSalt
			ps.setString(3, data.getEmail()); // data.getusername constructor use here are User(username,password,salt);

			i = ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e.getMessage() + "Problem in Dao_ ChangePassword");
			e.printStackTrace();
		}

		return i;
	}

}
