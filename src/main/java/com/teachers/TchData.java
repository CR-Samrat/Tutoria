package com.teachers;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.json.JSONObject;

/**
 * Servlet implementation class TchData
 */
public class TchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TchData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String[] getEmail(HttpServletRequest request, HttpServletResponse response){
    	HttpSession session = request.getSession();
    	String QueryPhone = "Select * from teachers where uname='"+session.getAttribute("uname")+"'";
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ServletOutputStream out = response.getOutputStream();
		JSONObject obj = new JSONObject();
		String QueryPhone = "Select * from teachersprofile where username='"+session.getAttribute("uname")+"'";
		try{
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(QueryPhone);
			query.next();
			obj.put("fname", query.getString("fname"));
			obj.put("lname", query.getString("lname"));
			obj.put("dob", query.getString("dob"));
			obj.put("gender", query.getString("gender"));
			obj.put("Qualification", query.getString("qualification"));
			obj.put("workExperience", query.getString("workExperience"));
			obj.put("primary", query.getString("preferedSubjectPrimary"));
			obj.put("secondary", query.getString("preferedSubjectSecondary"));
			obj.put("address", query.getString("address"));
			obj.put("city", query.getString("city"));
			obj.put("state", query.getString("state"));
			obj.put("country", query.getString("country"));
			obj.put("pin", query.getString("pin"));
			String[] ar = getEmail(request,response);
			obj.put("email", ar[0]);
			obj.put("phone", ar[1]);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(obj.toString());
	}

}
