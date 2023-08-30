package com.sign;

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

public class isProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public isProfile() {
        super();
    }
    public void isprofile(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	HttpSession session = req.getSession();
    	if(session.getAttribute("uname") != null) {
    		try {
    			String QueryPhone = "Select * from "+session.getAttribute("tableName")+" where uname='"+session.getAttribute("uname")+"' and password='"+session.getAttribute("pass")+"'";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
				java.sql.Statement st = con.createStatement();
				ResultSet query = (st).executeQuery(QueryPhone);
				query.next();
				int isProfile = query.getByte("isProfile");
				if(isProfile == 1 ) {
					//System.out.print("profile there");
					session.setAttribute("isProfile", true);
				}else {
					//System.out.println("profile missing");
					//System.out.print(session.getAttribute("tableName"));
					session.setAttribute("isProfile", false);
				}
    		}catch(Exception e) {
    			
    		}
    	}else {
    		res.sendRedirect("Login.html");
    	}
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		isProfile prof = new isProfile();
		prof.isprofile(req, res);
		ServletOutputStream out = res.getOutputStream();
		JSONObject obj = new JSONObject();
		HttpSession session = req.getSession();
		if(session.getAttribute("uname") != null) {
			obj.put("login",true);
			try {
				String QueryPhone = "Select * from "+session.getAttribute("tableName")+" where uname='"+session.getAttribute("uname")+"' and password='"+session.getAttribute("pass")+"'";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
				java.sql.Statement st = con.createStatement();
				ResultSet query = (st).executeQuery(QueryPhone);
				query.next();
				int isProfile = query.getByte("isProfile");
				obj.put("post", session.getAttribute("tableName"));
				if(isProfile == 1 ) {
					obj.put("isProfile", true);
				}else {
					obj.put("isProfile", false);
				}
				con.close();
			}catch(Exception e) {
				System.out.print(e);
			}
		}else {
			obj.put("login", false);
		}
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		out.print(obj.toString());
	}

}
