package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class Hospital extends HttpServlet {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare?useTimezone=true&serverTimezone=UTC","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHopital(String hos_name, String address_no,  String address_lane1, String address_lane2, String address_lane3, String city, String tel, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into hospitals(`hospital_ID`,`hospital_name`,`hospital_address_no`,`hospital_address_lane1`,`hospital_address_lane2`,`hospital_address_lane3`,`hospital_city`,`tel`,`email`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hos_name);
			preparedStmt.setString(3, address_no);
			preparedStmt.setString(4, address_lane1);
			preparedStmt.setString(5, address_lane2);
			preparedStmt.setString(6, address_lane3);
			preparedStmt.setString(7, city);
			preparedStmt.setString(8, tel);
			preparedStmt.setString(9, email);
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newHospitals = readHospital();
			 output = "{\"status\":\"success\", \"data\": \"" +newHospitals + "\"}"; 
			 
			//output = "Inserted successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the hospital.\"}";
			//output = "Error while inserting the Hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readHospital() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\'1\'><th>Hospital Name</th><th>Hospital Address No.</th><th>Hospital Address Lane 1</th><th>Hospital Address Lane 2</th><th>Hospital Address Lane 3</th><th>City</th><th>Tel</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from hospitals";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String hospital_id = Integer.toString(rs.getInt("hospital_id"));
				String hospital_name = rs.getString("hospital_name");
				String hospital_address_no = rs.getString("hospital_address_no");
				String hospital_address_lane1 = rs.getString("hospital_address_lane1");
				String hospital_address_lane2 = rs.getString("hospital_address_lane2");
				String hospital_address_lane3 = rs.getString("hospital_address_lane3");
				String hospital_city = rs.getString("hospital_city");
				String Tel = rs.getString("Tel");
				String Email = rs.getString("Email");
				
				// Add into the html table
				output += "<tr><td><input id='hidHospitalIDUpdate' name='hidHospitalIDUpdate' type='hidden' value='" + hospital_id + "'>" + hospital_name + "</td>";
				
				
				output += "<td>" + hospital_address_no + "</td>";
				output += "<td>" + hospital_address_lane1 + "</td>";
				output += "<td>" + hospital_address_lane2 + "</td>";
				output += "<td>" + hospital_address_lane3 + "</td>";
				output += "<td>" + hospital_city + "</td>";
				output += "<td>" + Tel + "</td>";
				output += "<td>" + Email + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='"
						 + hospital_id + "'>" + "</td></tr>";
				
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Hospitals.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateHospital(String hos_Id, String  hos_name, String address_no,  String address_lane1, String address_lane2, String address_lane3, String city, String tel, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE hospitals SET hospital_name=?,hospital_address_no=?,hospital_address_lane1=?,hospital_address_lane2=?,hospital_address_lane3=?,hospital_city=?,tel=?,email=?WHERE hospital_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, hos_name);
			preparedStmt.setString(2, address_no);
			preparedStmt.setString(3, address_lane1);
			preparedStmt.setString(4, address_lane2);
			preparedStmt.setString(5, address_lane3);
			preparedStmt.setString(6, city);
			preparedStmt.setString(7, tel);
			preparedStmt.setString(8, email);
			preparedStmt.setInt(9, Integer.parseInt(hos_Id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated successfully";
			String newHospitals = readHospital();
			 output = "{\"status\":\"success\", \"data\": \"" +newHospitals + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the hospital.\"}";
			//output = "Error while updating the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String hospital_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from hospitals where hospital_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(hospital_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			String newHospitals = readHospital();
			 output = "{\"status\":\"success\", \"data\": \"" +newHospitals + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the hospital.\"}";
			//output = "Error while deleting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
