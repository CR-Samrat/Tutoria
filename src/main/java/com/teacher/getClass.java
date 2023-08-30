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
 * Servlet implementation class getClass
 */
public class getClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		HttpSession session = request.getSession();
		String uname=(String) session.getAttribute("uname");
		String Query = "SELECT * FROM classdetails WHERE tuid = '"+uname+"'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			ResultSet query = (st).executeQuery(Query);
			while(query.next()) {
				obj.put("cName", query.getString("cName"));
				obj.put("cId", query.getString("cId"));
				obj.put("subject", query.getString("subject"));
				obj.put("timeset", query.getString("timeSet"));
				arr.put(obj.toMap());
				//System.out.println(query.getString("subject")+"  "+query.getString("cName")+"   "+query.getString("cId")+"");
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

}
