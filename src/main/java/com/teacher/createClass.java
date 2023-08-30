package com.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Servlet implementation class createClass
 */
public class createClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String className = request.getParameter("className");
		String Subject = request.getParameter("subject");
		System.out.println(className + " " + Subject);
		HttpSession session = request.getSession();
		String tid=(String) session.getAttribute("uname");
		String Query = "Insert into classdetails (tuid,subject,cName) values('"+tid+"','"+Subject+"','"+className+"')"+"";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			boolean rs = ((java.sql.Statement) st).execute(Query);
			if(!rs) {
				System.out.println("Query Insert Successfull");
			}else {
				System.out.println("Query Insertion Failed");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
