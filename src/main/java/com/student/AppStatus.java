package com.student;

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

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class AppStatus
 */
public class AppStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected String getName(String uid,String tableName) {
		try {
			String QueryPhone = "Select * from "+tableName+" where username='"+uid+"'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(QueryPhone);
			query.next();
			return (query.getString("fname")+" "+query.getString("lname"));
		}catch(Exception e) {
			System.out.println("sql error : "+e);
		}
		return null;
		
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		ServletOutputStream out = res.getOutputStream();
		HttpSession session = req.getSession();
		JSONArray arr = new JSONArray();
		try {
			String QueryPhone = "Select * from tempapplication where suid='"+session.getAttribute("uname")+"'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(QueryPhone);
			
			while(query.next()) {
				obj.put("suid",getName(query.getString("suid"),"studentsprofile"));
				obj.put("tuid", getName(query.getString("tuid"),"teachersprofile"));
				obj.put("subject", query.getString("subject"));
				if(query.getInt("status") == 0) {
					obj.put("status", "pending");
				}else if(query.getInt("status") == 1) {
					obj.put("status", "accepted");
				}else {
					obj.put("status", "rejected");
				}
				arr.put(obj.toMap());
			}
		}catch(Exception e) {
			System.out.print("Sql Error : "+e);
		}
		JSONObject obj11 = new JSONObject();
		obj11.put("jsonarray", arr);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		out.print(arr.toString());
	}

}
