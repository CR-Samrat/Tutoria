package com.sign;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.String;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
@SuppressWarnings("serial")
@WebServlet("/register")
public class Register extends HttpServlet{
	// return res on duplicate username 
	private boolean validateUsername(String unames,String tableName) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			String QueryUsername = "Select * from "+tableName+" where uname='"+unames+"'";
			ResultSet uname = (st).executeQuery(QueryUsername);
			Boolean bool = uname.next();
			con.close();
			return(bool);
		}catch(Exception e) {
			return false;
		}
		
	}
	// return res on duplicate email
	private boolean validateEmail(String emails,String tableName) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			String QueryEmail = "Select * from "+tableName+" where email='"+emails+"'"; 
			ResultSet email = (st).executeQuery(QueryEmail);
			Boolean bool = email.next();
			con.close();
			return(bool);
		}catch(Exception e) {
			return false;
		}
		
	}
	// return res on duplicate phone 
	private boolean validatePhone(long phones,String tableName) throws SQLException, ClassNotFoundException {
		try {
			String QueryPhone = "Select * from "+tableName+" where phone='"+phones+"'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet phone = (st).executeQuery(QueryPhone);
			Boolean bool = phone.next();
			con.close();
			return(bool);
		}catch(Exception e) {
			return false;
		}
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		ServletOutputStream out = res.getOutputStream();
		JSONObject obj = new JSONObject();
		StringBuffer jb = new StringBuffer();
		Register obj1 = new Register();
		System.out.print("Here is data");;
		//extracting data from request 
	    String line = null;
	    try {
	    	BufferedReader reader = req.getReader();
	    	while ((line = reader.readLine()) != null)
	    		jb.append(line);
	    }catch (Exception e) { 
	    	System.out.println(e);
	    }
		try {
			
			//converting buffered reader to json
			String tableName = "";
			JSONObject data = new JSONObject(jb.toString());
			if(data.getString("result").equals("tch")) {
				tableName = "teachers";
			}else if(data.getString("result").equals("std")) {
				tableName = "students";
			}else {
				return;
			}
			//Sql query to be executed
			String Query = "Insert into "+tableName+" values ('"+data.getString("uname")+"','"+data.getString("email")+"','"+(Long.parseLong(data.getString("phone")))+"','"+data.getString("pass")+"','"+0+"')";
			
			
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			
			if(obj1.validateUsername(data.getString("uname"),tableName)) {
				obj.put("status", 400);
				obj.put("QueryMessage", "duplicate username");
				obj.put("Connection", true);
				obj.put("message", "failed");
			}
			else if(obj1.validateEmail(data.getString("email"),tableName)) {
				obj.put("status", 400);
				obj.put("QueryMessage", "duplicate email");
				obj.put("Connection", true);
				obj.put("message", "failed");
			}
			else if(obj1.validatePhone(Long.parseLong(data.getString("phone")),tableName)) {
				obj.put("status", 400);
				obj.put("QueryMessage", "duplicate phone");
				obj.put("Connection", true);
				obj.put("message", "failed");
			}else {
				try {
					boolean rs = ((java.sql.Statement) st).execute(Query);
					if(!rs) {
						System.out.print("Query Insert Successfull");
					}else {
						System.out.print("Query Insertion failed");
					}
					obj.put("status", 200);
					obj.put("QueryMessage", rs);
					obj.put("Connection", true);
					obj.put("message", "success");
				}catch(Exception e) {
					System.out.print(e);
					out.print(e.toString());
				}	
			}
			
			
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			out.print(obj.toString());
		}catch(Exception e) {
			out.print(e.toString());
		}
		
		
	}
}
