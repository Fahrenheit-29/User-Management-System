package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.User;

public class Dao_Register {

	public String register(String email, String username, User data1, User data2) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		con = new Database().db();
		try {
			con.setAutoCommit(false);									
			String query = "SELECT 1 FROM userinfo WHERE email=?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {

				return "Email";

			} else {
				String query2 = "INSERT INTO userinfo (First_Name,Last_Name,Middle_Initial,Email,Address,Phone,Job_Title,Image_Name)VALUES(?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(query2);
				ps.setString(1, data1.getFirst_name());
				ps.setString(2, data1.getLast_name());
				ps.setString(3, data1.getMiddle_initial());
				ps.setString(4, data1.getEmail());
				ps.setString(5, data1.getAddress());
				ps.setString(6, data1.getPhone());
				ps.setString(7, data1.getJob_title());
				ps.setString(8, data1.getFile());

				int register = 0;
				register = ps.executeUpdate();

				if (register != 0) {

					try {
						String query3 = "SELECT 1 FROM account WHERE username=?";
						ps = con.prepareStatement(query3);
						ps.setString(1, username);
						rs = ps.executeQuery();

						if (rs.next()) {

							return "Username";
						} else {
							String query4 = "INSERT INTO account (username,password,salt,role,account_id)VALUES(?,?,?,?,LAST_INSERT_ID()) ";
							ps1 = con.prepareStatement(query4);
							ps1.setString(1, data2.getUsername());
							ps1.setString(2, data2.getPassword());
							ps1.setString(3, data2.getSalt());
							ps1.setString(4, data2.getRole());

							int register_again = 0;

							register_again = ps1.executeUpdate();

							if (register_again != 0) {

								System.out.println("REGISTER SUCCESFUL");
								con.commit();
								return "Success";

							}

						}

					} catch (Exception e) {
						System.out.println("ROLLBACK EXECUTED");
						con.rollback();
						System.out.println("SAVED IN PARENT TABLE FAIL " + e.getMessage());

						e.printStackTrace();
					}

				}

			}
		
			
		} catch (Exception e) {
			try {
				System.out.println("rollback happen");
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("\t SQL E1 exception Faik" + e1.getMessage());
				e1.printStackTrace();
			}
			System.out.println("\t Dao_Registration Error \t" + e.getMessage());

		} finally {
			if (rs != null) {
				rs.close();

			}
			if (ps != null) {
				System.out.println("CLOSE");
				ps.close();

			}
			if (ps1 != null) {
				ps1.close();

			}

			if (con != null) {
				con.close();

			}

		}

		return "Error";
	}
	
	public String createAccount(String email, String username, User data1, User data2) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		con = new Database().db();
		try {
			con.setAutoCommit(false);
			String query = "SELECT 1 FROM userinfo WHERE email=?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {

				return "Email";

			} else {
				String query2 = "INSERT INTO userinfo (First_Name,Last_Name,Middle_Initial,Email,Address,Phone,Job_Title,Image_Name,Active)VALUES(?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(query2);
				ps.setString(1, data1.getFirst_name());
				ps.setString(2, data1.getLast_name());
				ps.setString(3, data1.getMiddle_initial());
				ps.setString(4, data1.getEmail());
				ps.setString(5, data1.getAddress());
				ps.setString(6, data1.getPhone());
				ps.setString(7, data1.getJob_title());
				ps.setString(8, data1.getFile());
				ps.setString(9, data1.getActive());
				int register = 0;
				register = ps.executeUpdate();

				if (register != 0) {

					try {
						String query3 = "SELECT 1 FROM account WHERE username=?";
						ps = con.prepareStatement(query3);
						ps.setString(1, username);
						rs = ps.executeQuery();

						if (rs.next()) {

							return "Username";
						} else {
							String query4 = "INSERT INTO account (username,password,salt,role,account_id)VALUES(?,?,?,?,LAST_INSERT_ID()) ";
							ps1 = con.prepareStatement(query4);
							ps1.setString(1, data2.getUsername());
							ps1.setString(2, data2.getPassword());
							ps1.setString(3, data2.getSalt());
							ps1.setString(4, data2.getRole());

							int register_again = 0;

							register_again = ps1.executeUpdate();

							if (register_again != 0) {

								System.out.println("CREATE ACCOUNT SUCCESFUL");
								con.commit();
								return "Success";

							}

						}

					} catch (Exception e) {
						System.out.println("ROLLBACK EXECUTED");
						con.rollback();
						System.out.println("SAVED IN PARENT TABLE FAIL " + e.getMessage());

						e.printStackTrace();
					}

				}

			}
		
			
		} catch (Exception e) {
			try {
				System.out.println("rollback happen");
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("\t SQL E1 exception Faik" + e1.getMessage());
				e1.printStackTrace();
			}
			System.out.println("\t Dao_Registration Error \t" + e.getMessage());

		} finally {
			if (rs != null) {
				rs.close();

			}
			if (ps != null) {
				System.out.println("CLOSE");
				ps.close();

			}
			if (ps1 != null) {
				ps1.close();

			}

			if (con != null) {
				con.close();

			}

		}

		return "Error";
	}
	
	
	
	
	
	
	
	

}
