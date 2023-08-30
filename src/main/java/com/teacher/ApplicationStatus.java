package com.teacher;

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
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ApplicationStatus
 */
public class ApplicationStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected String[] getstdData(String sid) {
    	String[] arr = new String[10];
    	String Query = "Select * from studentsprofile where username='"+sid+"'";
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(Query);
			query.next();
			arr[0]=query.getString("fname")+" "+query.getString("lname");
			arr[1]=query.getString("Class");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return arr;
    }
    protected String[] stdData(String sid) {
    	String[] arr = new String[10];
    	String Query = "Select * from students where uname='"+sid+"'";
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(Query);
			query.next();
			String[] ar = getstdData(sid);
			arr[0] = ar[0];
			arr[1] = query.getString("email");
			arr[2] = query.getString("phone");
			arr[3] = ar[1];
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return arr;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletOutputStream out = response.getOutputStream();
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		HttpSession session = request.getSession();
		String uname=(String) session.getAttribute("uname");
		String Query = "Select * from tempapplication where tuid='"+uname+"'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(Query);
			while(query.next()) {
				String suid = query.getString("suid");
				String[] ar = stdData(suid);
				obj.put("suid", suid);
				obj.put("stdName", ar[0]);
				obj.put("email", ar[1]);
				obj.put("phone", ar[2]);
				obj.put("class", ar[3]);
				obj.put("subject", query.getString("subject"));
				arr.put(obj.toMap());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject obj11 = new JSONObject();
		obj11.put("jsonarray", arr);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(obj11.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
