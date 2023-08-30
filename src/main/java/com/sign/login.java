package com.sign;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.json.JSONObject;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public login() {
        super();
    }
	public boolean validate(String uname,String pass,String tableName) {
		try {
			String QueryPhone = "Select * from "+tableName+" where uname='"+uname+"' and password='"+pass+"'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(QueryPhone);
			Boolean bool = query.next();
			con.close();
			return(bool);
		}catch(Exception e) {
			return false;
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletOutputStream out = res.getOutputStream();
		JSONObject obj = new JSONObject();
		StringBuffer jb = new StringBuffer();
		HttpSession session = req.getSession();
		String line = null;
		login obj1 = new login();
		try {
	    	BufferedReader reader = req.getReader();
	    	while ((line = reader.readLine()) != null) {
	    		jb.append(line); // appends new line from req to line String
	    	}		
	    }catch (Exception e) { 
	    	System.out.println(e);
	    }
		
		try {
			JSONObject data = new JSONObject(jb.toString());
			String tableName = "";
			if(data.getString("result").equals("tch")) {
				tableName = "teachers";
			}else if(data.getString("result").equals("std")) {
				tableName = "students";
			}else {
				return;
			}
			if(obj1.validate(data.getString("uname"),data.getString("pass"),tableName)) {
				obj.put("status", 200);
				obj.put("QueryMessage", "login success");
				obj.put("Connection", true);
				obj.put("message", "success");
				session.setAttribute("uname",data.getString("uname"));
				session.setAttribute("pass",data.getString("pass"));
				session.setAttribute("tableName",tableName);
				
			}else {
				obj.put("status", 400);
				obj.put("QueryMessage", "login failed");
				obj.put("Connection", true);
				obj.put("message", "failed");
			}
			
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			out.print(obj.toString());
		}catch(Exception e) {
			System.out.print(e);
		}
	}

}
