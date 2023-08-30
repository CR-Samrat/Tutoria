package com.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class FindTeacher
 */
public class FindTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTeacher() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String[] getEmail(String uname){
    	String QueryPhone = "Select * from teachers where uname='"+uname+"'";
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
    		java.sql.Statement st = con.createStatement();
    		ResultSet query = (st).executeQuery(QueryPhone);
    		query.next();
    		String[] ar = {query.getString("email"),query.getString("phone")};
    		query.getString("email");
    		return ar;
    	}catch(Exception e) {
    		
    	}
    	return null;
    }
    public String getLocation(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	String QueryPhone = "Select * from studentsprofile where username='"+session.getAttribute("uname")+"'";
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
    		java.sql.Statement st = con.createStatement();
    		ResultSet query = (st).executeQuery(QueryPhone);
    		query.next();
    		return query.getString("city");
    	}catch(Exception e) {
    		System.out.println("getLocation Error : " + e);
    	}
    	return null;
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletOutputStream out = response.getOutputStream();
		JSONObject obj = new JSONObject();
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
	    	BufferedReader reader = request.getReader();
	    	while ((line = reader.readLine()) != null) {
	    		jb.append(line); // appends new line from req to line String
	    	}		
	    }catch (Exception e) { 
	    	System.out.println(e);
	    }
		
		JSONArray arr = new JSONArray();
		try{
			JSONObject data = new JSONObject(jb.toString());
			String isChecked = data.getString("isChecked");
			String Query;
			if(isChecked.equals("F")) {
				Query = "Select * from teachersprofile where city='"+getLocation(request,response)+"'";
			}else {
				Query = "Select * from teachersprofile";
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(Query);
			
			while(query.next()) {
				obj.put("name", query.getString("fname") +" "+ query.getString("lname"));
				obj.put("uname", query.getString("username"));
				obj.put("address", query.getString("address"));
				obj.put("qualification", query.getString("qualification"));
				obj.put("primary",query.getString("preferedSubjectPrimary"));
				obj.put("secondary",query.getString("preferedSubjectSecondary"));
				String[] ar = getEmail(query.getString("username"));
				obj.put("email", ar[0]);
				obj.put("phone",ar[1]);
				arr.put(obj.toMap());
			}
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		JSONObject obj11 = new JSONObject();
		obj11.put("jsonarray", arr);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(obj11.toString());
	}

}
